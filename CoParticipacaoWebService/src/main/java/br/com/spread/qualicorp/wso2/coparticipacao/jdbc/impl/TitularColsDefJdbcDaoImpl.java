package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.TitularColsDefJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.TitularColsDefSetter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class TitularColsDefJdbcDaoImpl extends AbstractJdbcDaoImpl<TitularColsDefEntity>
		implements
		TitularColsDefJdbcDao {

	public TitularColsDefJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			TitularColsDefEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new TitularColsDefSetter(setterAdapterType, entity);
	}

}
