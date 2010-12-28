package br.com.ssouza.validacao;

import org.junit.Assert;
import org.junit.Test;

public class TelefoneValidadorTest {

	@Test
	public void validarTelefoneNulo() {

		Assert.assertFalse(TelefoneValidador.isValido(null));
	}

	@Test
	public void validarTelefoneVazio() {

		Assert.assertFalse(TelefoneValidador.isValido(""));
	}

	@Test
	public void validarTelefone() {

		Assert.assertTrue(TelefoneValidador.isValido("(71)9876-5432"));

		Assert.assertTrue(TelefoneValidador.isValido("(71) 9876-5432"));
	}

	@Test
	public void validarTelefoneInvalido() {

		Assert.assertFalse(TelefoneValidador.isValido("(a1)9876-5432"));

		Assert.assertFalse(TelefoneValidador.isValido("(a1) 9876-5432"));

		Assert.assertFalse(TelefoneValidador.isValido("9876-5432"));
	}

}