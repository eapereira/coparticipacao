package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class LancamentoSetter extends PreparedStatementSetterAdapter<LancamentoEntity> {
	private static final int COL_ID_TITULAR = 1;
	private static final int COL_ID_DEPENDENTE = 2;
	private static final int COL_ID_CONTRATO = 3;
	private static final int COL_CD_MES = 4;
	private static final int COL_CD_ANO = 5;

	private static final int COL_USER_CREATED = 6;
	private static final int COL_USER_ALTERED = 6;

	public LancamentoSetter(SetterAdapterType setterAdapterType, LancamentoEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_TITULAR, getEntity().getTitular().getId());
		ps.setLong(COL_ID_DEPENDENTE, getEntity().getDependente().getId());
		ps.setLong(COL_ID_CONTRATO, getEntity().getContrato().getId());
		ps.setInt(COL_CD_MES, getEntity().getMes());
		ps.setInt(COL_CD_ANO, getEntity().getAno());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_TITULAR, getEntity().getTitular().getId());
		ps.setLong(COL_ID_DEPENDENTE, getEntity().getDependente().getId());
		ps.setLong(COL_ID_CONTRATO, getEntity().getContrato().getId());
		ps.setInt(COL_CD_MES, getEntity().getMes());
		ps.setInt(COL_CD_ANO, getEntity().getAno());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
