package com.teksystems.directoryreader;

import com.teksystems.directoryreader.data.AbstractResource;
import com.teksystems.directoryreader.data.Directory;
import com.teksystems.directoryreader.data.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CollectingFileVisitorTest {

	@Mock
	private Path path;

	@Mock
	private File file;

	@Mock
	private Path filename;

	@Mock
	private Path parent;

	@Mock
	private BasicFileAttributes basicFileAttributes;

	@Test
	public void preVisitDirectory() throws Exception {
		when(path.toString()).thenReturn("path1");
		when(path.getFileName()).thenReturn(filename);
		when(path.getParent()).thenReturn(parent);
		when(filename.toString()).thenReturn("filename");
		when(parent.toString()).thenReturn("context/path2/test");

		final Map<String, AbstractResource> map = new LinkedHashMap<>();

		final CollectingFileVisitor collectingFileVisitor = new CollectingFileVisitor(map, "context");

		collectingFileVisitor.preVisitDirectory(path, basicFileAttributes);

		assertThat(map).hasSize(1);

		assertThat(map.get("path1")).isInstanceOf(Directory.class);

		assertThat(map.get("path1").getName()).isEqualTo("filename");
		assertThat(map.get("path1").getUrl()).isEqualTo("path1");
		assertThat(map.get("path1").getChildren()).hasSize(0);
	}

	@Test
	public void visitFile_hidden() throws Exception {
		when(path.toFile()).thenReturn(file);
		when(file.isHidden()).thenReturn(Boolean.TRUE);

		final Map<String, AbstractResource> map = new LinkedHashMap<>();

		final CollectingFileVisitor collectingFileVisitor = new CollectingFileVisitor(map, "context");

		collectingFileVisitor.visitFile(path, basicFileAttributes);

		assertThat(map).hasSize(0);
	}

	@Test
	public void visitFile_visible() throws Exception {
		when(path.toFile()).thenReturn(file);
		when(file.isHidden()).thenReturn(Boolean.FALSE);

		when(path.toString()).thenReturn("path1");
		when(path.getFileName()).thenReturn(filename);
		when(path.getParent()).thenReturn(parent);
		when(filename.toString()).thenReturn("filename.ext");
		when(parent.toString()).thenReturn("context/path2/test");

		final Map<String, AbstractResource> map = new LinkedHashMap<>();

		final CollectingFileVisitor collectingFileVisitor = new CollectingFileVisitor(map, "context");

		collectingFileVisitor.visitFile(path, basicFileAttributes);

		assertThat(map).hasSize(1);

		assertThat(map.get("path1")).isInstanceOf(Document.class);

		assertThat(map.get("path1").getName()).isEqualTo("filename.ext");
		assertThat(map.get("path1").getUrl()).isEqualTo("path1");
		assertThat(((Document) map.get("path1")).getExtension()).isEqualTo(".ext");
		assertThat(map.get("path1").getChildren()).hasSize(0);
	}

}
