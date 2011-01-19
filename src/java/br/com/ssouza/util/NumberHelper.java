package br.com.ssouza.util;

/**
 * Classe que define métodos para tratamento de números.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 29, 2010
 */
public final class NumberHelper {

	/**
	 * Verifica se o valor do ID informadoéválido
	 * 
	 * @param id
	 * @return <code>true</code> caso contrário.<br>
	 *         <code>false</code> se o valorénulo ou zero.
	 */
	public static boolean isIdValido(Number id) {

		if (id == null) {
			return false;
		}

		if (id instanceof Long || id instanceof Integer || id instanceof Short) {
			if (id.longValue() == 0) {
				return false;
			}
		}

		return true;
	}
}