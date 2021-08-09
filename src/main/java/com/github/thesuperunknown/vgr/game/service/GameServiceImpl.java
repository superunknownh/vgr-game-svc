package com.github.thesuperunknown.vgr.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.thesuperunknown.vgr.game.entity.Game;
import com.github.thesuperunknown.vgr.game.repository.AvailabilityRepository;
import com.github.thesuperunknown.vgr.game.repository.GameRepository;
import com.github.thesuperunknown.vgr.game.repository.GenreRepository;
import com.github.thesuperunknown.vgr.game.repository.PlatformRepository;

@Service
public class GameServiceImpl implements GameService {

	@Override
	@Transactional(readOnly = true)
	public Iterable<Game> findAll() {
		var games = gameRepository.findAll();
		games.forEach(game -> {
			var genre = genreRepository.getById(game.getGenre().getId());
			if (genre.isPresent()) {
				game.getGenre().setName(genre.get().getName());
			}
			var availability = availabilityRepository.getById(game.getAvailability().getId());
			if (availability.isPresent()) {
				game.getAvailability().setName(availability.get().getName());
			}
			game.getPlatforms().forEach(platform -> {
				var oPlatform = platformRepository.getById(platform.getId());
				if (oPlatform.isPresent()) {
					platform.setName(oPlatform.get().getName());
				}
			});
		});
		return games;
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
