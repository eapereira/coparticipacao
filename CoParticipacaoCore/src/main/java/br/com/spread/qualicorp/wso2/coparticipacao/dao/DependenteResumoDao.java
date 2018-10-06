package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteResumoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DependenteResumoDao extends AbstractDao<DependenteResumoEntity> {

	List<DependenteResumoEntity> listByEmpresaId(Long empresaId) throws DaoException;
}
