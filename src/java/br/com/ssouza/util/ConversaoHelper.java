package br.com.ssouza.util;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;

import br.com.ssouza.web.struts.converter.BigDecimalConverter;
import br.com.ssouza.web.struts.converter.DateConverter;

/**
 * Classe que define métodos de registo de conversão para o Struts.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 29, 2010
 */
public final class ConversaoHelper {
	/**
	 * Conversão para o tipo Big Decimal
	 */
	public static void configureBigDecimal() {
		ConvertUtils.register(new BigDecimalConverter("#,##0.00;-#,##0.00", Constantes.LOCALE_PT_BR), BigDecimal.class);
	}

	/**
	 * Conversão para o tipo Date
	 */
	public static void configureDate(String pattern) {
		ConvertUtils.register(new DateConverter(pattern), Date.class);
	}

	/**
	 * Conversão para o tipo Integer
	 */
	public static void configureInteger() {
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
	}

	/**
	 * Conversão para o tipo Long
	 */
	public static void configureLong() {
		ConvertUtils.register(new LongConverter(null), Long.class);
	}

	/**
	 * Conversão para o tipo Short
	 */
	public static void configureShort() {
		ConvertUtils.register(new ShortConverter(null), Short.class);
	}

}