package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface BeneficiarioService extends AbstractService<BeneficiarioUi> {
	boolean validateBeneficiario(
			CoParticipacaoContext coParticipacaoContext,
			LancamentoUi lancamentoUi) throws ServiceException;

}
