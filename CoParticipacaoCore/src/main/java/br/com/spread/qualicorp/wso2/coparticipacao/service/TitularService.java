package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface TitularService extends AbstractService<TitularUi> {
	TitularUi findByCpf(String cpf) throws ServiceException;

	List<TitularUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException;

	List<TitularUi> listByEmpresaIdOrderByCpfAndName(EmpresaUi empresaUi) throws ServiceException;

	List<TitularUi> listByEmpresaIdOrderByMatriculaAndName(EmpresaUi empresaUi) throws ServiceException;

}
