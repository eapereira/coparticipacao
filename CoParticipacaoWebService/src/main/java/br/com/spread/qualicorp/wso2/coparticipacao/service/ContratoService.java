package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ContratoService
		extends
		AbstractService<ContratoUi> {
	ContratoUi findByCdContrato(String contratoName) throws ServiceException;
}
