package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.math.BigDecimal;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.jdbc.core.PreparedStatementSetter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class PreparedStatementSetterAdapter<ENTITY extends AbstractDomain> implements PreparedStatementSetter {

	private static final int COL_ID = 1;

	private SetterAdapterType setterAdapterType;

	private ENTITY entity;

	public PreparedStatementSetterAdapter(SetterAdapterType setterAdapterType, ENTITY entity) {
		this.setterAdapterType = setterAdapterType;
		this.entity = entity;
	}

	public void setValues(PreparedStatement ps) throws SQLException {
		if (SetterAdapterType.INSERT.equals(setterAdapterType)) {
			setCommonValues(ps);
			setValuesForInsert(ps);
		} else if (SetterAdapterType.UPDATE.equals(setterAdapterType)) {
			setCommonValues(ps);
			setValuesForUpdate(ps);
		} else if (SetterAdapterType.DELETE.equals(setterAdapterType)) {
			setValuesForDelete(ps);
		} else if (SetterAdapterType.SELECT.equals(setterAdapterType)) {
			setValuesForSelect(ps);
		}
	}

	protected abstract void setValuesForInsert(PreparedStatement ps) throws SQLException;

	protected abstract void setValuesForUpdate(PreparedStatement ps) throws SQLException;

	protected void setValuesForSelect(PreparedStatement ps) throws SQLException {

	}

	protected void setValuesForDelete(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID, getEntity().getId());
	}

	public ENTITY getEntity() {
		return entity;
	}

	public void setEntity(ENTITY entity) {
		this.entity = entity;
	}

	protected void setCommonValues(PreparedStatement ps) throws SQLException {

	}

	/**
	 * Atualmente esta operação não é suportada pelo driver do
	 * <code>MySQL</code>.
	 * 
	 * @param ps
	 * @param index
	 * @param value
	 * @throws SQLException
	 *             Se ocorrer algum erro, notificaremos o usuário.
	 */
	public void setValue(PreparedStatement ps, int index, Object value) throws SQLException {
		ParameterMetaData parameterMetaData = ps.getParameterMetaData();
		int columnType = parameterMetaData.getParameterType(index);

		if (value != null) {
			switch (columnType) {
			case Types.VARCHAR:
				ps.setString(index, (String) value);
				break;
			case Types.INTEGER:
				ps.setInt(index, (Integer) value);
				break;
			case Types.BIGINT:
				ps.setLong(index, (Long) value);
				break;
			case Types.DOUBLE:
			case Types.DECIMAL:
			case Types.NUMERIC:
				ps.setBigDecimal(index, (BigDecimal) value);
				break;
			case Types.DATE:
				ps.setDate(index, DateUtils.dateToSqlDate((LocalDate) value));
				break;
			case Types.TIMESTAMP:
				ps.setTimestamp(index, DateUtils.dateTimeToTimestamp((LocalDateTime) value));
				break;
			case Types.TIME:
				ps.setTime(index, DateUtils.localTimeToTime((LocalTime) value));
			}
		} else {
			ps.setNull(index, columnType);
		}
	}

	protected void setInt(PreparedStatement ps, int index, Integer value) throws SQLException {
		if (value != null) {
			ps.setInt(index, value);
		} else {
			ps.setNull(index, Types.INTEGER);
		}
	}

	protected void setLong(PreparedStatement ps, int index, Long value) throws SQLException {
		if (value != null) {
			ps.setLong(index, value);
		} else {
			ps.setNull(index, Types.BIGINT);
		}
	}

	protected void setBigDecimal(PreparedStatement ps, int index, BigDecimal value) throws SQLException {
		if (value != null) {
			ps.setBigDecimal(index, value);
		} else {
			ps.setNull(index, Types.DOUBLE);
		}
	}

	protected void setDate(PreparedStatement ps, int index, LocalDate value) throws SQLException {
		if (value != null) {
			ps.setDate(index, DateUtils.localDateToSqlDate(value));
		} else {
			ps.setNull(index, Types.DATE);
		}
	}

	protected void setTimestamp(PreparedStatement ps, int index, LocalDateTime value) throws SQLException {
		if (value != null) {
			ps.setTimestamp(index, DateUtils.dateTimeToTimestamp(value));
		} else {
			ps.setNull(index, Types.TIMESTAMP);
		}
	}

	protected void setString(PreparedStatement ps, int index, String value) throws SQLException {
		if (value != null) {
			ps.setString(index, value);
		} else {
			ps.setNull(index, Types.VARCHAR);
		}
	}

	protected void setBoolean(PreparedStatement ps, int index, Boolean value) throws SQLException {
		if (value != null) {
			if (Boolean.TRUE.equals(value)) {
				ps.setInt(index, NumberUtils.INTEGER_ONE);
			} else {
				ps.setInt(index, NumberUtils.INTEGER_ZERO);
			}
		} else {
			ps.setNull(index, Types.INTEGER);
		}
	}

}
