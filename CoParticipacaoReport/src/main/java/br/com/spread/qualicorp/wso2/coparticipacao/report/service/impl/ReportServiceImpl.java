package br.com.spread.qualicorp.wso2.coparticipacao.report.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitHeaderViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.AutomindJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.LMTransportesJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.TechnitOdontoJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.TechnitSaudeJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.AutomindReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.LMTransportesReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitOdontoReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitSaudeReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.ReportService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.AutomindCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.AutomindResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.LMTransportesCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.LMTransportesResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.TechnitOdonto;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.TechnitOdontoCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.TechnitSaude;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.TechnitSaudeCoparticipacaoService;
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
	private static final String CD_EMPRESA_TECHNIT_SAUDE = "091707";

	private static final String BRADESCO_AUTOMIND_REPORT = "/reports/bradesco-automind.jasper";
	private static final String BRADESCO_LM_TRANSPORTES_REPORT = "/reports/bradesco-LMTransportes.jasper";
	private static final String BRADESCO_TECHNIT_ODONTO_REPORT = "/reports/bradesco-TechnitOdonto.jasper";
	private static final String BRADESCO_TECHNIT_SAUDE_REPORT = "/reports/bradesco-TechnitSaude.jasper";

	@Autowired
	private AutomindResumoService automindResumoService;

	@Autowired
	private AutomindCoparticipacaoService automindCoparticipacaoService;

	@Autowired
	private LMTransportesResumoService lmTransportesResumoService;

	@Autowired
	private LMTransportesCoparticipacaoService lmTransportesCoparticipacaoService;

	@Autowired
	private TechnitOdontoCoparticipacaoService technitOdontoCoparticipacaoService;

	@Autowired
	private TechnitSaudeCoparticipacaoService technitSaudeCoparticipacaoService;

	@Override
	public void printReport(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			empresaUi = coParticipacaoContext.getEmpresaUi();

			LOGGER.info("Starting report for EmpresaUi[{}]:", empresaUi.getCdEmpresa());

			if (CD_EMPRESA_AUTOMIND.equals(empresaUi.getCdEmpresa())) {
				printReportAutomind(coParticipacaoContext, mes, ano);
			} else if (CD_EMPRESA_LM_TRANSPORTES.equals(empresaUi.getCdEmpresa())) {
				printReportLmTransportes(coParticipacaoContext, mes, ano);
			} else if (CD_EMPRESA_TECHNIT_ODONTO.equals(empresaUi.getCdEmpresa())) {
				printReportTechnitOdonto(coParticipacaoContext, mes, ano, TechnitOdonto.DUTRA);
				printReportTechnitOdonto(coParticipacaoContext, mes, ano, TechnitOdonto.SEDE);
			} else if (CD_EMPRESA_TECHNIT_SAUDE.equals(empresaUi.getCdEmpresa())) {
				printReportTechnitSaude(coParticipacaoContext, mes, ano, TechnitSaude.SEDE);
				printReportTechnitSaude(coParticipacaoContext, mes, ano, TechnitSaude.TENOVA);
				printReportTechnitSaude(coParticipacaoContext, mes, ano, TechnitSaude.TERNIUM);
			} else {
				throw new ServiceException("EmpresaUi[] doesn't has a report configured:", empresaUi.getCdEmpresa());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private void printReportTechnitSaude(
			CoParticipacaoContext coParticipacaoContext,
			Integer mes,
			Integer ano,
			TechnitSaude technitSaude) throws ServiceException {
		JRDataSource jrDataSource;
		List<TechnitSaudeReport> technitSaudeReports;
		TechnitSaudeReport technitSaudeReport;
		InputStream inputStream;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Creating DataSource to fill the report:");
			technitSaudeReports = new ArrayList<>();
			technitSaudeReport = new TechnitSaudeReport();
			technitSaudeReport
					.setTechnitSaudeCoparticipacaoViewUis(technitSaudeCoparticipacaoService.listByMesAndAno(mes, ano));
			technitSaudeReport.setTechnitHeaderViewUi(createTechnitHeader(coParticipacaoContext, mes, ano));
			technitSaudeReport.setSheetNameCoparticipacao("SEDE");

			technitSaudeReports.add(technitSaudeReport);

			LOGGER.info("Loading the report file[{}] to be filled with data:", BRADESCO_TECHNIT_SAUDE_REPORT);
			inputStream = getClass().getResourceAsStream(BRADESCO_TECHNIT_SAUDE_REPORT);

			jrDataSource = new TechnitSaudeJRDataSource(technitSaudeReports);

			fillAndExportReportToSpreadsheet(coParticipacaoContext, jrDataSource, inputStream);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private void printReportTechnitOdonto(
			CoParticipacaoContext coParticipacaoContext,
			Integer mes,
			Integer ano,
			TechnitOdonto technitOdonto) throws ServiceException {
		JRDataSource jrDataSource;
		List<TechnitOdontoReport> technitOdontoReports;
		TechnitOdontoReport technitOdontoReport;
		InputStream inputStream;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Creating DataSource to fill the report:");
			technitOdontoReports = new ArrayList<>();
			technitOdontoReport = new TechnitOdontoReport();
			technitOdontoReport.setTechnitOdontoCoparticipacaoViewUis(
					technitOdontoCoparticipacaoService.listByMesAndAnoAmdSubFatura(mes, ano, technitOdonto));
			technitOdontoReport.setTechnitHeaderViewUi(createTechnitHeader(coParticipacaoContext, mes, ano));
			technitOdontoReport.setSheetNameCoparticipacao("SEDE");

			technitOdontoReports.add(technitOdontoReport);

			LOGGER.info("Loading the report file[{}] to be filled with data:", BRADESCO_TECHNIT_ODONTO_REPORT);
			inputStream = getClass().getResourceAsStream(BRADESCO_TECHNIT_ODONTO_REPORT);

			jrDataSource = new TechnitOdontoJRDataSource(technitOdontoReports);

			fillAndExportReportToSpreadsheet(coParticipacaoContext, jrDataSource, inputStream);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private TechnitHeaderViewUi createTechnitHeader(
			CoParticipacaoContext coParticipacaoContext,
			Integer mes,
			Integer ano) throws ServiceException {
		TechnitHeaderViewUi technitHeaderViewUi;
		LocalDate dataCompetencia;

		try {
			LOGGER.info("BEGIN");

			dataCompetencia = LocalDate.of(ano, mes, NumberUtils.INTEGER_ONE);

			technitHeaderViewUi = new TechnitHeaderViewUi();
			technitHeaderViewUi.setTipoRegistro(NumberUtils.INTEGER_ONE);
			technitHeaderViewUi.setTipoArquivo("PARTIC.SEGURADO");
			technitHeaderViewUi.setCdContrato(coParticipacaoContext.getContratoUi().getCdContrato());
			technitHeaderViewUi.setDataCompetencia(dataCompetencia);
			technitHeaderViewUi.setDataProcessamento(LocalDate.now());

			LOGGER.info("END");
			return technitHeaderViewUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private void printReportLmTransportes(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		JRDataSource jrDataSource;
		List<LMTransportesReport> lmTransportesReports;
		LMTransportesReport lmTransportesReport;
		InputStream inputStream;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Creating DataSource to fill the report:");
			lmTransportesReports = new ArrayList<>();
			lmTransportesReport = new LMTransportesReport();
			lmTransportesReport.setLmTransportesCoparticipacaoViewUis(
					lmTransportesCoparticipacaoService.listByMesAndAno(mes, ano));
			lmTransportesReport.setLmTransportesResumoViewUis(lmTransportesResumoService.listByMesAndAno(mes, ano));
			lmTransportesReport.setSheetNameCoparticipacao(String.format("Coparticipação %d/%d", mes, ano));
			lmTransportesReport.setSheetNameRateio("Rateio");

			lmTransportesReports.add(lmTransportesReport);

			LOGGER.info("Loading the report file[{}] to be filled with data:", BRADESCO_LM_TRANSPORTES_REPORT);
			inputStream = getClass().getResourceAsStream(BRADESCO_LM_TRANSPORTES_REPORT);

			jrDataSource = new LMTransportesJRDataSource(lmTransportesReports);

			fillAndExportReportToSpreadsheet(coParticipacaoContext, jrDataSource, inputStream);

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
						arquivoExecucaoUi.setStatusExecucaoType(StatusExecucaoType.SUCCESS);

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
