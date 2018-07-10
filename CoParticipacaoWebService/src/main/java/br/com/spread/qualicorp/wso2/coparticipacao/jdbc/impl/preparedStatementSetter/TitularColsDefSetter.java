package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularColsDefEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class TitularColsDefSetter extends PreparedStatementSetterAdapter<TitularColsDefEntity> {

	private static final int COL_NM_COLUMN = 1;
	private static final int COL_CD_TYPE = 2;
	private static final int COL_VL_LENGTH = 3;

	private static final int COL_USER_CREATED = 4;
	private static final int COL_USER_ALTERED = 4;

	public TitularColsDefSetter(SetterAdapterType setterAdapterType, TitularColsDefEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setString(COL_NM_COLUMN, getEntity().getNameColumn());
		ps.setInt(COL_CD_TYPE, getEntity().getType().getId());
		ps.setInt(COL_VL_LENGTH, getEntity().getLength());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setString(COL_NM_COLUMN, getEntity().getNameColumn());
		ps.setInt(COL_CD_TYPE, getEntity().getType().getId());
		ps.setInt(COL_VL_LENGTH, getEntity().getLength());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
