package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.HashMap;
import java.util.Map;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;

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
	
	private Map<Long, Object> mapColumns;

	public DynamicEntity() {
		mapColumns = new HashMap<Long, Object>();
	}

	public Map<Long, Object> getMapColumns() {
		return mapColumns;
	}

	public void setMapColumns(Map<Long, Object> mapColumns) {
		this.mapColumns = mapColumns;
	}

	public void addColumn(Long id, Object value) throws DaoException {
		getMapColumns().put(id, value);
	}

	public Object findColumnValue(Long id) {
		if (getMapColumns().containsKey(id)) {
			return getMapColumns().get(id);
		}

		return null;
	}
}
