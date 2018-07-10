package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ContratoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.ContratoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.ContratoSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ContratoJdbcDaoImpl extends AbstractJdbcDaoImpl<ContratoEntity>
		implements
		ContratoJdbcDao {

	public ContratoJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			ContratoEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new ContratoSetter(setterAdapterType, entity);
	}

}
