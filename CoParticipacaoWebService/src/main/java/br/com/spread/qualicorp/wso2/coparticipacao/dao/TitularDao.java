package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface TitularDao extends AbstractDao<TitularEntity> {

	TitularEntity findByCpf(String cpf) throws DaoException;

	List<TitularEntity> listByEmpresaId(Long id) throws DaoException;

}
