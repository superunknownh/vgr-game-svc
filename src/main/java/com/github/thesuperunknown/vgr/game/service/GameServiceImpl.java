package com.github.thesuperunknown.vgr.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.thesuperunknown.vgr.game.entity.Game;
import com.github.thesuperunknown.vgr.game.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {

	@Override
	@Transactional(readOnly = true)
	public Iterable<Game> findAll() {
		return gameRepository.findAll();
	}
	
	@Autowired
	private GameRepository gameRepository;

}
