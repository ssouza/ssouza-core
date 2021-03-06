package br.com.ssouza.util;

import org.junit.Assert;
import org.junit.Test;

public class FormatacaoHelperTest {

	@Test(expected = NullPointerException.class)
	public void testCpfComMascaraNula() {
		FormatacaoHelper.cpfComMascara(null);
	}

	@Test
	public void testCpfComMascaraVazia() {
		Assert.assertEquals("000.000.000-00", FormatacaoHelper.cpfComMascara(""));
	}

	@Test
	public void testCpfComMascara() {
		Assert.assertEquals("123.456.789-10", FormatacaoHelper.cpfComMascara("12345678910"));
	}

	@Test(expected = NullPointerException.class)
	public void testCnpjComMascaraNula() {
		FormatacaoHelper.cnpjComMascara(null);
	}

	@Test
	public void testCnpjComMascaraVazia() {
		Assert.assertEquals("00.000.000/0000-00", FormatacaoHelper.cnpjComMascara(""));
	}

	@Test
	public void testCnpjComMascara() {
		Assert.assertEquals("12.345.678/1234-56", FormatacaoHelper.cnpjComMascara("12345678123456"));
	}

	@Test
	public void testCompleteComZerosAEsquerda() {
		Assert.assertEquals("009", FormatacaoHelper.completeComZerosAEsquerda("9", 3));
	}

}
