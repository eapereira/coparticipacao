package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.DependenteColsDefJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.DependenteColsDefSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class DependenteColsDefJdbcDaoImpl extends AbstractJdbcDaoImpl<DependenteColsDefEntity>
		implements
		DependenteColsDefJdbcDao {

	public DependenteColsDefJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			DependenteColsDefEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new DependenteColsDefSetter(setterAdapterType, entity);
	}

}
