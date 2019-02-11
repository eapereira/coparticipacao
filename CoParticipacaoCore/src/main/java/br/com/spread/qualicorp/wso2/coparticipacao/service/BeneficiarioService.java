package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.BeneficiarioNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.DependenteDuplicated;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.TitularDuplicated;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface BeneficiarioService {
	boolean validateBeneficiario(CoParticipacaoContext coParticipacaoContext, LancamentoDetailUi lancamentoDetailUi)
			throws ServiceException;

	BeneficiarioUi createBeneficiarioFromMecsas(CoParticipacaoContext coParticipacaoContext) throws ServiceException;

	TitularUi createTitular(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException, TitularDuplicated;

	DependenteUi createDependente(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException, BeneficiarioNotFoundException, DependenteDuplicated;

	DependenteUi findDependente(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException;

	DependenteUi findDependente(CoParticipacaoContext coParticipacaoContext, BeneficiarioIsentoUi beneficiarioIsentoUi)
			throws ServiceException;

	TitularUi findTitular(CoParticipacaoContext coParticipacaoContext, BeneficiarioIsentoUi beneficiarioIsentoUi)
			throws ServiceException;

	boolean isTitular(CoParticipacaoContext coParticipacaoContext, BeneficiarioIsentoUi beneficiarioIsentoUi)
			throws ServiceException;

	TitularUi createTitular(CoParticipacaoContext coParticipacaoContext, BeneficiarioIsentoUi beneficiarioIsentoUi)
			throws ServiceException;

	DependenteUi createDependente(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException;

	void updateTitular(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			TitularUi titularUi) throws ServiceException;

	void updateDependente(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			DependenteUi dependenteUi) throws ServiceException;

}
