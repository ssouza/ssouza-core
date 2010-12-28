package br.com.ssouza.validacao;

import java.util.regex.Pattern;

import br.com.ssouza.util.StringHelper;

/**
 * Classe que define a validação de um CNPJ.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class CNPJValidador {

	public static final Pattern FORMATADO = Pattern.compile("(\\d{2})[.](\\d{3})[.](\\d{3})/(\\d{4})-(\\d{2})");

	public static final Pattern NAO_FORMATADO = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");

	private CNPJValidador() {

	}

	/**
	 * Valida de acordo com as respectivas regras de uma CNPJ.
	 * 
	 * @param cnpj
	 * @param formatado
	 * @return <code>true</code> se o CNPJ for válido.<br>
	 *         <code>false</code> caso contrário.
	 */
	public static boolean isValido(String cnpj, boolean formatado) {

		if (cnpj == null) {
			return false;
		}

		boolean valido;

		if (formatado) {
			valido = FORMATADO.matcher(cnpj).matches();
		} else {
			valido = NAO_FORMATADO.matcher(cnpj).matches();
		}

		if (!valido) {
			return false;
		}

		int digito1 = 0;
		int d1 = 0;
		int digito2 = 0;
		int d2 = 0;
		int resto = 0;
		int fator = 0;

		cnpj = StringHelper.removerNaoDigitos(cnpj);

		if (cnpj.length() == 14 && !StringHelper.isSequenciaNumerica(cnpj)) {

			for (int i = 0; i < cnpj.length() - 2; i++) {

				if (i < 4) {
					fator = 5 - i;
				} else {
					fator = 13 - i;
				}

				d1 += Integer.valueOf(cnpj.substring(i, i + 1)) * fator;

				if (i < 5) {
					fator = 6 - i;
				} else {
					fator = 14 - i;
				}

				d2 += Integer.valueOf(cnpj.substring(i, i + 1)) * fator;
			}

			resto = d1 % 11;

			if (resto < 2) {
				digito1 = 0;
			} else {
				digito1 = 11 - resto;
			}
			d2 = d2 + (2 * digito1);

			resto = d2 % 11;
			if (resto < 2) {
				digito2 = 0;
			} else {
				digito2 = 11 - resto;
			}

			String dv = String.valueOf(digito1) + String.valueOf(digito2);

			if (dv.equals(cnpj.substring(cnpj.length() - 2, cnpj.length()))) {
				return true;
			}
		}

		return false;
	}

}