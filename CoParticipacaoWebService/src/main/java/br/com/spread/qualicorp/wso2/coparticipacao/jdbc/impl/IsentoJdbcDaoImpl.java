package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.IsentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.IsentoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.IsentoSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class IsentoJdbcDaoImpl extends AbstractJdbcDaoImpl<IsentoEntity>
		implements
		IsentoJdbcDao {

	public IsentoJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			IsentoEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new IsentoSetter(setterAdapterType, entity);
	}

}
