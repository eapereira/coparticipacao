package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.OperadoraEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.OperadoraJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.OperadoraSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class OperadoraJdbcDaoImpl extends AbstractJdbcDaoImpl<OperadoraEntity>
		implements
		OperadoraJdbcDao {

	public OperadoraJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			OperadoraEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new OperadoraSetter(setterAdapterType, entity);
	}

}
