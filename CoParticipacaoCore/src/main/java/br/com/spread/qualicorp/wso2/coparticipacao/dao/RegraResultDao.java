package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraResultEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegraResultDao extends AbstractDao<RegraResultEntity> {
	
	List<RegraResultEntity> listByRegraId(Long regraId) throws DaoException;
}
