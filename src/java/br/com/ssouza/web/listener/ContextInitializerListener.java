/**
 * 
 */
package br.com.ssouza.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import br.com.ssouza.util.LogHelper;
import br.com.ssouza.util.FileHelper;

/**
 * Classe que define o listener de configura��o do real path da aplica��o.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">S�rgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public class ContextInitializerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {

		FileHelper.setRealPath(event.getServletContext().getRealPath("/"));

		Logger log = LogHelper.getInstance();

		if (log.isDebugEnabled()) {
			log.debug("*******************************************************************");
			log.debug("Framework S�rgio Souza, obteve o caminho real da aplica��o web:");
			log.debug(FileHelper.getRealPath());
			log.debug("*******************************************************************");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		FileHelper.setRealPath(null);
	}

}