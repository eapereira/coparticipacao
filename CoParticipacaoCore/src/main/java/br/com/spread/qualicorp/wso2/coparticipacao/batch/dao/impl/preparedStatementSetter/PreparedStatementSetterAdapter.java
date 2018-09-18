package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class PreparedStatementSetterAdapter<ENTITY extends AbstractDomain>
		implements PreparedStatementSetter {

	private static final int COL_ID = 1;

	private SetterAdapterType setterAdapterType;

	private ENTITY entity;

	public PreparedStatementSetterAdapter(
			SetterAdapterType setterAdapterType,
			ENTITY entity) {
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

	protected abstract void setValuesForInsert(PreparedStatement ps)
			throws SQLException;

	protected abstract void setValuesForUpdate(PreparedStatement ps)
			throws SQLException;

	protected void setValuesForSelect(PreparedStatement ps)
			throws SQLException {

	}

	protected void setValuesForDelete(PreparedStatement ps)
			throws SQLException {
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
}