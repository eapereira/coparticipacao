package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface BeneficiarioService {
	boolean validateBeneficiario(
			CoParticipacaoContext coParticipacaoContext,
			LancamentoUi lancamentoUi) throws ServiceException;

	BeneficiarioUi createBeneficiarioFromMecsas(
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	TitularUi createTitular(
			BeneficiarioUi beneficiarioUi,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	DependenteUi createDependente(
			BeneficiarioUi beneficiarioUi,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

}
