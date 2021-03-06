package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.ViewDestinationColsDefJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.ViewDestinationColsDefSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ViewDestinationColsDefEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class ViewDestinationColsDefJdbcDaoImpl extends AbstractBatchDaoImpl<ViewDestinationColsDefEntity>
		implements
		ViewDestinationColsDefJdbcDao {

	public ViewDestinationColsDefJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			ViewDestinationColsDefEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new ViewDestinationColsDefSetter(setterAdapterType, entity);
	}

}
