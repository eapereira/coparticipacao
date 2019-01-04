package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalResultEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegraConditionalResultDao extends AbstractDao<RegraConditionalResultEntity> {

	List<RegraConditionalResultEntity> listByRegraConditionalId(Long regraConditionalId) throws DaoException;

}
