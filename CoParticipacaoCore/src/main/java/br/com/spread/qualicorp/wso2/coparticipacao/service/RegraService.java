package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface RegraService extends AbstractService<RegraUi> {

	void applyRegras(CoParticipacaoContext coParticipacaoContext, LancamentoUi lancamentoUi) throws ServiceException;

	void applyRegras(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			List<IsentoInputSheetCols> isentoInputSheetColss) throws ServiceException;

	void applyRegra(CoParticipacaoContext coParticipacaoContext, RegraUi regraUi, LancamentoDetail lancamentoDetail)
			throws ServiceException;

	void applyRegra(
			CoParticipacaoContext coParticipacaoContext,
			RegraUi regraUi,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			List<IsentoInputSheetCols> isentoInputSheetColss) throws ServiceException;

	List<RegraUi> listByArquivoInputId(ArquivoInputUi arquivoInputUi) throws ServiceException;

	void applyRegra(
			CoParticipacaoContext coParticipacaoContext,
			RegraUi regraUi,
			IsentoInputSheetColsUi isentoInputSheetColsUi,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException;

}
