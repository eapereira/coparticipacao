package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DynamicSetter
		extends PreparedStatementSetterAdapter<DynamicEntity> {

	private static final int COL_ID_CONTRATO = 1;
	private static final int COL_MES = 2;
	private static final int COL_ANO = 3;

	public DynamicSetter(
			SetterAdapterType setterAdapterType,
			DynamicEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps)
			throws SQLException {

	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setValuesForSelect(PreparedStatement ps)
			throws SQLException {
		ps.setLong(COL_ID_CONTRATO, getEntity().getColumnAsLong("ID_CONTRATO"));
		ps.setInt(COL_MES, getEntity().getColumnAsInteger("CD_MES"));
		ps.setInt(COL_ANO, getEntity().getColumnAsInteger("CD_ANO"));

	}
}
