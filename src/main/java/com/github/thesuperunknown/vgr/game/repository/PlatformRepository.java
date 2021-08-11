package com.github.thesuperunknown.vgr.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thesuperunknown.vgr.game.entity.Platform;

public interface PlatformRepository extends JpaRepository<Platform, String>, ExtendedPlatformRepository {
	
}
