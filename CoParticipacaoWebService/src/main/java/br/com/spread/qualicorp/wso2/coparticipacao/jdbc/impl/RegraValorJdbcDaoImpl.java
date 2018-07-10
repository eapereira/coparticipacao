package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraValorEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.RegraValorJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.RegraValorSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraValorJdbcDaoImpl extends AbstractJdbcDaoImpl<RegraValorEntity>
		implements
		RegraValorJdbcDao {

	public RegraValorJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			RegraValorEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new RegraValorSetter(setterAdapterType, entity);
	}

}
