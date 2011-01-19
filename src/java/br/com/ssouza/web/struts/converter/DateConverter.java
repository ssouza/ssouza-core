package br.com.ssouza.web.struts.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

import br.com.ssouza.util.DataHelper;
import br.com.ssouza.validacao.DataValidador;

/**
 * Classe que define a conversão de string em Data.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public class DateConverter implements Converter {

	private String pattern;

	public DateConverter(String pattern) {
		this.pattern = pattern;

		if (StringUtils.isBlank(pattern)) {
			this.pattern = DataHelper.PATTERN_DD_MM_YYYY;
		}
	}

	@SuppressWarnings("unchecked")
	public Object convert(Class clazz, Object object) {

		if (object == null) {
			return null;
		}

		if (object instanceof Date) {
			return object;
		}

		String text = (String) object;

		try {
			if (!DataValidador.isValido(text)) {
				return null;
			}

			SimpleDateFormat sdf = new SimpleDateFormat(this.pattern);

			return sdf.parse(text);

		} catch (ParseException e) {
			return null;
		}
	}

}