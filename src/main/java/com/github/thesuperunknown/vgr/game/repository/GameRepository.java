package com.github.thesuperunknown.vgr.game.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.thesuperunknown.vgr.game.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {

}
