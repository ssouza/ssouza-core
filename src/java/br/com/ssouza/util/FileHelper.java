package br.com.ssouza.util;

import org.apache.commons.lang.Validate;

/**
 * Classe que define métodos genéricos de arquivo.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class FileHelper {

	private static String realPath;

	public static final String FILE_SEPARATOR = getSystemProperty("file.separator");

	/**
	 * @return the realPath
	 */
	public static String getRealPath() {

		Validate.notNull(realPath, "O ContextInitializerListener.class não está definido no web.xml");

		return realPath;
	}

	/**
	 * @param realPath
	 *           the realPath to set
	 */
	public static void setRealPath(String realPath) {
		FileHelper.realPath = realPath;
	}

	/**
	 * Obtém uma propriedade do sistema.
	 * 
	 * @param property
	 * @return
	 */
	public static String getSystemProperty(String property) {
		try {
			return System.getProperty(property);
		} catch (SecurityException ex) {
			LogHelper.getInstance().warn(
					"Caught a SecurityException reading the system property '" + property
							+ "'; the SystemUtils property value will default to null.");
			return null;
		}
	}

}