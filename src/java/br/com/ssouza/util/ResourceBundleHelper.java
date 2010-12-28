package br.com.ssouza.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import br.com.ssouza.exception.SystemException;

/**
 * Classe que define acesso a um arquivo .PROPERTIES, mesmo que esteja dentro de um arquivo .JAR.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public class ResourceBundleHelper {

	private ResourceBundle resourceBundle;

	/**
	 * Construtor
	 * 
	 * @param baseName
	 *           - ex.: "resources.arquivoProperties", que está localizado dentro do
	 *           CLASS_PATH\resources\arquivoProperties.properties
	 */
	public ResourceBundleHelper(String baseName) {
		resourceBundle = ResourceBundle.getBundle(baseName);
	}

	/**
	 * Obtém uma mensagem.
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {

		String message = "";

		try {
			message = resourceBundle.getString(key);

			if (message != null) {
				message = message.trim();
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}

		return message;
	}

	public int getInt(String key) {

		String message = getString(key);

		if (NumberUtils.isDigits(message)) {
			return Integer.valueOf(message);
		}

		return 0;
	}

	/**
	 * Obtém uma mensagem, setando uma valores nela.
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public String getString(String key, Object[] params) {
		String message = getString(key);

		if (StringUtils.isNotBlank(message) && !ArrayUtils.isEmpty(params)) {
			message = MessageFormat.format(message, params);
		}

		return message;
	}

	/**
	 * Obtém um valor boleano.
	 * 
	 * @param key
	 * @return <code>true</code><br>
	 *         <code>false</code>
	 */
	public boolean getBoolean(String key) {
		return Constantes.TRUE.equalsIgnoreCase(getString(key));
	}

}