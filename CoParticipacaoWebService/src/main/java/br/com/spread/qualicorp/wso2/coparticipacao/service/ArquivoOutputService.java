package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ArquivoOutputService extends AbstractService<ArquivoOutputUi> {

	void writeOutputFile(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

}
