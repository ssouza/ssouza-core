package br.com.ssouza.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * Classe que define métodos genéricos de conversão de tipos.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class ParseHelper {

	/**
	 * Método que efetua a conversão de Date em String.
	 * 
	 * @param data
	 * @param padrao
	 * @return String
	 */
	public static String dateToString(Date data, String padrao) {

		if (data != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(padrao, Constantes.LOCALE_PT_BR);
			return simpleDateFormat.format(data);
		} else {
			return "";
		}
	}

	/**
	 * Método que efetua a conversão de String em Date.
	 * 
	 * @param data
	 * @param padrao
	 * @return Date
	 * @throws ParseException
	 */
	public static Date stringToDate(String data, String padrao) {

		Date date = null;

		if (StringUtils.isNotBlank(data)) {

			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(padrao, Constantes.LOCALE_PT_BR);
				date = simpleDateFormat.parse(data);
			} catch (ParseException e) {
				LogHelper.getInstance().warn(e.getMessage());
			}
		}

		return date;
	}

	public static String bigDecimalToString(BigDecimal valor) {

		DecimalFormat f = (DecimalFormat) DecimalFormat.getInstance(Constantes.LOCALE_PT_BR);
		f.applyPattern("#,##0.00;-#,##0.00");

		return f.format(valor);
	}

}