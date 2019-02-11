package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraOperationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraValorUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface RegraValorService extends AbstractService<RegraValorUi> {

	List<RegraValorUi> listByRegraOperationId(RegraOperationUi regraOperationUi) throws ServiceException;

}
