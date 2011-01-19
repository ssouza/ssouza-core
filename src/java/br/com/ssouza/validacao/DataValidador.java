package br.com.ssouza.validacao;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import br.com.ssouza.util.ParseHelper;

/**
 * Classe que define a validação de uma Data.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class DataValidador {

	private static final String SEPARADOR = "/";

	public static final String PADRAO = "dd/MM/yyyy";

	private DataValidador() {

	}

	/**
	 * Valida de acordo com as respectivas regras de uma Data.
	 * 
	 * @param data
	 *           formato aceitoé"dd/MM/yyyy". E ano maior que 1900.
	 * @return <code>true</code> se a Data for válido.<br>
	 *         <code>false</code> caso contrário.
	 */
	public static boolean isValido(String data) {

		if (data == null) {
			return false;
		}

		if (StringUtils.isBlank(data) || data.length() != 10) {
			return false;
		} else {
			if (!NumberUtils.isDigits(data.replaceAll(SEPARADOR, ""))) {
				return false;
			}
		}

		ParseHelper.stringToDate(data, PADRAO);

		String[] array = data.split(SEPARADOR);

		int dia = Integer.valueOf(array[0]);
		int mes = Integer.valueOf(array[1]);
		int ano = Integer.valueOf(array[2]);

		if (mes > 0 && mes <= 12 && ano >= 1900) {
			switch (mes) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12: {
					if (!(dia > 0 && dia <= 31)) {
						return false;
					}

				}
					break;

				case 4:
				case 6:
				case 9:
				case 11: {
					if (!(dia > 0 && dia <= 30)) {
						return false;
					}

				}
					break;

				case 2: {
					int bissexto = 0;
					if (ano % 4 == 0 || ano % 100 == 0 || ano % 400 == 0) {
						bissexto = 1;
					}
					if (bissexto == 1 && !(dia > 0 && dia <= 29)) {
						return false;
					}
					if (bissexto == 0 && !(dia > 0 && dia <= 28)) {
						return false;
					}

				}
					break;
			}
		} else {
			return false;
		}

		return true;
	}

}