package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteResumoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DependenteResumoBatchDao extends AbstractBatchDao<DependenteResumoEntity> {

	List<DependenteResumoEntity> listByEmpresaId(Long empresaId) throws DaoException;
}
