package br.com.ssouza.web.struts.converter;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.com.ssouza.util.DataHelper;

public class DateConverterTest {

	@Test
	public void convertDataNula() {
		Assert.assertNull(new DateConverter("").convert(Date.class, null));
		Assert.assertNull(new DateConverter("dd/MM/yyyy").convert(Date.class, null));
	}

	@Test
	public void convertDataVazia() {
		Assert.assertNull(new DateConverter("").convert(Date.class, ""));
		Assert.assertNull(new DateConverter("dd/MM/yyyy").convert(Date.class, ""));
	}

	@Test
	public void convertDataInvalida() {
		Assert.assertNull(new DateConverter("").convert(Date.class, "28-12-2010"));
		Assert.assertNull(new DateConverter("dd/MM/yyyy").convert(Date.class, "28-12-2010"));
	}

	@Test
	public void convertDataValida() {

		Date data = DataHelper.getPrimeiraHoraDoDia(28, 12, 2010).getTime();

		Assert.assertEquals(data, new DateConverter("").convert(Date.class, "28/12/2010"));
		Assert.assertEquals(data, new DateConverter("dd/MM/yyyy").convert(Date.class, "28/12/2010"));
	}

}
