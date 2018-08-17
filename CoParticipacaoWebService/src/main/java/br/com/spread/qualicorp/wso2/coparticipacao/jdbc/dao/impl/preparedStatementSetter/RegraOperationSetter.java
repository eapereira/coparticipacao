package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraOperationEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class RegraOperationSetter extends PreparedStatementSetterAdapter<RegraOperationEntity> {

	private static final int COL_ID_REGRA = 1;
	private static final int COL_TP_OPERATION = 2;

	private static final int COL_USER_CREATED = 3;
	private static final int COL_USER_ALTERED = 3;

	public RegraOperationSetter(SetterAdapterType setterAdapterType, RegraOperationEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_REGRA, getEntity().getRegra().getId());
		ps.setInt(COL_TP_OPERATION, getEntity().getTpOperation().getId());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_REGRA, getEntity().getRegra().getId());
		ps.setInt(COL_TP_OPERATION, getEntity().getTpOperation().getId());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
