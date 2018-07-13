package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputOutputDesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ArquivoInputOutputDesconhecidoService
		extends AbstractService<ArquivoInputOutputDesconhecidoUi> {

	List<ArquivoInputOutputDesconhecidoUi> listByArquivoInputId(Long id)
			throws ServiceException;

	List<ArquivoInputOutputDesconhecidoUi> listByContratoId(
			ContratoUi contratoUi) throws ServiceException;
}
