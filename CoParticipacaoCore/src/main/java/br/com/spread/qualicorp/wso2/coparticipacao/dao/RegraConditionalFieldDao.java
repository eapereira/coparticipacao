package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalFieldEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegraConditionalFieldDao extends AbstractDao<RegraConditionalFieldEntity> {

	List<RegraConditionalFieldEntity> listByRegraConditionalOperationId(Long regraConditionalOperationId)
			throws DaoException;

}
