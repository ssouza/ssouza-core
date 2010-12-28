package br.com.ssouza.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringHelperTest {

	@Test
	public void testRemoverNaoDigitosNulo() {
		String retorno = StringHelper.removerNaoDigitos(null);
		assertNull(retorno);
	}

	@Test
	public void testRemoverNaoDigitosVazio() {
		String retorno = StringHelper.removerNaoDigitos("");
		assertNotNull(retorno);
		assertTrue(retorno.isEmpty());
	}

	@Test
	public void testRemoverNaoDigitosValido() {
		String retorno = StringHelper.removerNaoDigitos("abc123#$%45");
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("12345", retorno);
	}

	@Test(expected = NullPointerException.class)
	public void testIsSequenciaNumericaNulo() {
		StringHelper.isSequenciaNumerica(null);
	}

	@Test
	public void testIsSequenciaNumericaVazio() {
		boolean valido = StringHelper.isSequenciaNumerica("");
		assertFalse(valido);
	}

	@Test
	public void testIsSequenciaNumerica1Caracter() {
		boolean valido = StringHelper.isSequenciaNumerica("1");
		assertFalse(valido);
	}

	@Test
	public void testIsSequenciaNumericaNaoValido() {
		boolean valido = StringHelper.isSequenciaNumerica("123");
		assertFalse(valido);
	}

	@Test
	public void testIsSequenciaNumericaValido() {
		boolean valido = StringHelper.isSequenciaNumerica("11");
		assertTrue(valido);
	}

}
