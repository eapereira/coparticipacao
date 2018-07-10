package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraOperationEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.RegraOperationJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.RegraOperationSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraOperationJdbcDaoImpl extends AbstractJdbcDaoImpl<RegraOperationEntity>
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
