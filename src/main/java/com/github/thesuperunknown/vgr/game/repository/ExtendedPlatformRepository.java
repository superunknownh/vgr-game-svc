package com.github.thesuperunknown.vgr.game.repository;

import java.util.Optional;

import com.github.thesuperunknown.vgr.game.entity.Platform;

public interface ExtendedPlatformRepository {

	Optional<Platform> getByID(String id);

}
