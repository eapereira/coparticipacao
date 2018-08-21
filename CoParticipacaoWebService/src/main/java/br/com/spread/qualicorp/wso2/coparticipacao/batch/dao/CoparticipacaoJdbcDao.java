package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface CoparticipacaoJdbcDao extends AbstractJdbcDao<EmpresaEntity> {

	void clearCoparticipacao(Long empresaId) throws DaoException;
}
