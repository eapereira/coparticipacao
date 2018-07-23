package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.LancamentoDetailJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.LancamentoDetailSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoDetailJdbcDaoImpl
		extends AbstractJdbcDaoImpl<LancamentoDetailEntity>
		implements LancamentoDetailJdbcDao {

	public LancamentoDetailJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			LancamentoDetailEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new LancamentoDetailSetter(setterAdapterType, entity);
	}

}
