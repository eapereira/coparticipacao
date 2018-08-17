package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LancamentoInputService
		extends AbstractService<LancamentoInputUi> {

	List<LancamentoInputUi> listByArquivoInputId(Long id)
			throws ServiceException;

}
