package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ViewDestinationColsDefEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class ViewDestinationColsDefSetter extends PreparedStatementSetterAdapter<ViewDestinationColsDefEntity> {

	private static final int COL_ID = 1;

	private static final int COL_ID_VIEW_DESTINATION = 1;
	private static final int COL_NM_COLUMN = 2;

	private static final int COL_USER_CREATED = 3;
	private static final int COL_USER_ALTERED = 3;

	public ViewDestinationColsDefSetter(

			SetterAdapterType setterAdapterType,
			ViewDestinationColsDefEntity entity) {
		super(setterAdapterType, entity);
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_VIEW_DESTINATION, getEntity().getViewDestination().getId());
		ps.setString(COL_NM_COLUMN, getEntity().getNameColumn());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_VIEW_DESTINATION, getEntity().getViewDestination().getId());
		ps.setString(COL_NM_COLUMN, getEntity().getNameColumn());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

	@Override
	protected void setValuesForDelete(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID, getEntity().getId());
	}

}
