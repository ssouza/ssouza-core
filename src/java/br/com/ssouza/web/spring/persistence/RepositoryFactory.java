package br.com.ssouza.web.spring.persistence;

import org.springframework.context.ApplicationContext;

public class RepositoryFactory {

	protected static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		RepositoryFactory.applicationContext = applicationContext;
	}

}
