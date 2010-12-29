package br.com.ssouza.hibernate.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.junit.Assert;
import org.junit.Test;

public class HibernateHelperTest {

	@Test
	public void validarGetEntityNulo() {
		Assert.assertNull(HibernateHelper.getEntity(null));
	}

	@Test
	public void validarGetEntity() {

		HibernateProxy proxy = mock(HibernateProxy.class);

		LazyInitializer initializer = mock(LazyInitializer.class);

		Object object = mock(Object.class);

		when(proxy.getHibernateLazyInitializer()).thenReturn(initializer);

		when(initializer.getImplementation()).thenReturn(object);

		Assert.assertNotNull(HibernateHelper.getEntity(proxy));
	}

}