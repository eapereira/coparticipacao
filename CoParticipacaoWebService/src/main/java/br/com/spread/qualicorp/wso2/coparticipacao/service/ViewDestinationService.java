package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ViewDestinationService
		extends AbstractService<ViewDestinationUi> {

	String createSqlToViewDestination(ViewDestinationUi viewDestinationUi)
			throws ServiceException;

	List<DynamicEntity> listByContratoAndMesAndAno(
			ViewDestinationUi ViewDestinationUi,
			int mes,
			int ano) throws ServiceException;

}
