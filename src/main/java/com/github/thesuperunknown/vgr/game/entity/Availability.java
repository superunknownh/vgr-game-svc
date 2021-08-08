package com.github.thesuperunknown.vgr.game.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Transient;

public class Availability {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Availability other = (Availability) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Column(name = "availability_id", nullable = false)
	private Integer id;

	@Transient
	private String name;

}
