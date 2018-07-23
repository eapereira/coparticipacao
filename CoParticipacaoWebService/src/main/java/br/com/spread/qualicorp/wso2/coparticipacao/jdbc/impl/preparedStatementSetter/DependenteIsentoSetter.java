package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class DependenteIsentoSetter
		extends PreparedStatementSetterAdapter<DependenteIsentoEntity> {
	private static final int COL_ID_DEPENDENTE = 1;
	private static final int COL_TP_ISENTO = 2;
	private static final int COL_MES = 3;
	private static final int COL_ANO = 4;

	private static final int COL_USER_CREATED = 5;
	private static final int COL_USER_ALTERED = 5;

	private static final int COL_ID = 6;

	public DependenteIsentoSetter(
			SetterAdapterType setterAdapterType,
			DependenteIsentoEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps)
			throws SQLException {
		ps.setLong(COL_ID_DEPENDENTE, getEntity().getDependente().getId());
		ps.setLong(COL_TP_ISENTO, getEntity().getIsentoType().getId());
		ps.setInt(COL_MES, getEntity().getMes());
		ps.setInt(COL_ANO, getEntity().getAno());

		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps)
			throws SQLException {
		ps.setLong(COL_ID, getEntity().getId());

		ps.setLong(COL_ID_DEPENDENTE, getEntity().getDependente().getId());
		ps.setLong(COL_TP_ISENTO, getEntity().getIsentoType().getId());
		ps.setInt(COL_MES, getEntity().getMes());
		ps.setInt(COL_ANO, getEntity().getAno());

		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
