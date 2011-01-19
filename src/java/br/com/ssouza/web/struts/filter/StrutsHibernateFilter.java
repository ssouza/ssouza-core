package br.com.ssouza.web.struts.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import br.com.ssouza.util.LogHelper;
import br.com.ssouza.web.struts.util.TransacaoHelper;

/**
 * Classe que define o filtro de transações do hibernate.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public class StrutsHibernateFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {

		try {

			TransacaoHelper.beginTransaction();

			chain.doFilter(servletRequest, servletResponse);

			if (TransacaoHelper.isActive()) {
				TransacaoHelper.commitTransaction();
			}

		} catch (Exception e) {

			if (TransacaoHelper.isActive()) {
				TransacaoHelper.rollbackTransaction();
			}

			LogHelper.getInstance().error(e.getMessage());

		} finally {
			TransacaoHelper.closeSession();
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig config) throws ServletException {

		TransacaoHelper.getSessionFactory();

		Logger log = LogHelper.getInstance();
		if (log.isDebugEnabled()) {
			log.info("*********************************************** ");
			log.info("O StrutsHibernateFilter foi inicializado pelo SSouza Framework, "
					+ "agora uma sessão será aberta/fechada para cada requisição.");
			log.info("*********************************************** ");
		}
	}

}