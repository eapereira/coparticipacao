package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DependenteDetailService extends AbstractService<DependenteDetailUi> {
	Object getFieldValue(DependenteDetailUi dependenteDetailUi) throws ServiceException;

	DependenteDetailUi createDependenteDetail(
			DependenteUi dependenteUi,
			ArquivoInputColsDefUi arquivoInputColsDefUi,
			Object value) throws ServiceException;

	void storeDetails(CoParticipacaoContext coParticipacaoContext, DependenteUi dependenteUi) throws ServiceException;

}
