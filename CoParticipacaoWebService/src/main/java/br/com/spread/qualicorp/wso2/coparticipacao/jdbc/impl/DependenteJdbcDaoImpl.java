package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.DependenteJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.DependenteSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class DependenteJdbcDaoImpl extends AbstractJdbcDaoImpl<DependenteEntity>
		implements
		DependenteJdbcDao {

	public DependenteJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			DependenteEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new DependenteSetter(setterAdapterType, entity);
	}

}
