package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface TitularIsentoService extends AbstractService<TitularIsentoUi> {

	void deleteByMesAndAno(EmpresaUi empresaUi, Integer mes, Integer ano) throws ServiceException;

	List<TitularIsentoUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException;

	void deleteByContratoAndMesAndAno(ContratoUi contratoUi, Integer mes, Integer ano) throws ServiceException;

}
