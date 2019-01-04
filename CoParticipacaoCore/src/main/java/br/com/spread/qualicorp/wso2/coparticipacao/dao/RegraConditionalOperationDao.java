package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalOperationEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegraConditionalOperationDao extends AbstractDao<RegraConditionalOperationEntity> {

	List<RegraConditionalOperationEntity> listByRegraConditionalId(Long regraConditionalId) throws DaoException;

}
