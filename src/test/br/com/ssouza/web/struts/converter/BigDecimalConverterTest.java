package br.com.ssouza.web.struts.converter;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.ssouza.util.Constantes;

public class BigDecimalConverterTest {

	private BigDecimalConverter converter;

	@Before
	public void setUp() throws Exception {
		converter = new BigDecimalConverter("#,##0.00;-#,##0.00", Constantes.LOCALE_PT_BR);
	}

	@Test(expected = NullPointerException.class)
	public void convertMascaraNula() {
		new BigDecimalConverter(null, Constantes.LOCALE_PT_BR);
	}

	@Test(expected = NullPointerException.class)
	public void convertLocaleNulo() {
		new BigDecimalConverter("#,##0.00;-#,##0.00", null);
	}

	@Test
	public void convertValorNulo() {
		Assert.assertNull(converter.convert(BigDecimal.class, null));
	}

	@Test
	public void convertValorValido() {
		Assert.assertEquals(BigDecimal.TEN, converter.convert(BigDecimal.class, BigDecimal.TEN));
		Assert.assertEquals(new BigDecimal("10.0"), converter.convert(BigDecimal.class, "10,0"));
	}

}