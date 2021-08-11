package com.github.thesuperunknown.vgr.game.service;

import java.util.Optional;
import java.util.UUID;

import com.github.thesuperunknown.vgr.game.entity.Game;

public interface GameService {

	public Iterable<Game> findAll();

	public Optional<Game> findById(UUID uuid);

	public Game save(Game game);

	public Optional<Game> deleteById(UUID uuid);

}
