package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularResumoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface TitularResumoDao extends AbstractDao<TitularResumoEntity> {

	List<TitularResumoEntity> listByEmpresaId(Long empresaId) throws DaoException;
}
