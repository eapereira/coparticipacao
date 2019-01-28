package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DynamicEntityRowMapper implements RowMapper<DynamicEntity> {

	private static final Logger LOGGER = LogManager.getLogger(DynamicEntityRowMapper.class);

	public DynamicEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		DynamicEntity dynamicEntity = new DynamicEntity();
		ResultSetMetaData metaData = rs.getMetaData();
		String columnName;
		Object value;
		int columnType;
		LocalDate localDate;

		for (int col = 1; col < metaData.getColumnCount(); col++) {
			columnName = metaData.getColumnName(col);
			columnType = metaData.getColumnType(col);

			value = null;

			switch (columnType) {
			case Types.INTEGER:
				value = rs.getInt(col);
				break;
			case Types.BIGINT:
				value = rs.getLong(col);
				break;
			case Types.DECIMAL:
			case Types.DOUBLE:
				value = rs.getBigDecimal(col);
				break;
			case Types.DATE:
				/*
				 * Gambiarra - BUG no MySQL faz ele retornar as data com um dia
				 * a menos:
				 */
				localDate = DateUtils.dateToLocalDate(rs.getDate(col));

				if (localDate != null) {
					value = localDate.plusDays(NumberUtils.INTEGER_ONE);
				}
				break;
			case Types.TIMESTAMP:
				value = DateUtils.timestampToLocalDateTime(rs.getTimestamp(col));
				break;
			case Types.CHAR:
			case Types.VARCHAR:
				value = rs.getString(col);
				break;
			}

			LOGGER.info("Reading data from PreparedStatement with columnName [{}] and value [{}]:", columnName, value);

			dynamicEntity.addColumn(columnName, value);
		}

		return dynamicEntity;
	}

}
