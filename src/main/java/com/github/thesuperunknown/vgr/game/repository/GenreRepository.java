package com.github.thesuperunknown.vgr.game.repository;

import java.util.Optional;

import com.github.thesuperunknown.vgr.game.entity.Genre;

public interface GenreRepository {

	Optional<Genre> getById(int id);

}
