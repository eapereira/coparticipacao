package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularIsentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class TitularIsentoSetter extends PreparedStatementSetterAdapter<TitularIsentoEntity> {

	private static final int COL_ID_TITULAR = 1;
	private static final int COL_TP_ISENTO = 2;
	private static final int COL_MES = 3;
	private static final int COL_ANO = 4;
	private static final int COL_VL_ISENCAO = 5;
	private static final int COL_ID_CONTRATO = 6;

	private static final int COL_USER_CREATED = 7;
	private static final int COL_USER_ALTERED = 7;

	private static final int COL_ID = 8;

	public TitularIsentoSetter(SetterAdapterType setterAdapterType, TitularIsentoEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID, getEntity().getId());

		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

	@Override
	protected void setCommonValues(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_TITULAR, getEntity().getTitular().getId());
		ps.setInt(COL_TP_ISENTO, getEntity().getIsentoType().getId());
		ps.setInt(COL_MES, getEntity().getMes());
		ps.setInt(COL_ANO, getEntity().getAno());

		if (getEntity().getValorIsencao() != null) {
			ps.setBigDecimal(COL_VL_ISENCAO, getEntity().getValorIsencao());
		} else {
			ps.setNull(COL_VL_ISENCAO, Types.DOUBLE);
		}

		ps.setLong(COL_ID_CONTRATO, getEntity().getContrato().getId());
	}

}
