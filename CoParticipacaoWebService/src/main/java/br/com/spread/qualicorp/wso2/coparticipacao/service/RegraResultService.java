package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraResultUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegraResultService extends AbstractService<RegraResultUi> {

	List<RegraResultUi> listByRegraId(RegraUi regraUi) throws ServiceException;

}
