package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.ArquivoInputJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.ArquivoInputSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoInputJdbcDaoImpl extends AbstractJdbcDaoImpl<ArquivoInputEntity>
		implements
		ArquivoInputJdbcDao {

	public ArquivoInputJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			ArquivoInputEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new ArquivoInputSetter(setterAdapterType, entity);
	}

}
