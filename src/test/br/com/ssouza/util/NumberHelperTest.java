package br.com.ssouza.util;

import org.junit.Assert;
import org.junit.Test;

public class NumberHelperTest {

	@Test
	public void validarIdNulo() {
		Assert.assertFalse(NumberHelper.isIdValido(null));
	}

	@Test
	public void invalidarIdZero() {
		Assert.assertFalse(NumberHelper.isIdValido(0L));
		Assert.assertFalse(NumberHelper.isIdValido(0));
		Assert.assertFalse(NumberHelper.isIdValido((short) 0));
	}

	@Test
	public void validarId() {
		Assert.assertTrue(NumberHelper.isIdValido(1l));
		Assert.assertTrue(NumberHelper.isIdValido(1));
		Assert.assertTrue(NumberHelper.isIdValido((short) 1));
	}

}