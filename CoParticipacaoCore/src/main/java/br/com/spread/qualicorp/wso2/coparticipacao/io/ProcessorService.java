package br.com.spread.qualicorp.wso2.coparticipacao.io;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ProcessorService {
	void readInputStream(
			CoParticipacaoContext coParticipacaoContext,
			ProcessorListener processorListener) throws ServiceException;

}
