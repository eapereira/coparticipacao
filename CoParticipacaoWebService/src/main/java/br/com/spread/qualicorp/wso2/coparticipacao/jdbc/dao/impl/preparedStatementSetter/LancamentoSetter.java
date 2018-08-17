package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

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
	private static final int COL_VL_PRINCIPAL = 6;

	private static final int COL_USER_CREATED = 7;
	private static final int COL_USER_ALTERED = 7;

	private static final int COL_ID = 8;

	public LancamentoSetter(SetterAdapterType setterAdapterType, LancamentoEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		setCommonsValues(ps);

		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	private void setCommonsValues(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_TITULAR, getEntity().getTitular().getId());

		if (getEntity().getDependente() != null) {
			ps.setLong(COL_ID_DEPENDENTE, getEntity().getDependente().getId());
		} else {
			ps.setNull(COL_ID_DEPENDENTE, Types.BIGINT);
		}

		ps.setLong(COL_ID_CONTRATO, getEntity().getContrato().getId());
		ps.setInt(COL_CD_MES, getEntity().getMes());
		ps.setInt(COL_CD_ANO, getEntity().getAno());
		ps.setBigDecimal(COL_VL_PRINCIPAL, getEntity().getValorPrincipal());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		setCommonsValues(ps);

		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
		ps.setLong(COL_ID, getEntity().getId());
	}

}
