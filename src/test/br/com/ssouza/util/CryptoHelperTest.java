package br.com.ssouza.util;

import org.junit.Assert;
import org.junit.Test;

public class CryptoHelperTest {

	@Test(expected = IllegalArgumentException.class)
	public void testEncryptWithNull() {
		CryptoHelper.getInstance().encrypt(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEncryptWithEmpty() {
		CryptoHelper.getInstance().encrypt("");
	}

	@Test
	public void testEncrypt() {
		Assert.assertNotNull(CryptoHelper.getInstance().encrypt("Sérgio"));
	}

	@Test
	public void testCheck() {
		String hashed = CryptoHelper.getInstance().encrypt("Sérgio");
		Assert.assertNotNull(hashed);
		Assert.assertTrue(CryptoHelper.getInstance().check("Sérgio", hashed));
	}

	@Test
	public void testEncryptWithMD5() {
		Assert.assertNotNull(CryptoHelper.getInstance().encryptWithMD5("Sérgio"));
	}

	@Test
	public void testCheckWithMD5() {
		String hashed = CryptoHelper.getInstance().encryptWithMD5("Sérgio");
		Assert.assertNotNull(hashed);
		Assert.assertTrue(CryptoHelper.getInstance().checkWithMD5("Sérgio", hashed));
	}

	@Test
	public void testEncryptWithSHA1() {
		Assert.assertNotNull(CryptoHelper.getInstance().encryptWithSHA1("Sérgio"));
	}

	@Test
	public void testCheckWithSHA1() {
		String hashed = CryptoHelper.getInstance().encryptWithSHA1("Sérgio");
		Assert.assertNotNull(hashed);
		Assert.assertTrue(CryptoHelper.getInstance().checkWithSHA1("Sérgio", hashed));
	}

	@Test
	public void testEncryptWithSHA256() {
		Assert.assertNotNull(CryptoHelper.getInstance().encryptWithSHA256("Sérgio"));
	}

	@Test
	public void testCheckWithSHA256() {
		String hashed = CryptoHelper.getInstance().encryptWithSHA256("Sérgio");
		Assert.assertNotNull(hashed);
		Assert.assertTrue(CryptoHelper.getInstance().checkWithSHA256("Sérgio", hashed));
	}

	@Test
	public void testEncryptWithSHA512() {
		Assert.assertNotNull(CryptoHelper.getInstance().encryptWithSHA512("Sérgio"));
	}

	@Test
	public void testCheckWithSHA512() {
		String hashed = CryptoHelper.getInstance().encryptWithSHA512("Sérgio");
		Assert.assertNotNull(hashed);
		Assert.assertTrue(CryptoHelper.getInstance().checkWithSHA512("Sérgio", hashed));
	}

}