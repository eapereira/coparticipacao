package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DesconhecidoService extends AbstractService<DesconhecidoUi> {
	void createDesconhecido(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	void deleteByMesAndAno(int mes, int ano) throws ServiceException;

}
