package br.com.ssouza.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResourceBundleHelperTest {

	private ResourceBundleHelper bundleHelper;

	@Before
	public void setUp() throws Exception {

		bundleHelper = ResourceBundleHelper.getInst("ssouza");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void validarKeyNula() {
		bundleHelper.getString(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validarKeyVazia() {
		bundleHelper.getString("");
	}

	@Test
	public void validarString() {
		Assert.assertEquals("", bundleHelper.getString("ssouza.string.vazia"));
		Assert.assertEquals("ssouza", bundleHelper.getString("ssouza.string"));
	}

	@Test
	public void validarNaoInteiro() {
		Assert.assertEquals(0, bundleHelper.getInt("ssouza.int.string"));
	}

	@Test
	public void validarInteiro() {
		Assert.assertEquals(10, bundleHelper.getInt("ssouza.int"));
		Assert.assertEquals(0, bundleHelper.getInt("ssouza.int.vazio"));
	}

	@Test
	public void validarNaoBoolean() {
		Assert.assertEquals(false, bundleHelper.getBoolean("ssouza.boolean.y"));
		Assert.assertEquals(false, bundleHelper.getBoolean("ssouza.boolean.n"));
	}

	@Test
	public void validarBoolean() {
		Assert.assertEquals(true, bundleHelper.getBoolean("ssouza.boolean.true"));
		Assert.assertEquals(false, bundleHelper.getBoolean("ssouza.boolean.false"));
		Assert.assertEquals(true, bundleHelper.getBoolean("ssouza.boolean.yes"));
		Assert.assertEquals(false, bundleHelper.getBoolean("ssouza.boolean.no"));
		Assert.assertEquals(true, bundleHelper.getBoolean("ssouza.boolean.on"));
		Assert.assertEquals(false, bundleHelper.getBoolean("ssouza.boolean.off"));
	}

}
