package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputBeneficiarioUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface InputBeneficiarioService
		extends AbstractService<InputBeneficiarioUi> {

	InputBeneficiarioUi findByArquivoInputId(ArquivoInputUi arquivoInputUi)
			throws ServiceException;

}
