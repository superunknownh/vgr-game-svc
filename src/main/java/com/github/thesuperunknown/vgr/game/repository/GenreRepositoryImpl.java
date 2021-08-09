package com.github.thesuperunknown.vgr.game.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.github.thesuperunknown.vgr.game.entity.Genre;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

	@Override
	public Optional<Genre> getById(int id) {
		var url = configuration.getHost() + "/genres/" + id;
		try {
			var genre = client.get().uri(url).retrieve().bodyToMono(Genre.class).block();
			if (genre == null) {
				return Optional.empty();
			}
			return Optional.of(genre);
		} catch (WebClientResponseException ex) {
			return Optional.empty();
		}
	}

	private final WebClient client = WebClient.create();

	@Autowired
	private GenreRepositoryConfiguration configuration;

}
