package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.ExecucaoBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.ExecucaoSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ExecucaoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class ExecucaoBatchDaoImpl extends AbstractBatchDaoImpl<ExecucaoEntity> implements ExecucaoBatchDao {

	private static final Logger LOGGER = LogManager.getLogger(ExecucaoBatchDaoImpl.class);

	public ExecucaoBatchDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			ExecucaoEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new ExecucaoSetter(setterAdapterType, entity);
	}

}
