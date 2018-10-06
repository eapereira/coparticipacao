package br.com.spread.qualicorp.wso2.coparticipacao.batch.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DependenteBatchService extends AbstractBatchService<DependenteUi> {

	void saveBatch(CoParticipacaoContext coParticipacaoContext, List<DependenteUi> dependenteUis)
			throws ServiceException;
}
