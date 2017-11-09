package com.teksystems.directoryreader;

import com.teksystems.directoryreader.data.AbstractResource;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class DirectoryReaderUtil {

	private static final String TEST_DIRECTORY = "./testdirectory";
	private static final String PROJECT_DIRECTORY = "Main Project";

	public static void main(String[] args) throws Exception {
		new DirectoryReaderUtil().read();
	}

	public void read() throws Exception {
		final Map<String, AbstractResource> map = new LinkedHashMap<>();
		Files.walkFileTree(Paths.get(TEST_DIRECTORY + "/" + PROJECT_DIRECTORY), new CollectingFileVisitor(map, TEST_DIRECTORY));

		map.get(PROJECT_DIRECTORY).print("");
	}

}
