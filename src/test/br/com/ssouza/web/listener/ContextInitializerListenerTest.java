package br.com.ssouza.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.ssouza.util.FileHelper;

public class ContextInitializerListenerTest {

	ServletContextEvent contextEvent;

	@Before
	public void setUp() throws Exception {

		contextEvent = Mockito.mock(ServletContextEvent.class);

		ServletContext servletContext = Mockito.mock(ServletContext.class);

		Mockito.when(contextEvent.getServletContext()).thenReturn(servletContext);

		Mockito.when(servletContext.getRealPath("/")).thenReturn("/home/ssouza/");

		new ContextInitializerListener().contextInitialized(contextEvent);
	}

	@Test
	public void validarCaminhoReal() {
		Assert.assertEquals("/home/ssouza/", FileHelper.getRealPath());
	}

}