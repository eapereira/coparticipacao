package br.com.spread.qualicorp.wso2.coparticipacao.io;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ProcessorListener {

	ProcessLineResult processLine(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	void beforeProcess(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	void afterProcess(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	boolean validateLine(
			String line,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

}
