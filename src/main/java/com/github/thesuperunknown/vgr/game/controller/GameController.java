package com.github.thesuperunknown.vgr.game.controller;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.thesuperunknown.vgr.game.entity.Game;
import com.github.thesuperunknown.vgr.game.service.GameService;

@RestController
@RequestMapping("/games")
public class GameController {
	
	/**
	 * Gets all the existing games.
	 * 
	 * <code>GET /games</code>
	 * 
	 * @return All the existing games
	 */
	@GetMapping
	public List<Game> readAll() {
		List<Game> games = StreamSupport.stream(gameService.findAll().spliterator(), false).toList();
		return games;
	}
	
	@Autowired
	private GameService gameService;

}
