package br.com.ssouza.validacao;

import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberUtils;

import br.com.ssouza.util.StringHelper;

/**
 * Classe que define a valida��o de um Telefone.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">S�rgio Souza</a>
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
	 *           ex.: "(dd)dddd-dddd" ou "(dd) dddd-dddd", onde "d" � um d�gito decimal.
	 * @return <code>true</code> se o Telefone for v�lido.<br>
	 *         <code>false</code> caso contr�rio.
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