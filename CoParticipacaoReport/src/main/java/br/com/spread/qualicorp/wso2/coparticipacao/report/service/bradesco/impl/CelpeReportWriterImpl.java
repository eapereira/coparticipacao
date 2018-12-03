package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.CelpeSaudeJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.CelpeSaudeReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.CelpeSaudeCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.CelpeSaudeRateioService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.CelpeSaudeReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.CelpeSaudeResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class CelpeReportWriterImpl implements CelpeSaudeReportWriter {

	private static final Logger LOGGER = LogManager.getLogger(CelpeReportWriterImpl.class);

	private static final String CD_EMPRESA_CELPE_SAUDE = "071421";

	private static final String BRADESCO_CELPE_SAUDE_REPORT = "/reports/bradesco/CelpeSaude.jasper";

	@Autowired
	private CelpeSaudeResumoService celpeSaudeResumoService;

	@Autowired
	private CelpeSaudeRateioService celpeSaudeRateioService;

	@Autowired
	private CelpeSaudeCoparticipacaoService celpeSaudeCoparticipacaoService;

	@Override
	public String getReportFile() throws ServiceException {
		return BRADESCO_CELPE_SAUDE_REPORT;
	}

	@Override
	public JRDataSource getDataSource(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		JRDataSource jrDataSource;
		List<CelpeSaudeReport> celpeSaudeReports;
		CelpeSaudeReport celpeSaudeReport;
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

			LOGGER.info("END");
			return jrDataSource;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public String getCdEmpresa() throws ServiceException {
		return CD_EMPRESA_CELPE_SAUDE;
	}

}
