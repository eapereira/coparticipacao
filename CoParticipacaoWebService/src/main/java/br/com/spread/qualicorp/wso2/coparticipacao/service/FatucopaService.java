package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.io.ProcessorListener;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface FatucopaService extends ProcessorListener {

	void generateOutputFileWithoutFatucopa(CoParticipacaoContext coParticipacaoContext) throws ServiceException;

}
