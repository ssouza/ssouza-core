package br.com.ssouza.validacao;

import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberUtils;

import br.com.ssouza.util.StringHelper;

/**
 * Classe que define a validação de um Telefone.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class TelefoneValidador {

	public static final Pattern FORMATADO_1 = Pattern.compile("\\((\\d{2})\\)(\\d{4})-(\\d{4})");

	public static final Pattern FORMATADO_2 = Pattern.compile("\\((\\d{2})\\) (\\d{4})-(\\d{4})");

	private TelefoneValidador() {

	}

	/**
	 * Valida de acordo com as respectivas regras de um Telefone.
	 * 
	 * @param fone
	 *           ex.: "(dd)dddd-dddd" ou "(dd) dddd-dddd", onde "d"éum dígito decimal.
	 * @return <code>true</code> se o Telefone for válido.<br>
	 *         <code>false</code> caso contrário.
	 */
	public static boolean isValido(String fone) {

		if (fone == null) {
			return false;
		}

		if (!FORMATADO_1.matcher(fone).matches() && !FORMATADO_2.matcher(fone).matches()) {
			return false;
		}

		fone = StringHelper.removerNaoDigitos(fone);

		if (!NumberUtils.isDigits(fone)) {
			return false;
		}

		return true;
	}

}