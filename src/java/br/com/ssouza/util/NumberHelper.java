package br.com.ssouza.util;

/**
 * Classe que define m�todos para tratamento de n�meros.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">S�rgio Souza</a>
 * @version 1.0
 * @since Dec 29, 2010
 */
public final class NumberHelper {

	/**
	 * Verifica se o valor do ID informado � v�lido
	 * 
	 * @param id
	 * @return <code>true</code> caso contr�rio.<br>
	 *         <code>false</code> se o valor � nulo ou zero.
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