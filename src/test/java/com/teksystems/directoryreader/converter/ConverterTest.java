package com.teksystems.directoryreader.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConverterTest {

	@Mock
	private Path path;

	@Mock
	private Path filename;

	@Mock
	private Path parent;

	@Test
	public void pathToName_fileNameIsNotNull_converted() {
		when(path.getFileName()).thenReturn(filename);
		when(filename.toString()).thenReturn("filename");

		assertThat(Converter.pathToName(path)).isEqualTo("filename");
	}

	@Test
	public void pathToName_fileNameIsNull_emptyString() {
		when(path.getFileName()).thenReturn(null);

		assertThat(Converter.pathToName(path)).isEqualTo("");
	}

	@Test
	public void pathToUrl_converted() {
		when(path.toString()).thenReturn("context/path2/test");

		assertThat(Converter.pathToUrl(path, "context")).isEqualTo("path2/test");
	}

	@Test
	public void pathToParentUrl_parentIsNotNull_converted() {
		when(path.getParent()).thenReturn(parent);
		when(parent.toString()).thenReturn("context/path2/test");

		assertThat(Converter.pathToParentUrl(path, "context")).isEqualTo("path2/test");
	}

	@Test
	public void pathToParentUrl_parentIsNull_emptyString() {
		when(path.getParent()).thenReturn(null);

		assertThat(Converter.pathToParentUrl(path, "context")).isEqualTo("");
	}

	@Test
	public void nameToExtension_normalExtension_converted() {
		assertThat(Converter.nameToExtension("filename.ext")).isEqualTo(".ext");
	}

	@Test
	public void nameToExtension_noExtension_converted() {
		assertThat(Converter.nameToExtension("filename")).isEqualTo("");
	}

}
