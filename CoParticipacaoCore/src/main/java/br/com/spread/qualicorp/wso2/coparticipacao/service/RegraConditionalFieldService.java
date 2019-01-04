package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalFieldUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalOperationUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegraConditionalFieldService extends AbstractService<RegraConditionalFieldUi> {

	List<RegraConditionalFieldUi> listByRegraConditionalOperation(
			RegraConditionalOperationUi regraConditionalOperationUi) throws ServiceException;

}
