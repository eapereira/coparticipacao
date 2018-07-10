package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class InputDependenteSetter extends PreparedStatementSetterAdapter<InputDependenteEntity> {
	private static final int COL_ID_DEPENDENTE_COLS_DEF = 1;
	private static final int COL_ID_ARQUIVO_INPUT_COLS_DEF = 2;

	private static final int COL_USER_CREATED = 3;
	private static final int COL_USER_ALTERED = 3;

	public InputDependenteSetter(SetterAdapterType setterAdapterType, InputDependenteEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_DEPENDENTE_COLS_DEF, getEntity().getDependenteColsDef().getId());
		ps.setLong(COL_ID_ARQUIVO_INPUT_COLS_DEF, getEntity().getArquivoInputColsDef().getId());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_DEPENDENTE_COLS_DEF, getEntity().getDependenteColsDef().getId());
		ps.setLong(COL_ID_ARQUIVO_INPUT_COLS_DEF, getEntity().getArquivoInputColsDef().getId());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
