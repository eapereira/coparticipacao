package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DynamicSetter extends PreparedStatementSetterAdapter<DynamicEntity>{

	public DynamicSetter(
			SetterAdapterType setterAdapterType,
			DynamicEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
