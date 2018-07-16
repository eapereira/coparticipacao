package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class DependenteIsentoSetter extends PreparedStatementSetterAdapter<DependenteIsentoEntity> {
	private static final int COL_ID_DEPENDENTE = 1;
	private static final int COL_ID_ISENTO = 2;

	private static final int COL_USER_CREATED = 3;
	private static final int COL_USER_ALTERED = 3;

	public DependenteIsentoSetter(SetterAdapterType setterAdapterType, DependenteIsentoEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_DEPENDENTE, getEntity().getDependente().getId());
		ps.setLong(COL_ID_ISENTO, getEntity().getIsentoType().getId());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_DEPENDENTE, getEntity().getDependente().getId());
		ps.setLong(COL_ID_ISENTO, getEntity().getIsentoType().getId());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
