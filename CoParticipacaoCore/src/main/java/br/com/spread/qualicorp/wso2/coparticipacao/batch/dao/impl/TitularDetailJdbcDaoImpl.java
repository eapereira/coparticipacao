package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.TitularDetailJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.TitularDetailSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularDetailEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class TitularDetailJdbcDaoImpl extends AbstractBatchDaoImpl<TitularDetailEntity> implements TitularDetailJdbcDao {

	public TitularDetailJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			TitularDetailEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new TitularDetailSetter(setterAdapterType, entity);
	}

}