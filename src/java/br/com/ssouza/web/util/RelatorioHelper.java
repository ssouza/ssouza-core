package br.com.ssouza.web.util;

import java.io.File;
import java.io.FileNotFoundException;
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
 * Classe que define a geração de relatórios pelo JasperReports.
 * 
 * @author <a href="mailto:sergio.lcs@gmail.com">Sérgio Souza</a>
 * @version 1.0
 * @since Dec 28, 2010
 */
public final class RelatorioHelper {

	public static void gerarPDF(HttpServletResponse response, Connection conexao, Map<String, Object> map,
			String caminhoRelatorioJasper, String nomeRelatorio) {

		if (conexao == null) {
			throw new NullPointerException("A conexão não pode ser nula.");
		}

		if (map == null) {
			throw new NullPointerException("O map não pode ser nulo.");
		}

		if (caminhoRelatorioJasper == null) {
			throw new NullPointerException("O caminho para o relatório não pode ser nulo.");
		}

		try {

			File arquivoJasper = new File(caminhoRelatorioJasper);
			if (!arquivoJasper.exists()) {
				throw new FileNotFoundException("O relatório '" + arquivoJasper.getName() + "' não foi encontrado.");
			}

			if (StringUtils.isNotBlank(nomeRelatorio)) {
				nomeRelatorio = " filename=" + nomeRelatorio + ".pdf";
			} else {
				nomeRelatorio = "";
			}

			byte[] bytes = null;

			if (conexao.isClosed()) {
				throw new SystemException("Não foi possível obter a conexão com o "
						+ "Banco de Dados para a geração do Relatório.");
			}

			map.put(JRParameter.REPORT_LOCALE, Constantes.LOCALE_PT_BR);

			bytes = JasperRunManager.runReportToPdf(arquivoJasper.getPath(), map, conexao);

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

		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

}