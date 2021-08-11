package com.github.thesuperunknown.vgr.game.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import com.github.thesuperunknown.vgr.game.entity.Platform;
import com.github.thesuperunknown.vgr.game.utils.ResourcesUtils;

@Repository
public class ExtendedPlatformRepositoryImpl implements ExtendedPlatformRepository {

	@Override
	public Optional<Platform> getByID(String id) {
		if (!catalog.containsKey(id)) {
			return Optional.empty();
		}
		return Optional.of(catalog.get(id));
	}

	@PostConstruct
	private void loadStaticCatalog() {
		catalog = new HashMap<>();
		try {
			var lines = ResourcesUtils.getResourceFileLines(CATALOG_RESX);
			for (int i = 1; i < lines.size(); i++) {
				String[] cols = lines.get(i).split(",");
				try {
					var platform = new Platform();
					platform.setId(cols[0]);
					platform.setName(cols[1]);
					catalog.put(cols[0], platform);
				} catch (ArrayIndexOutOfBoundsException ex) {
					continue;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private Map<String, Platform> catalog;
	
	public static final String CATALOG_RESX = "static/platforms-catalog.csv";

}
