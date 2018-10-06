package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ViewDestinationService extends AbstractService<ViewDestinationUi> {

	String createSqlToViewDestination(ViewDestinationUi viewDestinationUi, boolean useContrato) throws ServiceException;

	List<DynamicEntity> listByContratoAndMesAndAno(
			ViewDestinationUi ViewDestinationUi,
			ContratoUi contratoUi,
			int mes,
			int ano) throws ServiceException;

	List<DynamicEntity> listBydMesAndAno(ViewDestinationUi viewDestinationUi, int mes, int ano) throws ServiceException;

}
