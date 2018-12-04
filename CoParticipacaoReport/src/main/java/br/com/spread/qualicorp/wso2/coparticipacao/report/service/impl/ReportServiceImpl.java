package br.com.spread.qualicorp.wso2.coparticipacao.report.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.ReportService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.ReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.AutomindReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.CelpeSaudeReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.LMTransportesReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.SpreadSaudeReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.TechnitOdontoReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.TechnitSaudeReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.util.CoParticipacaoFileUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
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
	private static final String CD_EMPRESA_TECHNIT_SAUDE = "180831";
	private static final String CD_EMPRESA_CELPE_SAUDE = "071421";
	private static final String CD_EMPRESA_COELBA_ODONTO = "071421";
	private static final String CD_EMPRESA_COELBA_SAUDE = "071421";

	@Autowired
	private ArquivoExecucaoService arquivoExecucaoService;

	@Autowired
	private AutomindReportWriter automindReportWriter;

	@Autowired
	private LMTransportesReportWriter lmTransportesReportWriter;

	@Autowired
	private TechnitSaudeReportWriter technitSaudeReportWriter;

	@Autowired
	private TechnitOdontoReportWriter technitOdontoReportWriter;

	@Autowired
	private CelpeSaudeReportWriter celpeSaudeReportWriter;

	@Autowired
	private SpreadSaudeReportWriter spreadSaudeReportWriter;

	@Override
	public void printReport(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		EmpresaUi empresaUi;
		ContratoUi contratoUi;
		ReportWriter reportWriter;

		try {
			LOGGER.info("BEGIN");

			empresaUi = coParticipacaoContext.getEmpresaUi();
			contratoUi = (ContratoUi) coParticipacaoContext.getArquivoExecucaoUi().getContrato();

			LOGGER.info("Starting report for EmpresaUi[{}]:", empresaUi.getCdEmpresa());

			if (!contratoUi.getChildren().isEmpty()) {
				for (Contrato child : contratoUi.getChildren()) {
					LOGGER.info("Starting report process for ContratoUi[{}]:", child.getCdContrato());
					createReportProcess(coParticipacaoContext, mes, ano, (ContratoUi) child);
				}
			} else {
				reportWriter = getReportWriter(empresaUi.getCdEmpresa());

				printReport(coParticipacaoContext, mes, ano, reportWriter);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private ReportWriter getReportWriter(String cdEmpresa) throws ServiceException {
		ReportWriter reportWriter = null;

		try {
			LOGGER.info("BEGIN");

			if (automindReportWriter.getCdEmpresa().equals(cdEmpresa)) {
				reportWriter = automindReportWriter;
			} else if (lmTransportesReportWriter.getCdEmpresa().equals(cdEmpresa)) {
				reportWriter = lmTransportesReportWriter;
			} else if (technitOdontoReportWriter.getCdEmpresa().equals(cdEmpresa)) {
				reportWriter = technitOdontoReportWriter;
			} else if (technitSaudeReportWriter.getCdEmpresa().equals(cdEmpresa)) {
				reportWriter = technitSaudeReportWriter;
			} else if (celpeSaudeReportWriter.getCdEmpresa().equals(cdEmpresa)) {
				reportWriter = celpeSaudeReportWriter;
			} else if (CD_EMPRESA_COELBA_ODONTO.equals(cdEmpresa)) {

			} else if (CD_EMPRESA_COELBA_SAUDE.equals(cdEmpresa)) {

			} else if (spreadSaudeReportWriter.getCdEmpresa().equals(cdEmpresa)) {
				reportWriter = spreadSaudeReportWriter;
			} else {
				throw new ServiceException("EmpresaUi[%s] doesn't has a report configured:", cdEmpresa);
			}

			LOGGER.info("END");
			return reportWriter;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private void createReportProcess(
			CoParticipacaoContext coParticipacaoContext,
			Integer mes,
			Integer ano,
			ContratoUi contratoUi) throws ServiceException {
		ArquivoExecucaoUi arquivoExecucaoUi;
		ArquivoExecucaoUi arquivoExecucaoUiTmp;

		try {
			LOGGER.info("BEGIN");

			arquivoExecucaoUi = arquivoExecucaoService.createArquivoExecucao(coParticipacaoContext, contratoUi);

			arquivoExecucaoUiTmp = coParticipacaoContext.getArquivoExecucaoUi();
			coParticipacaoContext.setArquivoExecucaoUi(arquivoExecucaoUi);

			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.STARTED);
			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.RUNNING);

			/*
			 * Chamando o caminho normal para o processo como se fosse para o
			 * ContratoUi parent:
			 */
			printReport(coParticipacaoContext, mes, ano);

			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.SUCCESS);

			coParticipacaoContext.setArquivoExecucaoUi(arquivoExecucaoUiTmp);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private void printReport(
			CoParticipacaoContext coParticipacaoContext,
			Integer mes,
			Integer ano,
			ReportWriter reportWriter) throws ServiceException {
		JRDataSource jrDataSource;
		InputStream inputStream;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Creating the DataSource to fill the report:");
			jrDataSource = reportWriter.getDataSource(coParticipacaoContext, mes, ano);

			LOGGER.info("Loading the report file[{}] to be filled with data:", reportWriter.getReportFile());
			inputStream = getClass().getResourceAsStream(reportWriter.getReportFile());

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
		JasperReport jasperReport;

		try {
			LOGGER.info("BEGIN");

			if (inputStream != null) {
				empresaUi = coParticipacaoContext.getEmpresaUi();
				contratoUi = (ContratoUi) coParticipacaoContext.getArquivoExecucaoUi().getContrato();
				arquivoExecucaoUi = coParticipacaoContext.getArquivoExecucaoUi();

				parameters = new HashMap<>();

				LOGGER.info("Loading the report:");
				jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

				// Preenchendo o relatório:
				LOGGER.info("Filling the report:");
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

				LOGGER.info("Creating the OutputFile to store the report into the file system:");

				file = new File(empresaUi.getOutputReportDir());

				if (!file.exists()) {
					file.mkdirs();
				}

				for (ArquivoOutputUi arquivoOutputUi : coParticipacaoContext.getArquivoOutputUis()) {
					if (ArquivoType.SPREADSHEET.equals(arquivoOutputUi.getArquivoType())) {
						LOGGER.info("Building the OutputFile'name to export:");

						sb = new StringBuilder();
						sb.append(empresaUi.getOutputReportDir());
						sb.append(File.separator);
						sb.append(
								CoParticipacaoFileUtils.createFileName(
										coParticipacaoContext,
										(ArquivoOutputUi) arquivoOutputUi,
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

						// Disponibilizando o arquivo para o usuário fazer
						// download:
						arquivoExecucaoUi.setNameArquivoOutput(sb.toString());

						CoParticipacaoFileUtils.close(fileOutputStream);

						LOGGER.info("Exporting process done:");
						break;
					}
				}
			} else {
				throw new ServiceException("Couldn't find report file.");
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}

	}

}
