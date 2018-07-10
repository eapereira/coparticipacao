package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularIsentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.TitularIsentoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.TitularIsentoSetter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class TitularIsentoJdbcDaoImpl extends AbstractJdbcDaoImpl<TitularIsentoEntity>
		implements
		TitularIsentoJdbcDao {

	public TitularIsentoJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			TitularIsentoEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new TitularIsentoSetter(setterAdapterType, entity);
	}

}
