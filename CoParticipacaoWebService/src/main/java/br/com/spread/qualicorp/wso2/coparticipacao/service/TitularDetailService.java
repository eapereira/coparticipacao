package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface TitularDetailService extends AbstractService<TitularDetailUi> {
	TitularDetailUi createTitularDetail(TitularUi titularUi, ArquivoInputColsDefUi arquivoInputColsDefUi, Object value)
			throws ServiceException;

	Object getFieldValue(TitularDetailUi titularDetailUi) throws ServiceException;

	void storeDetails(CoParticipacaoContext coParticipacaoContext, TitularUi titularUi) throws ServiceException;

}
