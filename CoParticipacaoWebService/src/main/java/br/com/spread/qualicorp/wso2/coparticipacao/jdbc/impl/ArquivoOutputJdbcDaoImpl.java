package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.ArquivoOutputJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.ArquivoOutputSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoOutputJdbcDaoImpl extends AbstractJdbcDaoImpl<ArquivoOutputEntity>
		implements
		ArquivoOutputJdbcDao {

	public ArquivoOutputJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			ArquivoOutputEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new ArquivoOutputSetter(setterAdapterType, entity);
	}

}
