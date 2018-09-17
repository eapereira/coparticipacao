package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ParameterEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ParameterDao extends AbstractDao<ParameterEntity> {

	List<ParameterEntity> listByEmpresaId(Long id) throws DaoException;
}
