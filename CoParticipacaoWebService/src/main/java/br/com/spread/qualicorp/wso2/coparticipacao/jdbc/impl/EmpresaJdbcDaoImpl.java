package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.EmpresaJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.EmpresaSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class EmpresaJdbcDaoImpl extends AbstractJdbcDaoImpl<EmpresaEntity>
		implements
		EmpresaJdbcDao {

	public EmpresaJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			EmpresaEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new EmpresaSetter(setterAdapterType, entity);
	}

}
