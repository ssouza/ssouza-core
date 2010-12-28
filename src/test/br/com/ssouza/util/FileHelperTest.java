package br.com.ssouza.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileHelperTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test(expected = IllegalArgumentException.class)
	public void validarRealPathNulo() {
		FileHelper.setRealPath(null);
		FileHelper.getRealPath();
	}

	@Test
	public void validarRealPath() {
		FileHelper.setRealPath("/home/sergio/");
		Assert.assertEquals("/home/sergio/", FileHelper.getRealPath());
	}

}
