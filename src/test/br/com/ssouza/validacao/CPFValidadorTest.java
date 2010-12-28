package br.com.ssouza.validacao;

import org.junit.Assert;
import org.junit.Test;

public class CPFValidadorTest {

	@Test
	public void validarCPFNulo() {

		Assert.assertFalse(CPFValidador.isValido(null, true));

		Assert.assertFalse(CPFValidador.isValido(null, false));
	}

	@Test
	public void validarCPFVazio() {

		Assert.assertFalse(CPFValidador.isValido("", true));

		Assert.assertFalse(CPFValidador.isValido("", false));
	}

	@Test
	public void validarTamanhoMinimo() {

		Assert.assertFalse(CPFValidador.isValido("000.000-00", true));

		Assert.assertFalse(CPFValidador.isValido("0", false));
	}

	@Test
	public void validarCPFComSequenciaNumerica() {

		Assert.assertFalse(CPFValidador.isValido("000.000.000-00", true));
		Assert.assertFalse(CPFValidador.isValido("00000000000", false));

		Assert.assertFalse(CPFValidador.isValido("111.111.111-11", true));
		Assert.assertFalse(CPFValidador.isValido("11111111111", false));

		Assert.assertFalse(CPFValidador.isValido("222.222.222-22", true));
		Assert.assertFalse(CPFValidador.isValido("22222222222", false));

		Assert.assertFalse(CPFValidador.isValido("333.333.333-33", true));
		Assert.assertFalse(CPFValidador.isValido("33333333333", false));

		Assert.assertFalse(CPFValidador.isValido("444.444.444-44", true));
		Assert.assertFalse(CPFValidador.isValido("44444444444", false));

		Assert.assertFalse(CPFValidador.isValido("555.555.555-55", true));
		Assert.assertFalse(CPFValidador.isValido("55555555555", false));

		Assert.assertFalse(CPFValidador.isValido("666.666.666-66", true));
		Assert.assertFalse(CPFValidador.isValido("66666666666", false));

		Assert.assertFalse(CPFValidador.isValido("777.777.777-77", true));
		Assert.assertFalse(CPFValidador.isValido("77777777777", false));

		Assert.assertFalse(CPFValidador.isValido("888.888.888-88", true));
		Assert.assertFalse(CPFValidador.isValido("88888888888", false));

		Assert.assertFalse(CPFValidador.isValido("999.999.999-99", true));
		Assert.assertFalse(CPFValidador.isValido("99999999999", false));
	}

	@Test
	public void validarCPFValido() {

		Assert.assertTrue(CPFValidador.isValido("378.388.824-78", true));
		Assert.assertTrue(CPFValidador.isValido("37838882478", false));
	}

	@Test
	public void validarCPFInvalido() {

		Assert.assertFalse(CPFValidador.isValido("378.388.824-77", true));
		Assert.assertFalse(CPFValidador.isValido("37838882477", false));
	}

}