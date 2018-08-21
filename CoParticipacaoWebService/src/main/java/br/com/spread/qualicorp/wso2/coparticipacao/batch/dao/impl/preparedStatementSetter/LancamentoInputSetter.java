package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class LancamentoInputSetter extends PreparedStatementSetterAdapter<LancamentoInputEntity> {

	private static final int COL_ID_ARQUIVO_INPUT = 1;

	private static final int COL_USER_CREATED = 2;
	private static final int COL_USER_ALTERED = 2;

	private static final int COL_ID = 3;

	public LancamentoInputSetter(SetterAdapterType setterAdapterType, LancamentoInputEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_ARQUIVO_INPUT, getEntity().getArquivoInput().getId());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_ARQUIVO_INPUT, getEntity().getArquivoInput().getId());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());

		ps.setLong(COL_ID, getEntity().getId());
	}

}
