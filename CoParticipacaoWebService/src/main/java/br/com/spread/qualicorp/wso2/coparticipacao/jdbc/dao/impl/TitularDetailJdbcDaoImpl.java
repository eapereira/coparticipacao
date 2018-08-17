package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.TitularDetailJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter.TitularDetailSetter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class TitularDetailJdbcDaoImpl extends AbstractJdbcDaoImpl<TitularDetailEntity> implements TitularDetailJdbcDao {

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
