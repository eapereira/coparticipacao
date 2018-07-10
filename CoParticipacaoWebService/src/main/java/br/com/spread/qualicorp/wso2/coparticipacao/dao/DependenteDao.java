package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface DependenteDao extends AbstractDao<DependenteEntity> {

	DependenteEntity findByCpf(String cpf) throws DaoException;

	List<DependenteEntity> listByEmpresaId(Long id) throws DaoException;

}
