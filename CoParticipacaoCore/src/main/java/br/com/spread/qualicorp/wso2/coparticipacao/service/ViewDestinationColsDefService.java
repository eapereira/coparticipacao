package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ViewDestinationColsDefService extends AbstractService<ViewDestinationColsDefUi> {
	List<ViewDestinationColsDefUi> listByViewDestinationId(ViewDestinationUi viewDestinationUi) throws ServiceException;
}
