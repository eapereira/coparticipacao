package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.DesconhecidoDetailJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.DesconhecidoDetailSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class DesconhecidoDetailJdbcDaoImpl
		extends AbstractJdbcDaoImpl<DesconhecidoDetailEntity>
		implements DesconhecidoDetailJdbcDao {

	public DesconhecidoDetailJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			DesconhecidoDetailEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new DesconhecidoDetailSetter(setterAdapterType, entity);
	}

}
