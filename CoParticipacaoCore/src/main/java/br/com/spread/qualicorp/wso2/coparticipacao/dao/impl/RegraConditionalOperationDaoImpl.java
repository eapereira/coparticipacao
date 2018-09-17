package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalOperationDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalOperationEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraConditionalOperationDaoImpl
		extends AbstractDaoImpl<RegraConditionalOperationEntity>
		implements RegraConditionalOperationDao {

	public RegraConditionalOperationDaoImpl() throws DaoException {
		super();
	}

}
