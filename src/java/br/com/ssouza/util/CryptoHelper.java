package br.com.ssouza.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

import br.com.ssouza.exception.SystemException;

public class CryptoHelper {

	private static final String ENCODING_DEFAULT = "UTF-8";
	private String encoding;

	private CryptoHelper() {

	}

	public static CryptoHelper getInstance() {
		return new CryptoHelper().withEncoding(ENCODING_DEFAULT);
	}

	public static CryptoHelper getInstance(String encoding) {

		if (StringUtils.isBlank(encoding)) {
			throw new IllegalArgumentException("O encoding informado não pode ser nulo ou vazio");
		}

		return new CryptoHelper().withEncoding(encoding);
	}

	private CryptoHelper withEncoding(String encoding) {
		this.encoding = encoding;
		return this;
	}

	private String getEncoding() {
		return this.encoding;
	}

	/**
	 * Método que criptografa um valor com BCrypt
	 * 
	 * @param plainText
	 * @return hash
	 * @throw {@link IllegalArgumentException}
	 */
	protected String encrypt(String plainText) {

		if (StringUtils.isBlank(plainText)) {
			throw new IllegalArgumentException("O plainText informado não pode ser nulo ou vazio");
		}

		String salt = BCrypt.gensalt();

		return BCrypt.hashpw(plainText, salt);
	}

	/**
	 * Método que valida se uma valor é igual a outro criptografado com MD5
	 * 
	 * @param plainText
	 * @param hashed
	 * @return true - if equals
	 * @throw {@link IllegalArgumentException}
	 */
	protected boolean check(String plainText, String hashed) {

		if (StringUtils.isBlank(plainText)) {
			throw new IllegalArgumentException("O plainText informado não pode ser nulo ou vazio");
		}

		if (StringUtils.isBlank(hashed)) {
			throw new IllegalArgumentException("O hashed informado não pode ser nulo ou vazio");
		}
		return BCrypt.checkpw(plainText, hashed);
	}

	/**
	 * Método que criptografa um valor com MD5
	 * 
	 * @param plainText
	 * @return hash
	 * @throw {@link IllegalArgumentException}
	 */
	protected String encryptWithMD5(String plainText) {

		if (StringUtils.isBlank(plainText)) {
			throw new IllegalArgumentException("O plainText informado não pode ser nulo ou vazio");
		}

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(plainText.getBytes(getEncoding())));
			return hash.toString(16);
		} catch (Exception e) {
			throw new SystemException("Ocorreu um erro na tentativa de criptografar um plainText em MD5", e);
		}
	}

	/**
	 * Método que valida se uma valor é igual a outro criptografado com MD5
	 * 
	 * @param plainText
	 * @param hashed
	 * @return true - if equals
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throw {@link IllegalArgumentException}
	 */
	protected boolean checkWithMD5(String plainText, String hashed) {

		if (StringUtils.isBlank(plainText)) {
			throw new IllegalArgumentException("O plainText informado não pode ser nulo ou vazio");
		}

		if (StringUtils.isBlank(hashed)) {
			throw new IllegalArgumentException("O hashed informado não pode ser nulo ou vazio");
		}
		return hashed.equals(encryptWithMD5(plainText));
	}

	/**
	 * Método que criptografa um valor com SHA-1
	 * 
	 * @param plainText
	 * @return hash
	 * @throw {@link IllegalArgumentException}
	 */
	protected String encryptWithSHA1(String plainText) {

		if (StringUtils.isBlank(plainText)) {
			throw new IllegalArgumentException("O plainText informado não pode ser nulo ou vazio");
		}

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			BigInteger hash = new BigInteger(1, md.digest(plainText.getBytes(getEncoding())));
			return hash.toString(16);
		} catch (Exception e) {
			throw new SystemException("Ocorreu um erro na tentativa de criptografar um plainText em SHA-1", e);
		}
	}

	/**
	 * Método que valida se uma valor é igual a outro criptografado com SHA-1
	 * 
	 * @param plainText
	 * @param hashed
	 * @return true - if equals
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throw {@link IllegalArgumentException}
	 */
	protected boolean checkWithSHA1(String plainText, String hashed) {

		if (StringUtils.isBlank(plainText)) {
			throw new IllegalArgumentException("O plainText informado não pode ser nulo ou vazio");
		}

		if (StringUtils.isBlank(hashed)) {
			throw new IllegalArgumentException("O hashed informado não pode ser nulo ou vazio");
		}
		return hashed.equals(encryptWithSHA1(plainText));
	}

	/**
	 * Método que criptografa um valor com SHA-256
	 * 
	 * @param plainText
	 * @return hash
	 * @throw {@link IllegalArgumentException}
	 */
	protected String encryptWithSHA256(String plainText) {

		if (StringUtils.isBlank(plainText)) {
			throw new IllegalArgumentException("O plainText informado não pode ser nulo ou vazio");
		}

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			BigInteger hash = new BigInteger(1, md.digest(plainText.getBytes(getEncoding())));
			return hash.toString(16);
		} catch (Exception e) {
			throw new SystemException("Ocorreu um erro na tentativa de criptografar um plainText em SHA-256", e);
		}
	}

	/**
	 * Método que valida se uma valor é igual a outro criptografado com SHA-256
	 * 
	 * @param plainText
	 * @param hashed
	 * @return true - if equals
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throw {@link IllegalArgumentException}
	 */
	protected boolean checkWithSHA256(String plainText, String hashed) {

		if (StringUtils.isBlank(plainText)) {
			throw new IllegalArgumentException("O plainText informado não pode ser nulo ou vazio");
		}

		if (StringUtils.isBlank(hashed)) {
			throw new IllegalArgumentException("O hashed informado não pode ser nulo ou vazio");
		}
		return hashed.equals(encryptWithSHA256(plainText));
	}

	/**
	 * Método que criptografa um valor com SHA-512
	 * 
	 * @param plainText
	 * @return hash
	 * @throw {@link IllegalArgumentException}
	 */
	protected String encryptWithSHA512(String plainText) {

		if (StringUtils.isBlank(plainText)) {
			throw new IllegalArgumentException("O plainText informado não pode ser nulo ou vazio");
		}

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			BigInteger hash = new BigInteger(1, md.digest(plainText.getBytes(getEncoding())));
			return hash.toString(16);
		} catch (Exception e) {
			throw new SystemException("Ocorreu um erro na tentativa de criptografar um plainText em SHA-512", e);
		}
	}

	/**
	 * Método que valida se uma valor é igual a outro criptografado com SHA-512
	 * 
	 * @param plainText
	 * @param hashed
	 * @return true - if equals
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throw {@link IllegalArgumentException}
	 */
	protected boolean checkWithSHA512(String plainText, String hashed) {

		if (StringUtils.isBlank(plainText)) {
			throw new IllegalArgumentException("O plainText informado não pode ser nulo ou vazio");
		}

		if (StringUtils.isBlank(hashed)) {
			throw new IllegalArgumentException("O hashed informado não pode ser nulo ou vazio");
		}
		return hashed.equals(encryptWithSHA512(plainText));
	}

	// protected void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	//
	// String senha = "01023";
	// String hashed = encrypt(senha);
	// System.out.println(hashed);
	// System.out.println(check(senha, hashed));
	//
	// MessageDigest md = MessageDigest.getInstance("MD5");
	// BigInteger hash = new BigInteger(1, md.digest(senha.getBytes(getEncoding())));
	// String s2 = hash.toString(16);
	// System.out.println(s2);
	//
	// md = MessageDigest.getInstance("MD2");
	// hash = new BigInteger(1, md.digest(senha.getBytes(getEncoding())));
	// s2 = hash.toString(16);
	// System.out.println(s2);
	//
	// md = MessageDigest.getInstance("SHA-1");
	// hash = new BigInteger(1, md.digest(senha.getBytes(getEncoding())));
	// s2 = hash.toString(16);
	// System.out.println(s2);
	//
	// md = MessageDigest.getInstance("SHA-256");
	// hash = new BigInteger(1, md.digest(senha.getBytes(getEncoding())));
	// s2 = hash.toString(16);
	// System.out.println(s2);
	//
	// md = MessageDigest.getInstance("SHA-384");
	// hash = new BigInteger(1, md.digest(senha.getBytes(getEncoding())));
	// s2 = hash.toString(16);
	// System.out.println(s2);
	//
	// md = MessageDigest.getInstance("SHA-512");
	// hash = new BigInteger(1, md.digest(senha.getBytes(getEncoding())));
	// s2 = hash.toString(16);
	// System.out.println(s2);
	//
	// }

}