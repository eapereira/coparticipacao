package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;

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

	void deleteByMesAndAno(EmpresaUi empresaUi, Integer mes, Integer ano)
			throws ServiceException;

}
