package br.com.ssouza.validacao;

import java.util.regex.Pattern;

import br.com.ssouza.util.StringHelper;

/**
 * Classe que define a validação de uma CPF.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class CPFValidador {

	public static final Pattern FORMATADO = Pattern.compile("(\\d{3})[.](\\d{3})[.](\\d{3})-(\\d{2})");

	public static final Pattern NAO_FORMATADO = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");

	private CPFValidador() {

	}

	/**
	 * Valida de acordo com as respectivas regras de uma CPF.
	 * 
	 * @param cpf
	 * @param formatado
	 * @return <code>true</code> se o CPF for válido.<br>
	 *         <code>false</code> caso contrário.
	 */
	public static boolean isValido(String cpf, boolean formatado) {

		if (cpf == null) {
			return false;
		}

		boolean valido;

		if (formatado) {
			valido = FORMATADO.matcher(cpf).matches();
		} else {
			valido = NAO_FORMATADO.matcher(cpf).matches();
		}

		if (!valido) {
			return false;
		}

		cpf = StringHelper.removerNaoDigitos(cpf);

		outer: if (cpf.length() == 11 && !StringHelper.isSequenciaNumerica(cpf)) {

			int soma = 0;

			for (int i = 0; i < 9; i++) {

				int digito = cpf.charAt(i) - 48;

				if (digito < 0 || digito > 9) {
					break outer;
				} else {
					soma += digito * (10 - i);
				}
			}

			soma = soma % 11;

			if (soma == 0 || soma == 1) {
				soma = 0;
			} else {
				soma = 11 - soma;
			}

			int digito1 = cpf.charAt(9) - 48;

			if (soma == digito1) {

				soma = 0;

				for (int i = 0; i < 10; i++) {
					int digito = cpf.charAt(i) - 48;
					if (digito < 0 || digito > 9)
						break outer;
					soma += digito * (11 - i);
				}
				soma = soma % 11;

				if (soma == 0 || soma == 1) {
					soma = 0;
				} else {
					soma = 11 - soma;
				}

				int digito2 = cpf.charAt(10) - 48;
				if (soma == digito2) {
					return true;
				}
			}
		}

		return false;
	}

}