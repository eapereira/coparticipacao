package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ArquivoInputColsDefService
		extends
		AbstractService<ArquivoInputColsDefUi> {

	List<ArquivoInputColsDefUi> listByArquivoInputId(Long id) throws ServiceException;

}
