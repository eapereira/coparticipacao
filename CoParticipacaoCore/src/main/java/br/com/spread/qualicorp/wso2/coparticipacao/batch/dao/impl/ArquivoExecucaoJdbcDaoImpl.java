package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.ArquivoExecucaoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.ArquivoExecucaoSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoExecucaoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoExecucaoJdbcDaoImpl extends AbstractBatchDaoImpl<ArquivoExecucaoEntity>
		implements ArquivoExecucaoJdbcDao {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoExecucaoJdbcDaoImpl.class);

	public ArquivoExecucaoJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			ArquivoExecucaoEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new ArquivoExecucaoSetter(setterAdapterType, entity);
	}

}
