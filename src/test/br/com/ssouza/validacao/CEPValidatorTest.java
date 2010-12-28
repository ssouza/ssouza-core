package br.com.ssouza.validacao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CEPValidatorTest {

	@Test
	public void validarCEPNulo() {

		assertFalse(CEPValidador.isValido(null, true));

		assertFalse(CEPValidador.isValido(null, false));
	}

	@Test
	public void validarCEPVazio() {

		assertFalse(CEPValidador.isValido("", true));

		assertFalse(CEPValidador.isValido("", false));
	}

	@Test
	public void validarTamanhoMinimo() {

		assertFalse(CEPValidador.isValido("00000-00", true));

		assertFalse(CEPValidador.isValido("0", false));
	}

	@Test
	public void validarSequenciaNumerica() {

		assertFalse(CEPValidador.isValido("00000-000", true));
		assertFalse(CEPValidador.isValido("00000000", false));

		assertFalse(CEPValidador.isValido("11111-111", true));
		assertFalse(CEPValidador.isValido("11111111", false));

		assertFalse(CEPValidador.isValido("22222-222", true));
		assertFalse(CEPValidador.isValido("22222222", false));

		assertFalse(CEPValidador.isValido("33333-333", true));
		assertFalse(CEPValidador.isValido("33333333", false));

		assertFalse(CEPValidador.isValido("44444-444", true));
		assertFalse(CEPValidador.isValido("44444444", false));

		assertFalse(CEPValidador.isValido("55555-555", true));
		assertFalse(CEPValidador.isValido("55555555", false));

		assertFalse(CEPValidador.isValido("66666-666", true));
		assertFalse(CEPValidador.isValido("66666666", false));

		assertFalse(CEPValidador.isValido("77777-777", true));
		assertFalse(CEPValidador.isValido("77777777", false));

		assertFalse(CEPValidador.isValido("88888-888", true));
		assertFalse(CEPValidador.isValido("88888888", false));

		assertFalse(CEPValidador.isValido("99999-999", true));
		assertFalse(CEPValidador.isValido("99999999", false));

	}

	@Test
	public void validarCEPValido() {

		assertTrue(CEPValidador.isValido("40100-000", true));
		assertTrue(CEPValidador.isValido("40100000", false));
	}

}