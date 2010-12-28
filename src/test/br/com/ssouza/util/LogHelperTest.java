package br.com.ssouza.util;

import org.junit.Assert;
import org.junit.Test;

public class LogHelperTest {

	@Test
	public void testGetInstance() {
		Assert.assertEquals("br.com.ssouza", LogHelper.getInstance().getName());
	}

}
