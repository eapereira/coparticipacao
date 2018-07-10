package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputColsDefEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class ArquivoInputColsDefSetter extends PreparedStatementSetterAdapter<ArquivoInputColsDefEntity> {

	private static final int COL_ID_ARQUIVO_INPUT = 1;
	private static final int COL_NM_COLUMN = 2;
	private static final int COL_CD_TYPE = 3;
	private static final int COL_VL_LENGTH = 4;
	private static final int COL_CD_ORDEM = 5;
	private static final int COL_USER_CREATED = 6;
	private static final int COL_USER_ALTERED = 6;

	public ArquivoInputColsDefSetter(SetterAdapterType setterAdapterType, ArquivoInputColsDefEntity entity) {
		super(setterAdapterType, entity);
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_ARQUIVO_INPUT, getEntity().getArquivoInput().getId());
		ps.setString(COL_NM_COLUMN, getEntity().getNameColumn());
		ps.setInt(COL_CD_TYPE, getEntity().getType().getId());
		ps.setInt(COL_VL_LENGTH, getEntity().getLength());
		ps.setInt(COL_CD_ORDEM, getEntity().getOrdem());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_ARQUIVO_INPUT, getEntity().getArquivoInput().getId());
		ps.setString(COL_NM_COLUMN, getEntity().getNameColumn());
		ps.setInt(COL_CD_TYPE, getEntity().getType().getId());
		ps.setInt(COL_VL_LENGTH, getEntity().getLength());
		ps.setInt(COL_CD_ORDEM, getEntity().getOrdem());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

}
