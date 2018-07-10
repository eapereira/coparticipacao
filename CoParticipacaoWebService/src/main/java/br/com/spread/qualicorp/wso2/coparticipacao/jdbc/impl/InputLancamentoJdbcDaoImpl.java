package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputLancamentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.InputLancamentoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.InputLancamentoSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputLancamentoJdbcDaoImpl extends AbstractJdbcDaoImpl<InputLancamentoEntity>
		implements
		InputLancamentoJdbcDao {

	public InputLancamentoJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			InputLancamentoEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new InputLancamentoSetter(setterAdapterType, entity);
	}

}
