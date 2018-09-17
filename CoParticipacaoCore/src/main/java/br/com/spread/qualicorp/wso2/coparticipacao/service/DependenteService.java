package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DependenteService extends AbstractService<DependenteUi> {

	DependenteUi findByCpf(String cpf) throws ServiceException;

	List<DependenteUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException;
	
	List<DependenteUi> listByEmpresaIdOrderByCpfAndName(EmpresaUi empresaUi) throws ServiceException;
	
	List<DependenteUi> listByEmpresaIdOrderByMatriculaAndName(EmpresaUi empresaUi) throws ServiceException;

}
