package br.com.spread.qualicorp.wso2.coparticipacao.report.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ReportService {

	void printReport(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano) throws ServiceException;

}