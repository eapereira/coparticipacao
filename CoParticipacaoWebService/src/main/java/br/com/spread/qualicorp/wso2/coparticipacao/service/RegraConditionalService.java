package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegraConditionalService
		extends AbstractService<RegraConditionalUi> {

	List<RegraConditionalUi> listByArquivoInputId(ArquivoInputUi arquivoInputUi)
			throws ServiceException;

	void applyRegras(
			LancamentoUi lancamentoUi,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

}
