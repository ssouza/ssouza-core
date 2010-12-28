package br.com.ssouza.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

public class ParseHelperTest {

	@Test
	public void dateToString() {

		Calendar calendar = DataHelper.getData(10, 6, 2010);

		String data = ParseHelper.dateToString(calendar.getTime(), DataHelper.PATTERN_DD_MM_YYYY);

		assertEquals("10", data.substring(0, 2));
		assertEquals("06", data.substring(3, 5));
		assertEquals("2010", data.substring(6));
	}

	@Test
	public void datetimeToString() {

		Calendar calendar1 = DataHelper.getData(10, 6, 2010);

		calendar1 = DataHelper.getPrimeiraHoraDoDia(calendar1);

		String data1 = ParseHelper.dateToString(calendar1.getTime(), DataHelper.PATTERN_DD_MM_YYYY_HH_MM_SS);

		assertEquals("10", data1.substring(0, 2));
		assertEquals("06", data1.substring(3, 5));
		assertEquals("2010", data1.substring(6, 10));
		assertEquals("00", data1.substring(11, 13));
		assertEquals("00", data1.substring(14, 16));
		assertEquals("00", data1.substring(17, 19));

		Calendar calendar2 = DataHelper.getData(10, 6, 2010);

		calendar2 = DataHelper.getUltimaHoraDoDia(calendar2);

		String data2 = ParseHelper.dateToString(calendar2.getTime(), DataHelper.PATTERN_DD_MM_YYYY_HH_MM_SS);

		assertEquals("10", data2.substring(0, 2));
		assertEquals("06", data2.substring(3, 5));
		assertEquals("2010", data2.substring(6, 10));
		assertEquals("23", data2.substring(11, 13));
		assertEquals("59", data2.substring(14, 16));
		assertEquals("59", data2.substring(17, 19));
	}

	@Test
	public void stringToDate() {

		Calendar calendar = DataHelper.getDataAtual();

		calendar.setTime(ParseHelper.stringToDate("10/06/2010", DataHelper.PATTERN_DD_MM_YYYY));

		assertEquals(10, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(5, calendar.get(Calendar.MONTH));
		assertEquals(2010, calendar.get(Calendar.YEAR));
	}

	@Test
	public void bigDecimalToString() {

		String valor = ParseHelper.bigDecimalToString(new BigDecimal(1234.56));

		assertEquals("1.234", valor.split(",")[0]);
		assertEquals("56", valor.split(",")[1]);
	}

}