package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.LMTransportesJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.LMTransportesReport;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.LMTransportesCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.LMTransportesReportWriter;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.LMTransportesResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LMTransportesReportWriterImpl implements LMTransportesReportWriter {

	private static final Logger LOGGER = LogManager.getLogger(LMTransportesReportWriterImpl.class);

	private static final String CD_EMPRESA_LM_TRANSPORTES = "073179";
	
	private static final String BRADESCO_LM_TRANSPORTES_REPORT = "/reports/bradesco/LMTransportes.jasper";

	@Autowired
	private LMTransportesResumoService lmTransportesResumoService;

	@Autowired
	private LMTransportesCoparticipacaoService lmTransportesCoparticipacaoService;

	@Override
	public String getReportFile() throws ServiceException {
		return BRADESCO_LM_TRANSPORTES_REPORT;
	}

	@Override
	public JRDataSource getDataSource(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException {
		JRDataSource jrDataSource;
		List<LMTransportesReport> lmTransportesReports;
		LMTransportesReport lmTransportesReport;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Creating DataSource to fill the report:");
			lmTransportesReports = new ArrayList<>();
			lmTransportesReport = new LMTransportesReport();
			lmTransportesReport.setLmTransportesCoparticipacaoViewUis(lmTransportesCoparticipacaoService.listAll());
			lmTransportesReport.setLmTransportesResumoViewUis(lmTransportesResumoService.listAll());
			lmTransportesReport.setSheetNameCoparticipacao(String.format("Coparticipação %d/%d", mes, ano));
			lmTransportesReport.setSheetNameRateio("Rateio");

			lmTransportesReports.add(lmTransportesReport);

			jrDataSource = new LMTransportesJRDataSource(lmTransportesReports);

			LOGGER.info("END");
			return jrDataSource;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public String getCdEmpresa() throws ServiceException {
		return CD_EMPRESA_LM_TRANSPORTES;
	}
}
