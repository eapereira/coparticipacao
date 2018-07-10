package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.LancamentoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.LancamentoSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoJdbcDaoImpl extends AbstractJdbcDaoImpl<LancamentoEntity>
		implements
		LancamentoJdbcDao {

	public LancamentoJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			LancamentoEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new LancamentoSetter(setterAdapterType, entity);
	}

}
