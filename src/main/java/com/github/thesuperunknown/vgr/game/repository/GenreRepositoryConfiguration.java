package com.github.thesuperunknown.vgr.game.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "genres.repository")
public class GenreRepositoryConfiguration {

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	private String host;
}
