package com.github.thesuperunknown.vgr.game.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourcesUtils {

	public static List<String> getResourceFileLines(String resourceName) throws IOException {
		Resource resourceFile = resourceLoader.getResource("classpath:" + resourceName);
		List<String> lines = new ArrayList<>();
		try (BufferedReader fileIn = new BufferedReader(new FileReader(resourceFile.getFile()))) {
			String line;
			while ((line = fileIn.readLine()) != null) {
				lines.add(line);
			}
		}
		return lines;
	}

	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
}
