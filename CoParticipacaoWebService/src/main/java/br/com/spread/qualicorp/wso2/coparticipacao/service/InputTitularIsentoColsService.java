package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface InputTitularIsentoColsService
		extends AbstractService<InputTitularIsentoColsUi> {

	List<InputTitularIsentoColsUi> listByInputTitularIsentoId(
			InputTitularIsentoUi inputTitularIsentoUi) throws ServiceException;

	List<InputTitularIsentoColsUi> listByArquivoInputId(
			ArquivoInputUi arquivoInputUi) throws ServiceException;

}
