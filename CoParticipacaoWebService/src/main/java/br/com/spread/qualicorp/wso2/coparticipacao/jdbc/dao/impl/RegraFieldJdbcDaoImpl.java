package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraFieldEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.RegraFieldJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter.RegraFieldSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraFieldJdbcDaoImpl extends AbstractJdbcDaoImpl<RegraFieldEntity>
		implements
		RegraFieldJdbcDao {

	public RegraFieldJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			RegraFieldEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new RegraFieldSetter(setterAdapterType, entity);
	}

}
