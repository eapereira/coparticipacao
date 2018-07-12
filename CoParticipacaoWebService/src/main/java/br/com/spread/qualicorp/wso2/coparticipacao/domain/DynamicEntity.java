package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DynamicEntity extends AbstractDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6130898664447953695L;

	private Map<String, Object> mapColumns;

	public DynamicEntity() {
		mapColumns = new HashMap<String, Object>();
	}

	public Map<String, Object> getMapColumns() {
		return mapColumns;
	}

	public void setMapColumns(Map<String, Object> mapColumns) {
		this.mapColumns = mapColumns;
	}

	public void addColumn(String id, Object value) {
		getMapColumns().put(id, value);
	}

	public Object findColumnValue(Long id) {
		if (getMapColumns().containsKey(id)) {
			return getMapColumns().get(id);
		}

		return null;
	}

	public Object getColumnValue(String columnName) {
		if (getMapColumns().containsKey(columnName)) {
			return getMapColumns().get(columnName);
		}

		return null;
	}

	public Long getColumnAsLong(String columnName) {
		return (Long) getColumnValue(columnName);
	}

	public Integer getColumnAsInteger(String columnName) {
		return (Integer) getColumnValue(columnName);
	}

	public BigDecimal getColumnAsBigDecimal(String columnName) {
		return (BigDecimal) getColumnValue(columnName);
	}

	public LocalDate getColumnAsDate(String columnName) {
		return (LocalDate) getColumnValue(columnName);
	}

	public String getColumnAsString(String columnName) {
		return (String) getColumnValue(columnName);
	}

}
