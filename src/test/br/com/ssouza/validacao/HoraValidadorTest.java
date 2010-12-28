package br.com.ssouza.validacao;

import org.junit.Assert;
import org.junit.Test;

public class HoraValidadorTest {

	@Test
	public void validarHoraNula() {

		Assert.assertFalse(HoraValidador.isValido(null));
	}

	@Test
	public void validarHoraVazia() {

		Assert.assertFalse(HoraValidador.isValido(""));
	}

	@Test
	public void validarHora() {

		Assert.assertTrue(HoraValidador.isValido("00:00"));

		Assert.assertTrue(HoraValidador.isValido("00:00:00"));

		Assert.assertTrue(HoraValidador.isValido("23:59"));

		Assert.assertTrue(HoraValidador.isValido("23:59:59"));
	}

	@Test
	public void validarHoraInvalida() {

		Assert.assertFalse(HoraValidador.isValido("23:60"));

		Assert.assertFalse(HoraValidador.isValido("23:60:00"));

		Assert.assertFalse(HoraValidador.isValido("23:00:60"));

		Assert.assertFalse(HoraValidador.isValido("24:00"));

		Assert.assertFalse(HoraValidador.isValido("24:00:00"));
	}

}