package com.github.thesuperunknown.vgr.game.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.thesuperunknown.vgr.game.entity.Game;
import com.github.thesuperunknown.vgr.game.repository.AvailabilityRepository;
import com.github.thesuperunknown.vgr.game.repository.GameRepository;
import com.github.thesuperunknown.vgr.game.repository.GenreRepository;
import com.github.thesuperunknown.vgr.game.repository.PlatformRepository;

@Service
/*
 * https://www.callicoder.com/hibernate-spring-boot-jpa-many-to-many-mapping-example/
 */
public class GameServiceImpl implements GameService {

	@Override
	@Transactional(readOnly = true)
	public Iterable<Game> findAll() {
		var games = gameRepository.findAll();
		games.forEach(game -> {
			game = enrichGame(game);
		});
		return games;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Game> findById(UUID uuid) {
		var oGame = gameRepository.findById(uuid);
		if (oGame.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(enrichGame(oGame.get()));
	}

	@Override
	@Transactional
	public Game save(Game game) {
		game.setId(UUID.randomUUID());
		return gameRepository.save(game);
	}

	@Override
	@Transactional
	public Optional<Game> deleteById(UUID uuid) {
		var oGame = gameRepository.findById(uuid);
		if (oGame.isEmpty()) {
			return Optional.empty();
		}
		gameRepository.delete(oGame.get());
		return oGame;
	}

	private Game enrichGame(Game game) {
		var genre = genreRepository.getById(game.getGenre().getId());
		if (genre.isPresent()) {
			game.getGenre().setName(genre.get().getName());
		}
		var availability = availabilityRepository.getById(game.getAvailability().getId());
		if (availability.isPresent()) {
			game.getAvailability().setName(availability.get().getName());
		}
		game.getPlatforms().forEach(platform -> {
			var oPlatform = platformRepository.getByID(platform.getId());
			if (oPlatform.isPresent()) {
				platform.setName(oPlatform.get().getName());
			}
		});
		return game;
	}

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private AvailabilityRepository availabilityRepository;

	@Autowired
	private PlatformRepository platformRepository;

}
