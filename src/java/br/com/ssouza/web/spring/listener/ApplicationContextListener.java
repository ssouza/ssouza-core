package br.com.ssouza.web.spring.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.ssouza.util.LogHelper;
import br.com.ssouza.web.spring.persistence.RepositoryFactory;

/**
 * Classe que define o listener de configuração do contexto da aplicação.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public class ApplicationContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {

		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event
				.getServletContext());

		RepositoryFactory.setApplicationContext(applicationContext);

		Logger log = LogHelper.getInstance();

		if (log.isDebugEnabled()) {
			log.debug("*******************************************************************");
			log.debug("O SSouza Framework inicializou o contexto da aplicação.");
			log.debug("*******************************************************************");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

		RepositoryFactory.setApplicationContext(null);
	}

}