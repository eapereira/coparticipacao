package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.batchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.PreparedStatementSetterAdapter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class BatchPreparedStatementSetterAdapter<ENTITY extends AbstractDomain>
		implements BatchPreparedStatementSetter {

	private List<ENTITY> entities;

	private PreparedStatementSetterAdapter<ENTITY> preparedStatementSetterAdapter;

	public BatchPreparedStatementSetterAdapter(
			PreparedStatementSetterAdapter<ENTITY> preparedStatementSetterAdapter,
			List<ENTITY> entities) {
		this.entities = entities;
		this.preparedStatementSetterAdapter = preparedStatementSetterAdapter;
	}

	public void setValues(PreparedStatement ps, int i) throws SQLException {
		ENTITY entity = entities.get(i);

		preparedStatementSetterAdapter.setEntity(entity);
		preparedStatementSetterAdapter.setValues(ps);
	}

	public int getBatchSize() {
		return entities.size();
	}

}
