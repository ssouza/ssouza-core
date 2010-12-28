package br.com.ssouza.validacao;

import org.junit.Assert;
import org.junit.Test;

public class CNPJValidadorTest {

	@Test
	public void validarCNPJNulo() {

		Assert.assertFalse(CNPJValidador.isValido(null, true));

		Assert.assertFalse(CNPJValidador.isValido(null, false));
	}

	@Test
	public void validarCNPJVazio() {

		Assert.assertFalse(CNPJValidador.isValido("", true));

		Assert.assertFalse(CNPJValidador.isValido("", false));
	}

	@Test
	public void validarTamanhoMinimo() {

		Assert.assertFalse(CNPJValidador.isValido("000.000/0000-00", true));

		Assert.assertFalse(CNPJValidador.isValido("0", false));
	}

	@Test
	public void validarSequenciaNumerica() {

		Assert.assertFalse(CNPJValidador.isValido("00.000.000/0000-00", true));
		Assert.assertFalse(CNPJValidador.isValido("00000000000000", false));

		Assert.assertFalse(CNPJValidador.isValido("11.111.111/1111-11", true));
		Assert.assertFalse(CNPJValidador.isValido("11111111111111", false));

		Assert.assertFalse(CNPJValidador.isValido("22.222.222/2222-22", true));
		Assert.assertFalse(CNPJValidador.isValido("22222222222222", false));

		Assert.assertFalse(CNPJValidador.isValido("33.333.333/3333-33", true));
		Assert.assertFalse(CNPJValidador.isValido("33333333333333", false));

		Assert.assertFalse(CNPJValidador.isValido("44.444.444/4444-44", true));
		Assert.assertFalse(CNPJValidador.isValido("44444444444444", false));

		Assert.assertFalse(CNPJValidador.isValido("55.555.555/5555-55", true));
		Assert.assertFalse(CNPJValidador.isValido("55555555555555", false));

		Assert.assertFalse(CNPJValidador.isValido("66.666.666/6666-66", true));
		Assert.assertFalse(CNPJValidador.isValido("66666666666666", false));

		Assert.assertFalse(CNPJValidador.isValido("77.777.777/7777-77", true));
		Assert.assertFalse(CNPJValidador.isValido("77777777777777", false));

		Assert.assertFalse(CNPJValidador.isValido("88.888.888/8888-88", true));
		Assert.assertFalse(CNPJValidador.isValido("88888888888888", false));

		Assert.assertFalse(CNPJValidador.isValido("99.999.999/9999-99", true));
		Assert.assertFalse(CNPJValidador.isValido("99999999999999", false));
	}

	@Test
	public void validarCNPJValido() {

		Assert.assertTrue(CNPJValidador.isValido("73.314.239/0001-00", true));
		Assert.assertTrue(CNPJValidador.isValido("73314239000100", false));
	}

	@Test
	public void validarCNPJInvalido() {

		Assert.assertFalse(CNPJValidador.isValido("73.314.239/0001-01", true));
		Assert.assertFalse(CNPJValidador.isValido("73314239000101", false));
	}

}