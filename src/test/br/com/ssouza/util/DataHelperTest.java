package br.com.ssouza.util;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DataHelperTest {

	@Test
	public void data() {

		Calendar calendar = DataHelper.getData(1, 6, 2010);

		assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(5, calendar.get(Calendar.MONTH));
		assertEquals(2010, calendar.get(Calendar.YEAR));
	}

	@Test
	public void primeiraHoraDoDia() {

		Calendar calendar = DataHelper.getPrimeiraHoraDoDia(1, 6, 2010);

		assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(5, calendar.get(Calendar.MONTH));
		assertEquals(2010, calendar.get(Calendar.YEAR));

		assertEquals(0, calendar.get(Calendar.HOUR));
		assertEquals(0, calendar.get(Calendar.MINUTE));
		assertEquals(0, calendar.get(Calendar.SECOND));
		assertEquals(0, calendar.get(Calendar.MILLISECOND));
	}

	@Test
	public void ultimaHoraDoDia() {

		Calendar calendar = DataHelper.getUltimaHoraDoDia(1, 6, 2010);

		assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(5, calendar.get(Calendar.MONTH));
		assertEquals(2010, calendar.get(Calendar.YEAR));

		assertEquals(23, calendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(59, calendar.get(Calendar.MINUTE));
		assertEquals(59, calendar.get(Calendar.SECOND));
		assertEquals(999, calendar.get(Calendar.MILLISECOND));
	}

	@Test
	public void primeiroDiaDoMes() {

		Calendar calendar = DataHelper.getPrimeiroDiaDoMes(6, 2010);

		assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(5, calendar.get(Calendar.MONTH));
		assertEquals(2010, calendar.get(Calendar.YEAR));
	}

	@Test
	public void primeiraHoraDoPrimeiroDiaDoMes() {

		Calendar calendar = DataHelper.getPrimeiraHoraDoPrimeiroDiaDoMes(6, 2010);

		assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(5, calendar.get(Calendar.MONTH));
		assertEquals(2010, calendar.get(Calendar.YEAR));
		assertEquals(0, calendar.get(Calendar.HOUR));
		assertEquals(0, calendar.get(Calendar.MINUTE));
		assertEquals(0, calendar.get(Calendar.SECOND));
		assertEquals(0, calendar.get(Calendar.MILLISECOND));
	}

	@Test
	public void ultimaHoraDoUltimoDiaDoMes() {

		Calendar calendar = DataHelper.getUltimaHoraDoUltimoDiaDoMes(6, 2010);

		assertEquals(30, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(5, calendar.get(Calendar.MONTH));
		assertEquals(2010, calendar.get(Calendar.YEAR));
		assertEquals(23, calendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(59, calendar.get(Calendar.MINUTE));
		assertEquals(59, calendar.get(Calendar.SECOND));
		assertEquals(999, calendar.get(Calendar.MILLISECOND));
	}

	@Test
	public void ultimoDiaDoMes() {

		Calendar calendar = DataHelper.getUltimoDiaDoMes(6, 2010);

		assertEquals(30, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(5, calendar.get(Calendar.MONTH));
		assertEquals(2010, calendar.get(Calendar.YEAR));
	}

	@Test
	public void dataDeveSerIgual() {

		Calendar calendar1 = DataHelper.getData(1, 6, 2010);
		Calendar calendar2 = DataHelper.getData(1, 6, 2010);

		assertTrue(DataHelper.isIgual(calendar1, calendar2));

		calendar1 = null;
		calendar2 = null;

		assertTrue(DataHelper.isIgual(calendar1, calendar2));

		Date date1 = null, date2 = null;

		assertTrue(DataHelper.isDataIgual(date1, date2));
		assertTrue(DataHelper.isDataIgual(date2, date1));
	}

	@Test
	public void dataNaoDeveSerIgual() throws InterruptedException {

		Calendar calendar1 = DataHelper.getData(1, 6, 2010);
		Calendar calendar2 = DataHelper.getData(2, 6, 2010);

		Date date1 = new Date();

		Thread.sleep(10);

		Date date2 = new Date();

		assertFalse(DataHelper.isIgual(calendar1, calendar2));
		assertFalse(DataHelper.isIgual(calendar2, calendar1));

		assertFalse(DataHelper.isIgual(date1, date2));

		date1 = null;

		assertFalse(DataHelper.isDataIgual(date1, date2));
		assertFalse(DataHelper.isDataIgual(date2, date1));
	}

	@Test
	public void dataDeveSerMenor() {

		Calendar dataInicio = DataHelper.getData(1, 6, 2010);
		Calendar dataFim = DataHelper.getData(2, 6, 2010);

		assertTrue(DataHelper.isMenorQue(dataInicio, dataFim));
	}

	@Test
	public void dataNaoDeveSerMenor() {

		Calendar dataInicio = DataHelper.getData(1, 6, 2010);
		Calendar dataFim = DataHelper.getData(2, 6, 2010);

		assertFalse(DataHelper.isMenorQue(dataFim, dataInicio));
		assertFalse(DataHelper.isMenorQue(dataInicio, dataInicio));
	}

	@Test
	public void dataDeveSerMenorOuIgual() {

		Calendar dataInicio = DataHelper.getData(1, 6, 2010);
		Calendar dataFim = DataHelper.getData(2, 6, 2010);

		assertTrue(DataHelper.isMenorOuIgualQue(dataInicio, dataInicio));
		assertTrue(DataHelper.isMenorOuIgualQue(dataInicio, dataFim));
	}

	@Test
	public void dataNaoDeveSerMenorOuIgual() {

		Calendar dataInicio = DataHelper.getData(1, 6, 2010);
		Calendar dataFim = DataHelper.getData(2, 6, 2010);

		assertFalse(DataHelper.isMenorOuIgualQue(dataFim, dataInicio));
	}

}