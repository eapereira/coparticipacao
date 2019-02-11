package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularResumoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface TitularResumoBatchDao extends AbstractBatchDao<TitularResumoEntity> {

	List<TitularResumoEntity> listByEmpresaId(Long empresaId) throws DaoException;
}
