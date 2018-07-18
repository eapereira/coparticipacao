package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface IsentoService {

	boolean hasIsento(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	void processIsento(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	void saveIsentos(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

}
