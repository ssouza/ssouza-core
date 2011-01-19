package br.com.ssouza.validacao;

import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberUtils;

/**
 * Classe que define a validação de um Horário.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class HoraValidador {

	public static final Pattern FORMATADO_HORA_MINUTO = Pattern.compile("(\\d{2})[:](\\d{2})");

	public static final Pattern FORMATADO_HORA_MINUTO_SEGUNDO = Pattern.compile("(\\d{2})[:](\\d{2})[:](\\d{2})");

	private static final String SEPARADOR = ":";

	private HoraValidador() {

	}

	/**
	 * Valida de acordo com as respectivas regras de uma Horário.
	 * 
	 * @param horas
	 *           os formatos aceitos são "dd:dd" ou "dd:dd:dd", onde "d"éum dígito decimal.
	 * @return <code>true</code> se o Horário for válido.<br>
	 *         <code>false</code> caso contrário.
	 */
	public static boolean isValido(String horas) {

		if (horas == null) {
			return false;
		}

		int tamanho = horas.length();
		String hora = "";
		String separador1 = "";
		String separador2 = "";
		String minuto = "";
		String segundo = "";

		switch (tamanho) {
			case 5: {
				if (!FORMATADO_HORA_MINUTO.matcher(horas).matches()) {
					return false;
				}

				hora = horas.substring(0, 2);
				separador1 = horas.substring(2, 3);
				minuto = horas.substring(3, 5);

				if (!NumberUtils.isDigits(hora) || !NumberUtils.isDigits(minuto) || !separador1.equals(SEPARADOR)) {
					return false;
				} else {
					if ((Integer.valueOf(hora) < 0 || Integer.valueOf(hora) >= 24)
							|| (Integer.valueOf(minuto) < 0 || Integer.valueOf(minuto) >= 60)) {
						return false;
					}
				}
			}
				break;

			case 8: {
				if (!FORMATADO_HORA_MINUTO_SEGUNDO.matcher(horas).matches()) {
					return false;
				}

				hora = horas.substring(0, 2);
				separador1 = horas.substring(2, 3);
				minuto = horas.substring(3, 5);
				separador2 = horas.substring(5, 6);
				segundo = horas.substring(6, 8);

				if (!NumberUtils.isDigits(hora) || !NumberUtils.isDigits(minuto) || !NumberUtils.isDigits(segundo)
						|| !separador1.equals(SEPARADOR) || !separador2.equals(SEPARADOR)) {
					return false;
				} else {
					if ((Integer.valueOf(hora) < 0 || Integer.valueOf(hora) >= 24)
							|| (Integer.valueOf(minuto) < 0 || Integer.valueOf(minuto) >= 60)
							|| (Integer.valueOf(segundo) < 0 || Integer.valueOf(segundo) >= 60)) {
						return false;
					}
				}
			}
				break;
		}

		if (tamanho != 5 && tamanho != 8) {
			return false;
		}

		return true;
	}

}