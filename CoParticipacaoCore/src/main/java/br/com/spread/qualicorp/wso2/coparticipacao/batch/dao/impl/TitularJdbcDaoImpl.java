package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.TitularJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.TitularSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class TitularJdbcDaoImpl extends AbstractBatchDaoImpl<TitularEntity>
		implements
		TitularJdbcDao {

	public TitularJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			TitularEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new TitularSetter(setterAdapterType, entity);
	}

}
