package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface InputDependenteIsentoColsService
		extends AbstractService<InputDependenteIsentoColsUi> {

	List<InputDependenteIsentoColsUi> listByInputDependenteIsentoId(
			InputDependenteIsentoUi inputDependenteIsentoUi)
			throws ServiceException;

	List<InputDependenteIsentoColsUi> listByArquivoInput(
			ArquivoInputUi arquivoInputUi) throws ServiceException;

}
