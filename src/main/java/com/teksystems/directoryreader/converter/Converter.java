package com.teksystems.directoryreader.converter;

import java.nio.file.Path;
import java.util.Optional;

/**
 * Converts objects
 */
public class Converter {

	public static String pathToName(final Path path) {
		return Optional.ofNullable(path.getFileName()).map(Path::toString).orElse("");
	}

	public static String pathToUrl(final Path path, final String context) {
		return path.toString().replace(context + "/", "");
	}

	public static String pathToParentUrl(final Path path, final String context) {
		return Optional.ofNullable(path.getParent()).map(Path::toString).orElse("").replace(context + "/", "");
	}

	public static String nameToExtension(final String name) {
		final int lastIndexOf = name.lastIndexOf('.');
		if (lastIndexOf < 0) {
			return "";
		}

		return name.substring(lastIndexOf);
	}

}
