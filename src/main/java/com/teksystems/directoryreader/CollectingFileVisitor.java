package com.teksystems.directoryreader;

import com.teksystems.directoryreader.converter.Converter;
import com.teksystems.directoryreader.data.AbstractResource;
import com.teksystems.directoryreader.data.Directory;
import com.teksystems.directoryreader.data.Document;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

/**
 * Responsibility of this class is to visit tree recursively and collects directories and documents into the provided map
 */
public class CollectingFileVisitor extends SimpleFileVisitor<Path> {

	private final Map<String, AbstractResource> map;
	private final String context;
	
	public CollectingFileVisitor(final Map<String, AbstractResource> map, final String context) {
		this.map = map;
		this.context = context;
	}
	
	@Override
	public FileVisitResult preVisitDirectory(final Path path, final BasicFileAttributes basicFileAttributes) throws IOException {
		final String name = Converter.pathToName(path);
		final String url = Converter.pathToUrl(path, context);
		final String parentUrl = Converter.pathToParentUrl(path, context);

		final Directory directory = new Directory(name, url);

		if (!map.isEmpty()) {
			map.get(parentUrl).getChildren().add(directory);
		}
		map.put(url, directory);

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(final Path path, final BasicFileAttributes basicFileAttributes) throws IOException {
		if (!path.toFile().isHidden()) {
			final String name = Converter.pathToName(path);
			final String url = Converter.pathToUrl(path, context);
			final String parentUrl = Converter.pathToParentUrl(path, context);
			final String extension = Converter.nameToExtension(name);

			final Document document = new Document(name, url, extension);

			if (!map.isEmpty()) {
				map.get(parentUrl).getChildren().add(document);
			}
			map.put(url, document);
		}

		return FileVisitResult.CONTINUE;
	}

}
