package br.com.ssouza.util;

import java.io.File;
import java.util.Locale;

/**
 * Classe que define diversas constantes.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 29, 2010
 */
public final class Constantes {

	private Constantes() {

	}

	/**
	 * @return ISO-8859-1
	 */
	public static final String CHARSET_ISO = "ISO-8859-1";
	/**
	 * @return UTF-8
	 */
	public static final String CHARSET_UTF = "UTF-8";
	/**
	 * @return conf/hibernate.cfg.xml
	 */
	public static final String FILE_HIBERNATE_CONFIG = "conf" + File.separator + "hibernate.cfg.xml";
	/**
	 * @return text/html
	 */
	public static final String CONTENT_TYPE_HTML = "text/html";
	/**
	 * @return text/plain
	 */
	public static final String CONTENT_TYPE_TEXT = "text/plain";
	/**
	 * @return 1
	 */
	public static final String ATIVO = "1";
	/**
	 * @return 0
	 */
	public static final String INATIVO = "0";
	/**
	 * @return N
	 */
	public static final String NAO = "N";
	/**
	 * @return S
	 */
	public static final String SIM = "S";
	/**
	 * @return true
	 */
	public static final String TRUE = "true";
	/**
	 * @return false
	 */
	public static final String FALSE = "false";
	/**
	 * @return pt_BR
	 */
	public static final Locale LOCALE_PT_BR = new Locale("pt", "BR");

}