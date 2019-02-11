package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.technit.TechnitOdontoJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitOdonto;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitOdontoReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.TechnitOdontoCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.TechnitOdontoReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TechnitOdontoReportWriterImpl extends TechnitReportWriter implements TechnitOdontoReportWriter {

	private static final Logger LOGGER = LogManager.getLogger(TechnitOdontoReportWriterImpl.class);

	private static final String CD_EMPRESA_TECHNIT_ODONTO = "091707";
	
	private static final String BRADESCO_TECHNIT_ODONTO_REPORT = "/reports/bradesco/TechnitOdonto.jasper";

	@Autowired
	private TechnitOdontoCoparticipacaoService technitOdontoCoparticipacaoService;

	@Override
	public String getReportFile() throws ServiceException {
		return BRADESCO_TECHNIT_ODONTO_REPORT;
	}

	@Override
	public JRDataSource getDataSource(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		JRDataSource jrDataSource;
		List<TechnitOdontoReport> technitOdontoReports;
		TechnitOdontoReport technitOdontoReport;
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
					technitOdontoCoparticipacaoService.listBySubFatura(technitOdonto));
			technitOdontoReport.setTechnitHeaderViewUi(createTechnitHeader(coParticipacaoContext, mes, ano));
			technitOdontoReport.setSheetNameCoparticipacao(technitOdonto.getDescription());

			technitOdontoReports.add(technitOdontoReport);

			jrDataSource = new TechnitOdontoJRDataSource(technitOdontoReports);

			LOGGER.info("END");
			return jrDataSource;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public String getCdEmpresa() throws ServiceException {
		return CD_EMPRESA_TECHNIT_ODONTO;
	}
}
