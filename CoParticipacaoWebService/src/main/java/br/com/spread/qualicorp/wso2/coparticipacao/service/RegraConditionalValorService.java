package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalValorUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegraConditionalValorService
		extends AbstractService<RegraConditionalValorUi> {

	Object getValor(
			RegraConditionalValorUi regraConditionalValorUi,
			ColDefType colDefType) throws ServiceException;

}
