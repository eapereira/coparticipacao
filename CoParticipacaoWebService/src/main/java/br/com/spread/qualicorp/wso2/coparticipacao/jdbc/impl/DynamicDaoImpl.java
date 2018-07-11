package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.DynamicDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class DynamicDaoImpl extends AbstractJdbcDaoImpl<DynamicEntity>
		implements DynamicDao {

	private static final Logger LOGGER = LogManager
			.getLogger(DynamicDaoImpl.class);

	public DynamicDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			DynamicEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
