package br.com.ssouza.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe que define métodos genéricos de Date.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class DataHelper {

	/**
	 * @return EEEE
	 */
	public static final String PATTERN_EEEE = "EEEE";
	/**
	 * @return MMMM
	 */
	public static final String PATTERN_MMMM = "MMMM";
	/**
	 * @return dd/MM
	 */
	public static final String PATTERN_DD_MM = "dd/MM";
	/**
	 * @return dd/MM/yyyy
	 */
	public static final String PATTERN_DD_MM_YYYY = "dd/MM/yyyy";
	/**
	 * @return dd/MM/yyyy kk:mm:ss
	 */
	public static final String PATTERN_DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	/**
	 * @return kk:mm
	 */
	public static final String PATTERN_HH_MM = "HH:mm";
	/**
	 * @return kk:mm:ss
	 */
	public static final String PATTERN_HH_MM_SS = "HH:mm:ss";
	/**
	 * @return yyyy-MM-dd
	 */
	public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
	/**
	 * @return yyyy-MM-dd kk:mm:ss
	 */
	public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Obtém a data atual.
	 * 
	 * @return Calendar
	 */
	public static Calendar getDataAtual() {
		return Calendar.getInstance(Constantes.LOCALE_PT_BR);
	}

	/**
	 * Define a data atual de mês
	 * 
	 * @param dia
	 * @param mes
	 *           <code>1</code> Janeiro<br>
	 *           <code>2</code> Fevereiro<br>
	 *           <code>...</code> <br>
	 *           <code>12</code> Dezembro<br>
	 * @param ano
	 * 
	 * @return Calendar
	 */
	public static Calendar getDataAtualDoMes(int mes, int ano) {

		Calendar calendar = getDataAtual();

		calendar.set(Calendar.MONTH, mes - 1);
		calendar.set(Calendar.YEAR, ano);

		return calendar;
	}

	/**
	 * Define uma data específica
	 * 
	 * @param mes
	 *           <code>1</code> Janeiro<br>
	 *           <code>2</code> Fevereiro<br>
	 *           <code>...</code> <br>
	 *           <code>12</code> Dezembro<br>
	 * @param ano
	 * 
	 * @return Calendar
	 */
	public static Calendar getData(int dia, int mes, int ano) {

		Calendar calendar = getDataAtual();

		calendar.set(Calendar.DAY_OF_MONTH, dia);
		calendar.set(Calendar.MONTH, mes - 1);
		calendar.set(Calendar.YEAR, ano);

		return calendar;
	}

	/**
	 * Obtém a primeira hora do dia.
	 * 
	 * @param calendar
	 * 
	 * @return Calendar
	 */
	public static Calendar getPrimeiraHoraDoDia(Calendar calendar) {

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar;
	}

	/**
	 * Obtém a primeira hora do dia.
	 * 
	 * @param dia
	 * @param mes
	 *           <code>1</code> Janeiro<br>
	 *           <code>2</code> Fevereiro<br>
	 *           <code>...</code> <br>
	 *           <code>12</code> Dezembro<br>
	 * @param ano
	 * 
	 * @return Calendar
	 */
	public static Calendar getPrimeiraHoraDoDia(int dia, int mes, int ano) {

		Calendar calendar = getData(dia, mes, ano);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar;
	}

	/**
	 * Obtém a última hora do dia.
	 * 
	 * @param dia
	 * @param mes
	 *           <code>1</code> Janeiro<br>
	 *           <code>2</code> Fevereiro<br>
	 *           <code>...</code> <br>
	 *           <code>12</code> Dezembro<br>
	 * @param ano
	 * 
	 * @return Calendar
	 */
	public static Calendar getUltimaHoraDoDia(int dia, int mes, int ano) {

		Calendar calendar = getData(dia, mes, ano);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar;
	}

	/**
	 * Obtém a última hora do dia.
	 * 
	 * @param calendar
	 * 
	 * @return Calendar
	 */
	public static Calendar getUltimaHoraDoDia(Calendar calendar) {

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar;
	}

	/**
	 * Obtém o primeiro dia do mês e ano.
	 * 
	 * @param mes
	 *           <code>1</code> Janeiro<br>
	 *           <code>2</code> Fevereiro<br>
	 *           <code>...</code> <br>
	 *           <code>12</code> Dezembro<br>
	 * @param ano
	 * @return Calendar
	 */
	public static Calendar getPrimeiroDiaDoMes(int mes, int ano) {
		return getData(1, mes, ano);
	}

	/**
	 * Obtém o primeiro dia do mês e ano.
	 * 
	 * @param mes
	 *           <code>1</code> Janeiro<br>
	 *           <code>2</code> Fevereiro<br>
	 *           <code>...</code> <br>
	 *           <code>12</code> Dezembro<br>
	 * @param ano
	 * @return Calendar
	 */
	public static Calendar getPrimeiraHoraDoPrimeiroDiaDoMes(int mes, int ano) {

		Calendar calendar = getData(1, mes, ano);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar;
	}

	/**
	 * Obtém o último dia do mês e ano
	 * 
	 * @param mes
	 *           <code>1</code> Janeiro<br>
	 *           <code>2</code> Fevereiro<br>
	 *           <code>...</code> <br>
	 *           <code>12</code> Dezembro<br>
	 * @param ano
	 * @return Calendar
	 */
	public static Calendar getUltimoDiaDoMes(int mes, int ano) {

		Calendar calendar = getData(1, mes, ano);

		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		return calendar;
	}

	/**
	 * Obtém o último dia do mês e ano
	 * 
	 * @param mes
	 *           <code>1</code> Janeiro<br>
	 *           <code>2</code> Fevereiro<br>
	 *           <code>...</code> <br>
	 *           <code>12</code> Dezembro<br>
	 * @param ano
	 * @return Calendar
	 */
	public static Calendar getUltimaHoraDoUltimoDiaDoMes(int mes, int ano) {

		Calendar calendar = getData(1, mes, ano);

		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar;
	}

	/**
	 * Verifica se duas datas são iguais.
	 * 
	 * @param data1
	 * @param data2
	 * @return <code>true</code> se as datas forem iguais.<br>
	 *         <code>false</code> caso contrário.
	 */
	public static boolean isIgual(Calendar calendar1, Calendar calendar2) {

		if (calendar1 == null && calendar2 == null) {

			return true;

		} else if (calendar1 != null && calendar2 != null) {

			return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
					&& calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
					&& calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
					&& calendar1.get(Calendar.HOUR_OF_DAY) == calendar2.get(Calendar.HOUR_OF_DAY)
					&& calendar1.get(Calendar.MINUTE) == calendar2.get(Calendar.MINUTE)
					&& calendar1.get(Calendar.SECOND) == calendar2.get(Calendar.SECOND)
					&& calendar1.get(Calendar.MILLISECOND) == calendar2.get(Calendar.MILLISECOND);
		}

		return false;
	}

	/**
	 * Verifica se duas datas são iguais.
	 * 
	 * @param data1
	 * @param data2
	 * @return <code>true</code> se as datas forem iguais.<br>
	 *         <code>false</code> caso contrário.
	 */
	public static boolean isIgual(Date date1, Date date2) {

		if (date1 == null && date2 == null) {

			return true;

		} else if (date1 != null && date2 != null) {

			Calendar calendar1 = DataHelper.getDataAtual(), calendar2 = DataHelper.getDataAtual();

			calendar1.setTime((Date) date1);
			calendar2.setTime((Date) date2);

			return isIgual(calendar1, calendar2);
		}

		return false;
	}

	/**
	 * Verifica se duas datas são iguais.
	 * 
	 * @param data1
	 * @param data2
	 * @return <code>true</code> se as datas forem iguais.<br>
	 *         <code>false</code> caso contrário.
	 */
	public static boolean isDataIgual(Calendar calendar1, Calendar calendar2) {

		if (calendar1 == null && calendar2 == null) {

			return true;

		} else if (calendar1 != null && calendar2 != null) {

			return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
					&& calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
					&& calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
		}

		return false;
	}

	/**
	 * Verifica se duas datas são iguais.
	 * 
	 * @param data1
	 * @param data2
	 * @return <code>true</code> se as datas forem iguais.<br>
	 *         <code>false</code> caso contrário.
	 */
	public static boolean isDataIgual(Date date1, Date date2) {

		if (date1 == null && date2 == null) {

			return true;

		} else if (date1 != null && date2 != null) {

			Calendar calendar1 = DataHelper.getDataAtual(), calendar2 = DataHelper.getDataAtual();

			calendar1.setTime((Date) date1);
			calendar2.setTime((Date) date2);

			return isDataIgual(calendar1, calendar2);
		}

		return false;
	}

	/**
	 * Verifica se a data de início é menor que a data fim.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return <code>true</code> se a data de início é menor que a data fim.<br>
	 *         <code>false</code> se o valor é nulo ou zero.
	 */
	public static boolean isMenorQue(Calendar dataInicio, Calendar dataFim) {
		return dataInicio.before(dataFim);
	}

	/**
	 * Verifica se a data de início é menor ou igual que a data fim.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return <code>true</code> se a data de início é menor ou igual que a data fim.<br>
	 *         <code>false</code> se o valor é nulo ou zero.
	 */
	public static boolean isMenorOuIgualQue(Calendar dataInicio, Calendar dataFim) {
		return !dataFim.before(dataInicio);
	}

}