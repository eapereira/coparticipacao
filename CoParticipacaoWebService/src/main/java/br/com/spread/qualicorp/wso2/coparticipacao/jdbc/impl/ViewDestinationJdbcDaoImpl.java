package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ViewDestinationEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.ViewDestinationJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.ViewDestinationSetter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ViewDestinationJdbcDaoImpl extends AbstractJdbcDaoImpl<ViewDestinationEntity>
		implements
		ViewDestinationJdbcDao {

	public ViewDestinationJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			ViewDestinationEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new ViewDestinationSetter(setterAdapterType, entity);
	}

}
