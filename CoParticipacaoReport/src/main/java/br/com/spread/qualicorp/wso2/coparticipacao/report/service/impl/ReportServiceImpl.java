package br.com.spread.qualicorp.wso2.coparticipacao.report.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.AutomindJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.AutomindReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.ReportService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.AutomindCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.AutomindResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.util.CoParticipacaoFileUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ReportServiceImpl implements ReportService {

	private static final Logger LOGGER = LogManager.getLogger(ReportServiceImpl.class);

	private static final String CD_EMPRESA_AUTOMIND = "AUTOMIND";
	private static final String CD_EMPRESA_LM_TRANSPORTES = "073179";
	private static final String CD_EMPRESA_TECHNIT_ODONTO = "091707";
	private static final String CD_EMPRESA_TECHNIT_SAUDE = "091707";

	private static final String BRADESCO_AUTOMIND_REPORT = "reports/bradesco-automind.jasper";
	private static final String BRADESCO_LM_TRANSPORTES_REPORT = "reports/bradesco-LMTransportes.jasper";
	private static final String BRADESCO_TECHNIT_ODONTO_REPORT = "reports/bradesco-technitOdonto.jasper";
	private static final String BRADESCO_TECHNIT_SAUDE_REPORT = "reports/bradesco-technitSaude.jasper";

	@Autowired
	private AutomindResumoService automindResumoService;

	@Autowired
	private AutomindCoparticipacaoService automindCoparticipacaoService;

	@Override
	public void printReport(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			empresaUi = coParticipacaoContext.getEmpresaUi();

			LOGGER.info("Starting report for EmpresaUi[]:", empresaUi.getCdEmpresa());

			if (CD_EMPRESA_AUTOMIND.equals(empresaUi.getCdEmpresa())) {
				printReportAutomind(coParticipacaoContext, mes, ano);
			} else if (CD_EMPRESA_LM_TRANSPORTES.equals(empresaUi.getCdEmpresa())) {
				printReportAutomind(coParticipacaoContext, mes, ano);
			} else if (CD_EMPRESA_TECHNIT_ODONTO.equals(empresaUi.getCdEmpresa())) {
				printReportAutomind(coParticipacaoContext, mes, ano);
			} else if (CD_EMPRESA_TECHNIT_SAUDE.equals(empresaUi.getCdEmpresa())) {
				printReportAutomind(coParticipacaoContext, mes, ano);				
			} else {
				throw new ServiceException("EmpresaUi[] doesn't has a report configured:", empresaUi.getCdEmpresa());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private void printReportAutomind(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		JRDataSource jrDataSource;
		List<AutomindReport> automindReports;
		AutomindReport automindReport;
		InputStream inputStream;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Creating DataSource to fill the report:");
			automindReports = new ArrayList<>();
			automindReport = new AutomindReport();
			automindReport.setAutomindCoparticipacaoViewUis(automindCoparticipacaoService.listByMesAndAno(mes, ano));
			automindReport.setAutomindResumoViewUis(automindResumoService.listByMesAndAno(mes, ano));
			automindReport.setSheetNameCoparticipacao(String.format("Coparticipação %d/%d", mes, ano));
			automindReport.setSheetNameRateio("Rateio");

			automindReports.add(automindReport);

			LOGGER.info("Loading the report file[{}] to be filled with data:", BRADESCO_AUTOMIND_REPORT);
			inputStream = getClass().getResourceAsStream(BRADESCO_AUTOMIND_REPORT);

			jrDataSource = new AutomindJRDataSource(automindReports);

			fillAndExportReportToSpreadsheet(coParticipacaoContext, jrDataSource, inputStream);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private void fillAndExportReportToSpreadsheet(
			CoParticipacaoContext coParticipacaoContext,
			JRDataSource jrDataSource,
			InputStream inputStream) throws ServiceException {
		Map<String, Object> parameters;
		JasperPrint jasperPrint;
		JRXlsExporter jrXlsExporter;
		StringBuilder sb;
		EmpresaUi empresaUi;
		ContratoUi contratoUi;
		FileOutputStream fileOutputStream;
		File file;
		ArquivoExecucaoUi arquivoExecucaoUi;

		try {
			LOGGER.info("BEGIN");

			empresaUi = coParticipacaoContext.getEmpresaUi();
			contratoUi = (ContratoUi) coParticipacaoContext.getArquivoExecucaoUi().getContrato();
			arquivoExecucaoUi = coParticipacaoContext.getArquivoExecucaoUi();

			parameters = new HashMap<>();

			// Preenchendo o relatório:
			LOGGER.info("Filling the report:");
			jasperPrint = JasperFillManager.fillReport(inputStream, parameters, jrDataSource);

			LOGGER.info("Creating the OutputFile to store the report into the file system:");

			for (ArquivoOutput arquivoOutput : contratoUi.getArquivoInput().getArquivoOutputs()) {
				if (ArquivoType.SPREADSHEET.equals(arquivoOutput.getArquivoType())) {
					LOGGER.info("Building the OutputFile'name to export:");
					sb = new StringBuilder();
					sb.append(empresaUi.getOutputReportDir());
					sb.append(File.separator);
					sb.append(
							CoParticipacaoFileUtils.createFileName(
									coParticipacaoContext,
									(ArquivoOutputUi) arquivoOutput,
									contratoUi));

					file = new File(sb.toString());

					if (file.exists()) {
						file.delete();
					}

					LOGGER.info("Exporting the report as XLSX spreadsheet to [{}]:", sb.toString());
					fileOutputStream = new FileOutputStream(file);

					// Exportando para o formato de planilha:
					jrXlsExporter = new JRXlsExporter();
					jrXlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
					jrXlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileOutputStream));
					jrXlsExporter.exportReport();

					// Disponibilizando o arquivo para o usuário fazer download:
					arquivoExecucaoUi.setNameArquivoOutput(sb.toString());
					arquivoExecucaoUi.setStatusExecucaoType(StatusExecucaoType.SUCCESS);

					LOGGER.info("Exporting process done:");
					break;
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}

	}

}
