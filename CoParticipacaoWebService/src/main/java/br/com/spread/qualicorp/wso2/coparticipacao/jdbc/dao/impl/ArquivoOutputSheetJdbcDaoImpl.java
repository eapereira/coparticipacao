package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputSheetEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.ArquivoOutputSheetJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter.ArquivoOutputSheetSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter.SetterAdapterType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoOutputSheetJdbcDaoImpl extends AbstractJdbcDaoImpl<ArquivoOutputSheetEntity>
		implements
		ArquivoOutputSheetJdbcDao {

	public ArquivoOutputSheetJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			ArquivoOutputSheetEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new ArquivoOutputSheetSetter(setterAdapterType, entity);
	}

}
