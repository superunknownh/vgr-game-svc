package com.github.thesuperunknown.vgr.game.repository;

import java.util.Optional;

import com.github.thesuperunknown.vgr.game.entity.Availability;

public interface AvailabilityRepository {

	Optional<Availability> getById(int id);

}
