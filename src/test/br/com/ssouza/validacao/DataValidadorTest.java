package br.com.ssouza.validacao;

import org.junit.Assert;
import org.junit.Test;

public class DataValidadorTest {

	@Test
	public void validarDataNulo() {

		Assert.assertFalse(DataValidador.isValido(null));
	}

	@Test
	public void validarDataVazio() {

		Assert.assertFalse(DataValidador.isValido(""));
	}

	@Test
	public void validarData() {

		Assert.assertTrue(DataValidador.isValido("01/01/2001"));

		Assert.assertTrue(DataValidador.isValido("29/02/2008"));

		Assert.assertTrue(DataValidador.isValido("28/02/2009"));
	}

	@Test
	public void validarDataInvalida() {

		Assert.assertFalse(DataValidador.isValido("29/02/2009"));

		Assert.assertFalse(DataValidador.isValido("29/02/09"));

		Assert.assertFalse(DataValidador.isValido("28-02-2009"));

		Assert.assertFalse(DataValidador.isValido("31/04/2009"));

		Assert.assertFalse(DataValidador.isValido("01/14/2009"));

		Assert.assertFalse(DataValidador.isValido("00/02/2009"));
	}

}