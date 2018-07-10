package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.LancamentoColsDefJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.LancamentoColsDefSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoColsDefJdbcDaoImpl extends AbstractJdbcDaoImpl<LancamentoColsDefEntity>
		implements
		LancamentoColsDefJdbcDao {

	public LancamentoColsDefJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			LancamentoColsDefEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new LancamentoColsDefSetter(setterAdapterType, entity);
	}

}
