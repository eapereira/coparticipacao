package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface InputTitularService extends AbstractService<InputTitularUi> {

	List<InputTitularUi> listByArquivoInputColsDefId(Long id)
			throws ServiceException;

	List<InputTitularUi> listByArquivoInputId(Long id) throws ServiceException;

}
