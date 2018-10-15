package br.com.spread.qualicorp.wso2.coparticipacao.report.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.birt.report.engine.api.EXCELRenderOption;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IRenderTask;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.ReportEngine;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.webservice.coparticipacaoreport.CoParticipacaoReportInfo;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.ReportService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ReportServiceImpl implements ReportService {

	private static final Logger LOGGER = LogManager.getLogger(ReportServiceImpl.class);

	public CoParticipacaoReportInfo createReport(Long empresaId, Integer mes, Integer ano) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			IReportDocument document = null;
			EngineConfig engineConfig = new EngineConfig();
			ReportEngine reportEngine = new ReportEngine(engineConfig);

			document = reportEngine.openReportDocument("output/resample/myreport.rptdocument");
			EXCELRenderOption options = new EXCELRenderOption();
			options.setOutputFormat("xls");
			options.setOutputFileName("output/resample/xlsoutput.xls");
			IRenderTask task = reportEngine.createRenderTask(document);
			task.setRenderOption(options);
			task.render();

			LOGGER.info("END");
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
