package com.teksystems.directoryreader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DirectoryReaderUtilTest {

	@Test
	public void generalTest() throws Exception {
		new DirectoryReaderUtil().read();
	}

}
