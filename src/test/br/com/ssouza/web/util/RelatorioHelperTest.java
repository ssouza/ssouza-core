package br.com.ssouza.web.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.ssouza.exception.SystemException;

public class RelatorioHelperTest {

	private Connection conexao;
	private HttpServletResponse response;

	@Before
	public void onSetUp() {

		conexao = Mockito.mock(Connection.class);

		try {
			Mockito.when(conexao.isClosed()).thenReturn(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response = Mockito.mock(HttpServletResponse.class);
	}

	@Test(expected = NullPointerException.class)
	public void validarGerarPDFMapNulo() {

		RelatorioHelper.gerarPDF(response, conexao, null, "", null);
	}

	@Test(expected = NullPointerException.class)
	public void validarGerarPDFConexaoNulo() {

		RelatorioHelper.gerarPDF(response, null, new HashMap<String, Object>(), "", null);
	}

	@Test(expected = SystemException.class)
	public void validarGerarPDFCaminhoRelatorioVazio() {

		RelatorioHelper.gerarPDF(response, conexao, new HashMap<String, Object>(), "", null);
	}

}