package br.com.ssouza.web.util;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.commons.lang.StringUtils;

import br.com.ssouza.exception.SystemException;
import br.com.ssouza.util.Constantes;

/**
 * Classe que define a gera��o de relat�rios pelo JasperReports.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">S�rgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class RelatorioHelper {

	public static void gerarPDF(HttpServletResponse response, Connection conexao, Map<String, Object> map,
			String caminhoRelatorio, String nomeRelatorio) {

		if (conexao == null) {
			throw new NullPointerException("A conex�o n�o pode ser nula.");
		}

		if (map == null) {
			throw new NullPointerException("O map n�o pode ser nulo.");
		}

		if (caminhoRelatorio == null) {
			throw new NullPointerException("O caminho para o relat�rio n�o pode ser nulo.");
		}

		try {

			File file = new File(caminhoRelatorio);
			if (!file.exists()) {
				throw new SystemException("O relat�rio '" + file.getName() + "' n�o foi encontrado.");
			}

			if (StringUtils.isNotBlank(nomeRelatorio)) {
				nomeRelatorio = " filename=" + nomeRelatorio + ".pdf";
			} else {
				nomeRelatorio = "";
			}

			byte[] bytes = null;

			if (!conexao.isClosed()) {

				map.put(JRParameter.REPORT_LOCALE, Constantes.LOCALE_PT_BR);

				bytes = JasperRunManager.runReportToPdf(file.getPath(), map, conexao);

				response.setContentType("application/pdf");
				response.setHeader("Pragma", "");
				response.setHeader("Cache-Control", "");
				response.setHeader("Expires", "");
				response.setHeader("Content-Disposition", "inline;" + nomeRelatorio);
				response.setContentLength(bytes.length);

				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
				ouputStream.close();

			} else {
				throw new SystemException("N�o foi poss�vel obter a conex�o com o "
						+ "Banco de Dados para a gera��o do Relat�rio.");
			}

		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

}