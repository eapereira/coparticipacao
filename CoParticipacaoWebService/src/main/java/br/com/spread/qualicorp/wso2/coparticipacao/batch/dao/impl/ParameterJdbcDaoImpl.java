package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.ParameterJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.ParameterSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ParameterEntity;

/**
 * 
 * @author <a href="mailto:edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ParameterJdbcDaoImpl extends AbstractJdbcDaoImpl<ParameterEntity>
		implements
		ParameterJdbcDao {

	public ParameterJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			ParameterEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new ParameterSetter(setterAdapterType, entity);
	}

}
