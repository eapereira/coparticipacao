package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.TechnitSaudeJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitSaude;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitSaudeReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.TechnitSaudeCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.TechnitSaudeReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TechnitSaudeReportWriterImpl extends TechnitReportWriter implements TechnitSaudeReportWriter {

	private static final Logger LOGGER = LogManager.getLogger(TechnitSaudeReportWriterImpl.class);

	private static final String CD_EMPRESA_TECHNIT_SAUDE = "180831";
	
	private static final String BRADESCO_TECHNIT_SAUDE_REPORT = "/reports/bradesco/TechnitSaude.jasper";

	@Autowired
	private TechnitSaudeCoparticipacaoService technitSaudeCoparticipacaoService;

	@Override
	public String getReportFile() throws ServiceException {
		return BRADESCO_TECHNIT_SAUDE_REPORT;
	}

	@Override
	public JRDataSource getDataSource(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		JRDataSource jrDataSource;
		List<TechnitSaudeReport> technitSaudeReports;
		TechnitSaudeReport technitSaudeReport;
		TechnitSaude technitSaude;
		ContratoUi contratoUi;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Creating DataSource to fill the report:");
			contratoUi = (ContratoUi) coParticipacaoContext.getArquivoExecucaoUi().getContrato();
			technitSaude = TechnitSaude.parse(contratoUi.getCdContrato());

			technitSaudeReports = new ArrayList<>();
			technitSaudeReport = new TechnitSaudeReport();
			technitSaudeReport.setTechnitSaudeCoparticipacaoViewUis(
					technitSaudeCoparticipacaoService
							.listByMesAndAnoAndSubFatura(mes, ano, technitSaude.getSubFaturas()));
			technitSaudeReport.setTechnitHeaderViewUi(createTechnitHeader(coParticipacaoContext, mes, ano));
			technitSaudeReport.setSheetNameCoparticipacao(technitSaude.getDescription());

			technitSaudeReports.add(technitSaudeReport);

			jrDataSource = new TechnitSaudeJRDataSource(technitSaudeReports);

			LOGGER.info("END");
			return jrDataSource;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public String getCdEmpresa() throws ServiceException {
		return CD_EMPRESA_TECHNIT_SAUDE;
	}

}