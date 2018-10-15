package br.com.spread.qualicorp.wso2.coparticipacao.report.service;

import br.com.spread.qualicorp.webservice.coparticipacaoreport.CoParticipacaoReportInfo;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ReportService {

	CoParticipacaoReportInfo createReport(Long empresaId, Integer mes, Integer ano) throws ServiceException;

}
