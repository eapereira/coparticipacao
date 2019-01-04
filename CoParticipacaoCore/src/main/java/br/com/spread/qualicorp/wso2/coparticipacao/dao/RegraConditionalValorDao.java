package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalValorEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegraConditionalValorDao extends AbstractDao<RegraConditionalValorEntity> {

	List<RegraConditionalValorEntity> listByRegraConditionalOperationId(Long regraConditionalOperationId)
			throws DaoException;

}
