package br.com.ssouza.util;

import org.apache.log4j.Logger;

/**
 * Classe que define o acesso ao Log4J.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class LogHelper {

	public static Logger getInstance() {
		return Logger.getLogger("br.com.ssouza");
	}

}