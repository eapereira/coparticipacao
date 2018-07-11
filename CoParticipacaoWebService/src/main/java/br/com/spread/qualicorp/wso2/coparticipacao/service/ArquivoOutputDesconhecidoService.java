package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputDesconhecidoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ArquivoOutputDesconhecidoService
		extends AbstractService<ArquivoOutputDesconhecidoUi> {

	ArquivoOutputDesconhecidoUi findByArquivoInputId(Long id)
			throws ServiceException;

}
