package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface InputDependenteService
		extends AbstractService<InputDependenteUi> {

	List<InputDependenteUi> listByArquivoInputColsDefId(Long id)
			throws ServiceException;

	List<InputDependenteUi> listByArquivoInputId(Long id)
			throws ServiceException;

}
