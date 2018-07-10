package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputSheetColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.ArquivoOutputSheetColsDefJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.ArquivoOutputSheetColsDefSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoOutputSheetColsDefJdbcDaoImpl extends AbstractJdbcDaoImpl<ArquivoOutputSheetColsDefEntity>
		implements
		ArquivoOutputSheetColsDefJdbcDao {

	public ArquivoOutputSheetColsDefJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			ArquivoOutputSheetColsDefEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new ArquivoOutputSheetColsDefSetter(setterAdapterType, entity);
	}

}
