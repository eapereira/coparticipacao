package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalResultUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegraConditionalResultService extends AbstractService<RegraConditionalResultUi> {

	List<RegraConditionalResultUi> listByRegraConditional(RegraConditionalUi regraConditionalUi)
			throws ServiceException;

}
