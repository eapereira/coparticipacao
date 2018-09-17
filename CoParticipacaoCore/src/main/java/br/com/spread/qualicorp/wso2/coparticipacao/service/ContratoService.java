package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ContratoService extends AbstractService<ContratoUi> {
	ContratoUi findByCdContrato(String contratoName) throws ServiceException;

	List<ContratoUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException;

	ContratoUi findByCdEmpresaAndCdContrato(String cdEmpresa, String cdContrato) throws ServiceException;
}
