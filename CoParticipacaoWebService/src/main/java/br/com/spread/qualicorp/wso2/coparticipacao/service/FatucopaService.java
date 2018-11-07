package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface FatucopaService {

	void generateOutputFileWithoutFatucopa(CoParticipacaoContext coParticipacaoContext) throws ServiceException;

}
