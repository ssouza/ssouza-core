package br.com.ssouza.validacao;

import java.util.regex.Pattern;

import br.com.ssouza.util.StringHelper;

/**
 * Classe que define a validação de um CEP.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class CEPValidador {

	public static final Pattern FORMATADO = Pattern.compile("(\\d{5})[-](\\d{3})");

	public static final Pattern NAO_FORMATADO = Pattern.compile("(\\d{5})(\\d{3})");

	private CEPValidador() {

	}

	/**
	 * Valida de acordo com as respectivas regras de um CEP.
	 * 
	 * @param cep
	 * @param formatado
	 * @return <code>true</code> se o CEP for válido.<br>
	 *         <code>false</code> caso contrário.
	 */
	public static boolean isValido(String cep, boolean formatado) {

		if (cep == null) {
			return false;
		}

		boolean valido;

		if (formatado) {
			valido = FORMATADO.matcher(cep).matches();
		} else {
			valido = NAO_FORMATADO.matcher(cep).matches();
		}

		if (!valido) {
			return false;
		}

		cep = StringHelper.removerNaoDigitos(cep);

		return (cep.length() == 8 && !StringHelper.isSequenciaNumerica(cep));
	}

}