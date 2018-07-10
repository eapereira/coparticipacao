package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.InputDependenteJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.InputDependenteSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputDependenteJdbcDaoImpl extends AbstractJdbcDaoImpl<InputDependenteEntity>
		implements
		InputDependenteJdbcDao {

	public InputDependenteJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			InputDependenteEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new InputDependenteSetter(setterAdapterType, entity);
	}

}
