package com.github.thesuperunknown.vgr.game.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	 * @return All the existing games.
	 */
	@GetMapping
	public List<Game> readAll() {
		return StreamSupport.stream(gameService.findAll().spliterator(), false).toList();
	}

	/**
	 * Gets an existing game by its ID.
	 * 
	 * <code>GET /games/{id}</code>
	 * 
	 * @param uuid The game ID.
	 * @return The existing game by its ID.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable(name = "id") UUID uuid) {
		var oGame = gameService.findById(uuid);
		if (oGame.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oGame);
	}

	/**
	 * Creates a new game.
	 * 
	 * @param game The new game data.
	 * @return The created response.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Game game) {
		return ResponseEntity.status(HttpStatus.CREATED).body(gameService.save(game));
	}

	/**
	 * Deletes an existing game by its ID.
	 * 
	 * <code>DELETE /games/{id}</code>
	 * 
	 * @param uuid The game ID.
	 * @return The deleted response.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") UUID uuid) {
		var oGame = gameService.deleteById(uuid);
		if (oGame.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}

	@Autowired
	private GameService gameService;

}
