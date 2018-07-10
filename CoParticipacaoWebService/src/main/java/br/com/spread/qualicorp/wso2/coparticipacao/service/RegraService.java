package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface RegraService extends AbstractService<RegraUi> {

	void applyRegras(
			LancamentoUi lancamentoUi,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	public void applyRegra(
			RegraUi regraUi,
			LancamentoDetail lancamentoDetail,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	List<RegraUi> listRegrasByArquivoInputId(Long arquivoInputFatucopaId)
			throws ServiceException;

}
