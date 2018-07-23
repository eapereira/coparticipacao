package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DesconhecidoDetailSetter
		extends PreparedStatementSetterAdapter<DesconhecidoDetailEntity> {

	private static final int COL_ID_DESCONHECIDO = 1;
	private static final int COL_ID_ARQUIVO_INPUT_COLS_DEF = 2;

	private static final int COL_VL_DOUBLE = 3;
	private static final int COL_VL_INT = 4;
	private static final int COL_VL_DATE = 5;
	private static final int COL_VL_LONG = 6;
	private static final int COL_VL_STRING = 7;

	private static final int COL_USER_CREATED = 8;
	private static final int COL_USER_ALTERED = 8;

	private static final int COL_ID = 9;

	public DesconhecidoDetailSetter(
			SetterAdapterType setterAdapterType,
			DesconhecidoDetailEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps)
			throws SQLException {
		ps.setLong(COL_ID_DESCONHECIDO, getEntity().getDesconhecido().getId());

		ps.setLong(
				COL_ID_ARQUIVO_INPUT_COLS_DEF,
				getEntity().getArquivoInputColsDef().getId());

		setCommonValues(ps);

		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	private void setCommonValues(PreparedStatement ps) throws SQLException {
		if (getEntity().getBigDecimalValue() != null) {
			ps.setBigDecimal(COL_VL_DOUBLE, getEntity().getBigDecimalValue());
		} else {
			ps.setNull(COL_VL_DOUBLE, Types.DOUBLE);
		}

		if (getEntity().getIntValue() != null) {
			ps.setInt(COL_VL_INT, getEntity().getIntValue());
		} else {
			ps.setNull(COL_VL_INT, Types.INTEGER);
		}

		if (getEntity().getDateValue() != null) {
			ps.setDate(
					COL_VL_DATE,
					DateUtils.localDateTimeToSqlDate(
							getEntity().getDateValue()));
		} else {
			ps.setNull(COL_VL_DATE, Types.DATE);
		}

		if (getEntity().getLongValue() != null) {
			ps.setLong(COL_VL_LONG, getEntity().getLongValue());
		} else {
			ps.setNull(COL_VL_LONG, Types.BIGINT);
		}

		if (getEntity().getStringValue() != null) {
			ps.setString(COL_VL_STRING, getEntity().getStringValue());
		} else {
			ps.setNull(COL_VL_STRING, Types.VARCHAR);
		}
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps)
			throws SQLException {
		ps.setLong(COL_ID_DESCONHECIDO, getEntity().getDesconhecido().getId());
		ps.setLong(
				COL_ID_ARQUIVO_INPUT_COLS_DEF,
				getEntity().getArquivoInputColsDef().getId());

		setCommonValues(ps);

		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());		
		ps.setLong(COL_ID, getEntity().getId());
	}

}
