package br.com.ssouza.web.struts.converter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.beanutils.Converter;

import br.com.ssouza.util.LogHelper;

/**
 * Classe que define a conversão de string em BigDecimal.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public class BigDecimalConverter implements Converter {

	private String mask;
	private Locale locale;

	public BigDecimalConverter(String mascara, Locale locale) {

		if (mascara == null) {
			throw new NullPointerException("A mascara não pode ser nula.");
		}

		if (locale == null) {
			throw new NullPointerException("O locale não pode ser nulo.");
		}

		this.mask = mascara;
		this.locale = locale;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object convert(Class clazz, Object object) {

		if (object == null) {
			return null;
		}

		if (object instanceof BigDecimal) {
			return object;
		}

		DecimalFormat format = new DecimalFormat(mask, new DecimalFormatSymbols(locale));
		format.setParseBigDecimal(true);

		try {
			return format.parse(String.valueOf(object));
		} catch (ParseException e) {
			LogHelper.getInstance().warn(e.getMessage());
		}
		return null;
	}
}
