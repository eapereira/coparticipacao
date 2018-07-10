package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputSheetEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class ArquivoOutputSheetSetter extends PreparedStatementSetterAdapter<ArquivoOutputSheetEntity> {

	private static final int COL_ID_ARQUIVO_OUTPUT = 1;
	private static final int COL_ID_VIEW_DESTINATION = 2;
	private static final int COL_NM_SHEET = 3;

	private static final int COL_USER_CREATED = 4;
	private static final int COL_USER_ALTERED = 4;

	public ArquivoOutputSheetSetter(SetterAdapterType setterAdapterType, ArquivoOutputSheetEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_ARQUIVO_OUTPUT, getEntity().getArquivoOutput().getId());
		ps.setLong(COL_ID_VIEW_DESTINATION, getEntity().getViewDestination().getId());
		ps.setString(COL_NM_SHEET, getEntity().getNmSheet());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_ARQUIVO_OUTPUT, getEntity().getArquivoOutput().getId());
		ps.setLong(COL_ID_VIEW_DESTINATION, getEntity().getViewDestination().getId());
		ps.setString(COL_NM_SHEET, getEntity().getNmSheet());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
