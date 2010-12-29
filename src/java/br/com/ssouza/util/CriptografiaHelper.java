package br.com.ssouza.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import br.com.ssouza.exception.SystemException;

/**
 * Classe que define métodos de des/criptografia.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 29, 2010
 */
public final class CriptografiaHelper {

	private final static String algorithm = "DES";
	private byte[] keyBytes;
	private Cipher eCipher;
	private Cipher dCipher;

	private CriptografiaHelper(byte[] key) {
		try {
			keyBytes = addParity(key);
			SecretKey _secretKey = new SecretKeySpec(keyBytes, algorithm);

			eCipher = Cipher.getInstance(algorithm);
			eCipher.init(Cipher.ENCRYPT_MODE, _secretKey);
			dCipher = Cipher.getInstance(algorithm);
			dCipher.init(Cipher.DECRYPT_MODE, _secretKey);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static CriptografiaHelper getInst(byte[] key) {
		return new CriptografiaHelper(key);
	}

	/**
	 * Método que criptografa um valor.
	 * 
	 * @param value
	 * @return String
	 * @throws SystemException
	 */
	public String encrypt(String value) {
		try {
			byte[] encoded = value.getBytes(Constantes.CHARSET_ISO);
			byte[] encrypt = eCipher.doFinal(encoded);
			return new BASE64Encoder().encode(encrypt);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Método que descriptografa um valor criptografado.
	 * 
	 * @param value
	 * @return String
	 * @throws SystemException
	 */
	public String decrypt(String value) {
		try {
			byte[] decoded = new BASE64Decoder().decodeBuffer(value);
			byte[] decrypt = dCipher.doFinal(decoded);
			return new String(decrypt, Constantes.CHARSET_ISO);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	private byte[] addParity(byte[] in) {
		byte[] resultArray = new byte[8];
		int result = 1;
		int bitCount = 0;
		for (int i = 0; i < 56; i++) {
			boolean bit = (in[6 - i / 8] & (1 << (i % 8))) > 0;
			if (bit) {
				resultArray[7 - result / 8] |= (1 << (result % 8)) & 0xFF;
				bitCount++;
			}
			if ((i + 1) % 7 == 0) {
				if (bitCount % 2 == 0)
					resultArray[7 - result / 8] |= 1;
				result++;
				bitCount = 0;
			}
			result++;
		}
		return resultArray;
	}

}