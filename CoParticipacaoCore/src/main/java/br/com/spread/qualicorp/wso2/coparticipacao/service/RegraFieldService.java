package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraFieldUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraOperationUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface RegraFieldService extends AbstractService<RegraFieldUi> {

	List<RegraFieldUi> listByRegraOperationId(RegraOperationUi regraOperationUi) throws ServiceException;

}
