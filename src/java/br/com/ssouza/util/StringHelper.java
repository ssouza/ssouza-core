package br.com.ssouza.util;

/**
 * Classe que define m�todos para tratamento de strings.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">S�rgio Souza</a>
 * @version 1.0
 * @since Dec 27, 2010
 */
public final class StringHelper {

	/**
	 * Remove tudo que n�o � n�mero da string.
	 * 
	 * @param value
	 * @return
	 */
	public static String removerNaoDigitos(String value) {
		return value == null ? null : value.replaceAll("\\D", "");
	}

	/**
	 * Verifica se o valor passado � uma sequencia num�rica
	 * 
	 * @param value
	 * @return
	 * @throws NullPointerException
	 */
	public static boolean isSequenciaNumerica(String value) {

		if (value.length() < 2) {
			return false;
		}

		for (int i = 1; i < value.length(); i++) {
			if (value.charAt(i) != value.charAt(0)) {
				return false;
			}
		}

		return true;
	}

}