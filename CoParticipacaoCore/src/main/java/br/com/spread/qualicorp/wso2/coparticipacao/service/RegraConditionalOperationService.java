package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalOperationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegraConditionalOperationService extends AbstractService<RegraConditionalOperationUi> {

	List<RegraConditionalOperationUi> listByRegraConditional(RegraConditionalUi regraConditionalUi)
			throws ServiceException;

}
