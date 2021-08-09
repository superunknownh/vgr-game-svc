package com.github.thesuperunknown.vgr.game.repository;

import java.util.Optional;

import com.github.thesuperunknown.vgr.game.entity.Platform;

public interface PlatformRepository {

	Optional<Platform> getById(String id);
	
}
