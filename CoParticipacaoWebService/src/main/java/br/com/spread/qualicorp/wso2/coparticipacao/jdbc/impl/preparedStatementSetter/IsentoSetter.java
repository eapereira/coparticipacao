package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.IsentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class IsentoSetter extends PreparedStatementSetterAdapter<IsentoEntity> {

	private static final int COL_NM_ISENTO = 1;

	private static final int COL_USER_CREATED = 2;
	private static final int COL_USER_ALTERED = 2;

	public IsentoSetter(SetterAdapterType setterAdapterType, IsentoEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setString(COL_NM_ISENTO, getEntity().getNameIsento());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setString(COL_NM_ISENTO, getEntity().getNameIsento());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
