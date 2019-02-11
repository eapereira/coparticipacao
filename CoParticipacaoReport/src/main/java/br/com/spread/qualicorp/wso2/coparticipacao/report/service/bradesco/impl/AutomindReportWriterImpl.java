package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.automind.AutomindJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.AutomindReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.AutomindCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.AutomindReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.AutomindResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class AutomindReportWriterImpl implements AutomindReportWriter {

	private static final Logger LOGGER = LogManager.getLogger(AutomindReportWriterImpl.class);

	private static final String CD_EMPRESA_AUTOMIND = "074210";
	
	private static final String BRADESCO_AUTOMIND_REPORT = "/reports/bradesco/Automind.jasper";

	@Autowired
	private AutomindResumoService automindResumoService;

	@Autowired
	private AutomindCoparticipacaoService automindCoparticipacaoService;

	@Override
	public String getReportFile() throws ServiceException {
		return BRADESCO_AUTOMIND_REPORT;
	}

	@Override
	public JRDataSource getDataSource(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		JRDataSource jrDataSource;
		List<AutomindReport> automindReports;
		AutomindReport automindReport;

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

			jrDataSource = new AutomindJRDataSource(automindReports);

			LOGGER.info("END");
			return jrDataSource;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public String getCdEmpresa() throws ServiceException {
		return CD_EMPRESA_AUTOMIND;
	}
}
