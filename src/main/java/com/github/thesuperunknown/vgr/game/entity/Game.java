package com.github.thesuperunknown.vgr.game.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "games")
public class Game {

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	@Override
	public int hashCode() {
		return Objects.hash(availability, createdAt, developer, genre, id, name, platforms, publisher, updatedAt, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return Objects.equals(availability, other.availability) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(developer, other.developer) && Objects.equals(genre, other.genre)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(platforms, other.platforms) && Objects.equals(publisher, other.publisher)
				&& Objects.equals(updatedAt, other.updatedAt) && Objects.equals(year, other.year);
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", year=" + year + ", publisher=" + publisher + ", developer="
				+ developer + ", genre=" + genre + ", platforms=" + platforms + ", availability=" + availability
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	/*
	 * https://thorben-janssen.com/generate-uuids-primary-keys-hibernate/
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", length = 16, updatable = false, nullable = false)
	private UUID id;

	@Column(length = 64, nullable = false)
	private String name;

	private Integer year;

	@Column(length = 64)
	private String publisher;

	@Column(length = 64)
	private String developer;

	@Embedded
	private Genre genre;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
	private List<Platform> platforms;

	@Embedded
	private Availability availability;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}
