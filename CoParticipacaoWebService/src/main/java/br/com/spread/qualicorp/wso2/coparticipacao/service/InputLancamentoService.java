package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputLancamentoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface InputLancamentoService
		extends AbstractService<InputLancamentoUi> {

	List<InputLancamentoUi> listByArquivoInputColsDefId(Long id)
			throws ServiceException;

	List<InputLancamentoUi> listByArquivoInputId(Long id)
			throws ServiceException;

}
