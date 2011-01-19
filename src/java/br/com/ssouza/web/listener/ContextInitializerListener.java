package br.com.ssouza.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import br.com.ssouza.util.LogHelper;
import br.com.ssouza.util.FileHelper;

/**
 * Classe que define o listener de configuração do real path da aplicação.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
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
			log.debug("O SSouza Framework obteve o caminho real da aplicação web:");
			log.debug(FileHelper.getRealPath());
			log.debug("*******************************************************************");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		FileHelper.setRealPath(null);
	}

}