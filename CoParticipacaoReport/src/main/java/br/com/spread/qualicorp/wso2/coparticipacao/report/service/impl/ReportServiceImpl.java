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
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitHeaderViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.AutomindJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.CelpeSaudeJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.LMTransportesJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.TechnitOdontoJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.TechnitSaudeJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.AutomindReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.CelpeSaudeReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.LMTransportesReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitOdontoReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitSaudeReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.AutomindCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.AutomindResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.CelpeSaudeCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.CelpeSaudeRateioService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.CelpeSaudeResumoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.CelpeSaudeResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.LMTransportesCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.LMTransportesResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.ReportService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.TechnitOdonto;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.TechnitOdontoCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.TechnitSaude;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.TechnitSaudeCoparticipacaoService;
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

	private static final String BRADESCO_AUTOMIND_REPORT = "/reports/bradesco-automind.jasper";
	private static final String BRADESCO_LM_TRANSPORTES_REPORT = "/reports/bradesco-LMTransportes.jasper";
	private static final String BRADESCO_TECHNIT_ODONTO_REPORT = "/reports/bradesco-TechnitOdonto.jasper";
	private static final String BRADESCO_TECHNIT_SAUDE_REPORT = "/reports/bradesco-TechnitSaude.jasper";
	private static final String BRADESCO_CELPE_SAUDE_REPORT = "/reports/bradesco-CelpeSaude.jasper";

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

	@Autowired
	private CelpeSaudeResumoService celpeSaudeResumoService;

	@Autowired
	private CelpeSaudeResumoDetailService celpeSaudeResumoDetailService;

	@Autowired
	private CelpeSaudeRateioService celpeSaudeRateioService;

	@Autowired
	private CelpeSaudeCoparticipacaoService celpeSaudeCoparticipacaoService;

	@Autowired
	private ArquivoExecucaoService arquivoExecucaoService;

	@Override
	public void printReport(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		EmpresaUi empresaUi;
		ContratoUi contratoUi;

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
				if (CD_EMPRESA_AUTOMIND.equals(empresaUi.getCdEmpresa())) {
					printReportAutomind(coParticipacaoContext, mes, ano);
				} else if (CD_EMPRESA_LM_TRANSPORTES.equals(empresaUi.getCdEmpresa())) {
					printReportLmTransportes(coParticipacaoContext, mes, ano);
				} else if (CD_EMPRESA_TECHNIT_ODONTO.equals(empresaUi.getCdEmpresa())) {
					printReportTechnitOdonto(coParticipacaoContext, mes, ano);
				} else if (CD_EMPRESA_TECHNIT_SAUDE.equals(empresaUi.getCdEmpresa())) {
					printReportTechnitSaude(coParticipacaoContext, mes, ano);
				} else if (CD_EMPRESA_CELPE_SAUDE.equals(empresaUi.getCdEmpresa())) {
					printReportCelpeSaude(coParticipacaoContext, mes, ano);
				} else {
					throw new ServiceException(
							"EmpresaUi[] doesn't has a report configured:",
							empresaUi.getCdEmpresa());
				}
			}

			LOGGER.info("END");
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

	private void printReportCelpeSaude(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		JRDataSource jrDataSource;
		List<CelpeSaudeReport> celpeSaudeReports;
		CelpeSaudeReport celpeSaudeReport;
		InputStream inputStream;
		ContratoUi contratoUi;

		try {
			LOGGER.info("BEGIN");

			contratoUi = (ContratoUi) coParticipacaoContext.getArquivoExecucaoUi().getContrato();
			celpeSaudeReports = new ArrayList<>();
			celpeSaudeReport = new CelpeSaudeReport();
			celpeSaudeReport.setCdContrato(contratoUi.getCdContrato());
			celpeSaudeReport.setMes(mes);
			celpeSaudeReport.setAno(ano);
			celpeSaudeReport.setCelpeSaudeResumoViewUis(celpeSaudeResumoService.listByMesAndAno(mes, ano));
			celpeSaudeReport.setCelpeSaudeRateioViewUis(celpeSaudeRateioService.listByMesAndAno(mes, ano));
			celpeSaudeReport
					.setCelpeSaudeCoparticipacaoViewUis(celpeSaudeCoparticipacaoService.listByMesAndAno(mes, ano));

			celpeSaudeReports.add(celpeSaudeReport);

			LOGGER.info("Creating the DataSource to fill the report:");
			jrDataSource = new CelpeSaudeJRDataSource(celpeSaudeReports);

			LOGGER.info("Loading the report file[{}] to be filled with data:", BRADESCO_CELPE_SAUDE_REPORT);
			inputStream = getClass().getResourceAsStream(BRADESCO_CELPE_SAUDE_REPORT);

			fillAndExportReportToSpreadsheet(coParticipacaoContext, jrDataSource, inputStream);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private void printReportTechnitSaude(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		JRDataSource jrDataSource;
		List<TechnitSaudeReport> technitSaudeReports;
		TechnitSaudeReport technitSaudeReport;
		InputStream inputStream;
		TechnitSaude technitSaude;
		ContratoUi contratoUi;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Creating DataSource to fill the report:");
			contratoUi = (ContratoUi) coParticipacaoContext.getArquivoExecucaoUi().getContrato();
			technitSaude = TechnitSaude.parse(contratoUi.getCdContrato());

			technitSaudeReports = new ArrayList<>();
			technitSaudeReport = new TechnitSaudeReport();
			technitSaudeReport
					.setTechnitSaudeCoparticipacaoViewUis(technitSaudeCoparticipacaoService.listByMesAndAno(mes, ano));
			technitSaudeReport.setTechnitHeaderViewUi(createTechnitHeader(coParticipacaoContext, mes, ano));
			technitSaudeReport.setSheetNameCoparticipacao(technitSaude.getDescription());

			technitSaudeReports.add(technitSaudeReport);

			LOGGER.info("Reading the report file[{}] to be filled with data:", BRADESCO_TECHNIT_SAUDE_REPORT);
			inputStream = getClass().getResourceAsStream(BRADESCO_TECHNIT_SAUDE_REPORT);

			jrDataSource = new TechnitSaudeJRDataSource(technitSaudeReports);

			fillAndExportReportToSpreadsheet(coParticipacaoContext, jrDataSource, inputStream);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private void printReportTechnitOdonto(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		JRDataSource jrDataSource;
		List<TechnitOdontoReport> technitOdontoReports;
		TechnitOdontoReport technitOdontoReport;
		InputStream inputStream;
		TechnitOdonto technitOdonto;
		ContratoUi contratoUi;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Creating DataSource to fill the report:");
			contratoUi = (ContratoUi) coParticipacaoContext.getArquivoExecucaoUi().getContrato();
			technitOdonto = TechnitOdonto.parse(contratoUi.getCdContrato());

			technitOdontoReports = new ArrayList<>();
			technitOdontoReport = new TechnitOdontoReport();
			technitOdontoReport.setTechnitOdontoCoparticipacaoViewUis(
					technitOdontoCoparticipacaoService.listByMesAndAnoAmdSubFatura(mes, ano, technitOdonto));
			technitOdontoReport.setTechnitHeaderViewUi(createTechnitHeader(coParticipacaoContext, mes, ano));
			technitOdontoReport.setSheetNameCoparticipacao(technitOdonto.getDescription());

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
