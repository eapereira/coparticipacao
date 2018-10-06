package br.com.spread.qualicorp.wso2.coparticipacao.batch.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface TitularBatchService extends AbstractBatchService<TitularUi> {
	void saveBatch(CoParticipacaoContext coParticipacaoContext,List<TitularUi> titularUis) throws ServiceException;
}
