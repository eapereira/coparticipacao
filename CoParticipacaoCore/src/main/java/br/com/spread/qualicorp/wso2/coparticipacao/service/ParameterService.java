package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ParameterUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ParameterService extends AbstractService<ParameterUi> {
	
	List<ParameterUi> listByEmpresaId(Long id) throws ServiceException;

}
