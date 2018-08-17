package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.ArquivoInputColsDefJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter.ArquivoInputColsDefSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoInputColsDefJdbcDaoImpl extends AbstractJdbcDaoImpl<ArquivoInputColsDefEntity>
		implements
		ArquivoInputColsDefJdbcDao {
	private static final Logger LOGGER = LogManager.getLogger(AbstractJdbcDaoImpl.class);

	public ArquivoInputColsDefJdbcDaoImpl() throws DaoException {
		super();
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			ArquivoInputColsDefEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new ArquivoInputColsDefSetter(setterAdapterType, entity);
	}

}
