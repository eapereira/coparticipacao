package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.spread.SpreadSaudeJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.SpreadSaudeReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.SpreadSaudeResumoReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.SpreadSaudeCoparticipacaoResumidaService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.SpreadSaudeCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.SpreadSaudeReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.SpreadSaudeResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class SpreadSaudeReportWriterImpl implements SpreadSaudeReportWriter {

	private static final Logger LOGGER = LogManager.getLogger(SpreadSaudeReportWriterImpl.class);

	private static final String CD_EMPRESA_SPREAD_SAUDE = "073828";

	private static final String BRADESCO_SPREAD_SAUDE_REPORT = "/reports/bradesco/SpreadSaude.jasper";

	@Autowired
	private SpreadSaudeResumoService spreadSaudeResumoService;

	@Autowired
	private SpreadSaudeCoparticipacaoService spreadSaudeCoparticipacaoService;

	@Autowired
	private SpreadSaudeCoparticipacaoResumidaService spreadSaudeCoparticipacaoResumidaService;

	@Override
	public String getCdEmpresa() throws ServiceException {
		return CD_EMPRESA_SPREAD_SAUDE;
	}

	@Override
	public String getReportFile() throws ServiceException {
		return BRADESCO_SPREAD_SAUDE_REPORT;
	}

	@Override
	public JRDataSource getDataSource(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		JRDataSource jrDataSource;
		SpreadSaudeReport spreadSaudeReport;
		List<SpreadSaudeReport> spreadSaudeReports;
		ContratoUi contratoUi;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Creating DataSource to fill the report:");
			contratoUi = (ContratoUi) coParticipacaoContext.getArquivoExecucaoUi().getContrato();

			spreadSaudeReports = new ArrayList<>();

			spreadSaudeReport = new SpreadSaudeReport();
			spreadSaudeReport.setSpreadSaudeResumoReports(createResumo(coParticipacaoContext, mes, ano));
			spreadSaudeReport.setSpreadSaudeCoparticipacaoResumidaViewUis(
					spreadSaudeCoparticipacaoResumidaService.listByMesAndAno(mes, ano));
			spreadSaudeReport
					.setSpreadSaudeCoparticipacaoViewUis(spreadSaudeCoparticipacaoService.listByMesAndAno(mes, ano));

			spreadSaudeReports.add(spreadSaudeReport);

			jrDataSource = new SpreadSaudeJRDataSource(spreadSaudeReports);

			LOGGER.info("END");
			return jrDataSource;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private List<SpreadSaudeResumoReport> createResumo(
			CoParticipacaoContext coParticipacaoContext,
			Integer mes,
			Integer ano) throws ServiceException {
		List<SpreadSaudeResumoReport> spreadSaudeResumoReports;
		SpreadSaudeResumoReport spreadSaudeResumoReport;

		try {
			LOGGER.info("BEGIN");

			spreadSaudeResumoReports = new ArrayList<>();
			spreadSaudeResumoReport = new SpreadSaudeResumoReport();
			spreadSaudeResumoReport.setResumo(spreadSaudeResumoService.listByMesAndAnoAndAtivos(mes, ano));
			spreadSaudeResumoReport.setResumoInativo(spreadSaudeResumoService.listByMesAndAnoAndInativos(mes, ano));

			spreadSaudeResumoReports.add(spreadSaudeResumoReport);

			LOGGER.info("END");
			return spreadSaudeResumoReports;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
