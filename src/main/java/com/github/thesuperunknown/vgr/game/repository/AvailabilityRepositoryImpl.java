package com.github.thesuperunknown.vgr.game.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import com.github.thesuperunknown.vgr.game.entity.Availability;
import com.github.thesuperunknown.vgr.game.utils.ResourcesUtils;

@Repository
public class AvailabilityRepositoryImpl implements AvailabilityRepository {

	@Override
	public Optional<Availability> getById(int id) {
		if (!catalog.containsKey(id)) {
			return Optional.empty();
		}
		return Optional.of(catalog.get(id));
	}

	@PostConstruct
	private void loadStaticCatalog() {
		catalog = new HashMap<>();
		try {
			for (var line : ResourcesUtils.getResourceFileLines(CATALOG_RESX)) {
				String[] cols = line.split(",");
				try {
					var availability = new Availability();
					availability.setId(Integer.parseInt(cols[0]));
					availability.setName(cols[1]);
					catalog.put(Integer.parseInt(cols[0]), availability);
				} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
					continue;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private Map<Integer, Availability> catalog;

	public static final String CATALOG_RESX = "static/availabilities-catalog.csv";

}
