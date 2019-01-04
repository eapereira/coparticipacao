package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface RegraService extends AbstractService<RegraUi> {
	void applyRegras(CoParticipacaoContext coParticipacaoContext) throws ServiceException;

	void applyRegra(CoParticipacaoContext coParticipacaoContext, RegraUi regraUi) throws ServiceException;

	List<RegraUi> listByArquivoInputId(ArquivoInputUi arquivoInputUi) throws ServiceException;

	List<RegraUi> listByArquivoInputSheet(ArquivoInputSheetUi arquivoInputSheetUi) throws ServiceException;

}
