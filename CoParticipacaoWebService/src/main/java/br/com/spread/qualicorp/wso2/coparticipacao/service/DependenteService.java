package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DependenteService extends AbstractService<DependenteUi> {

	DependenteUi findByCpf(String cpf) throws ServiceException;

	List<DependenteUi> listByEmpresaId(Long id) throws ServiceException;

}
