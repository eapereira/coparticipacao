package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.DependenteDetailJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter.DependenteDetailSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class DependenteDetailJdbcDaoImpl extends AbstractJdbcDaoImpl<DependenteDetailEntity>
		implements DependenteDetailJdbcDao {

	public DependenteDetailJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			DependenteDetailEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new DependenteDetailSetter(setterAdapterType, entity);
	}

}
