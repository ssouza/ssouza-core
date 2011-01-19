package br.com.ssouza.hibernate.util;

import org.hibernate.proxy.HibernateProxy;

/**
 * Classe que define diversos métodos genéricos para o Hibernate.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 29, 2010
 */
public final class HibernateHelper {

	/**
	 * Obtém um objeto que estava Enhacer.
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