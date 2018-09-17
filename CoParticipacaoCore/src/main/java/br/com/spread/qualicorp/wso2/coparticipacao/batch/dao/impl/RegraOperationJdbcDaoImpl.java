package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.RegraOperationJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.RegraOperationSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraOperationEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraOperationJdbcDaoImpl extends AbstractBatchDaoImpl<RegraOperationEntity>
		implements
		RegraOperationJdbcDao {

	public RegraOperationJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			RegraOperationEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new RegraOperationSetter(setterAdapterType, entity);
	}

}
