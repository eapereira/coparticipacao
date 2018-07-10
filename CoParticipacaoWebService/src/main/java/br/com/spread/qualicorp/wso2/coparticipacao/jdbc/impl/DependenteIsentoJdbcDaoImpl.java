package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.DependenteIsentoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.DependenteIsentoSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class DependenteIsentoJdbcDaoImpl extends AbstractJdbcDaoImpl<DependenteIsentoEntity>
		implements
		DependenteIsentoJdbcDao {

	public DependenteIsentoJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			DependenteIsentoEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new DependenteIsentoSetter(setterAdapterType, entity);
	}

}
