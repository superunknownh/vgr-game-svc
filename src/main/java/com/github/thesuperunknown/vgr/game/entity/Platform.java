package com.github.thesuperunknown.vgr.game.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "games_platforms")
public class Platform implements Serializable {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(game, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Platform other = (Platform) obj;
		return Objects.equals(game, other.game) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Id
	@Column(name = "platform_id")
	private String id;

	@Transient
	private String name;

	@Id
	@ManyToOne
	@JoinColumn(name = "game_id", nullable = false, updatable = false)
	private Game game;

	private static final long serialVersionUID = 7785414856772722077L;

}
