package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.LancamentoInputJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter.LancamentoInputSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoInputJdbcDaoImpl extends AbstractJdbcDaoImpl<LancamentoInputEntity>
		implements
		LancamentoInputJdbcDao {

	public LancamentoInputJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			LancamentoInputEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new LancamentoInputSetter(setterAdapterType, entity);
	}

}
