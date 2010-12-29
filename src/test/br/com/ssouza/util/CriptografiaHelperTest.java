package br.com.ssouza.util;

import org.junit.Assert;
import org.junit.Test;

import br.com.ssouza.exception.SystemException;

public class CriptografiaHelperTest {

	private static final byte[] ENCRYPTER_KEY = { 0x43, 0x71, 0x60, 0x3E, 0x1C, 0x7A, 0x6A, 0x18 };

	@Test(expected = SystemException.class)
	public void invalidarInstanciaComChaveNula() {
		CriptografiaHelper.getInst(null);
	}

	@Test(expected = SystemException.class)
	public void invalidarCriptografiaNula() {
		CriptografiaHelper.getInst(ENCRYPTER_KEY).encrypt(null);
	}

	@Test(expected = SystemException.class)
	public void invalidarDecriptografiaNula() {
		CriptografiaHelper.getInst(ENCRYPTER_KEY).decrypt(null);
	}

	@Test
	public void validarCriptografia() {
		CriptografiaHelper helper = CriptografiaHelper.getInst(ENCRYPTER_KEY);

		String encriptado = helper.encrypt("Sérgio");
		Assert.assertNotNull(encriptado);

		String decriptado = helper.decrypt(encriptado);
		Assert.assertEquals("Sérgio", decriptado);
	}

}