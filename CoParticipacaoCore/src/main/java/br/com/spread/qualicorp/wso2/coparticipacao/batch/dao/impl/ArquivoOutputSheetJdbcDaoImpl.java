package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.ArquivoOutputSheetJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.ArquivoOutputSheetSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputSheetEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoOutputSheetJdbcDaoImpl extends AbstractBatchDaoImpl<ArquivoOutputSheetEntity>
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
