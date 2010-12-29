package br.com.ssouza.util;

import org.hibernate.proxy.HibernateProxy;

/**
 * Classe que define diversos m�todos gen�ricos para o Hibernate.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">S�rgio Souza</a>
 * @version 1.0
 * @since Dec 29, 2010
 */
public final class HibernateHelper {

	/**
	 * Obt�m um objeto que estava Enhacer.
	 * 
	 * @param object
	 * @return
	 */
	public static Object getEntity(Object object) {

		if (object instanceof HibernateProxy) {
			HibernateProxy proxy = (HibernateProxy) object;
			return proxy.getHibernateLazyInitializer().getImplementation();
		}

		return object;
	}

}