package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ParameterEntity;

/**
 * 
 * @author <a href="mailto:edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class ParameterSetter extends PreparedStatementSetterAdapter<ParameterEntity> {

	private static final int COL_ID_EMPRESA = 1;
	private static final int COL_NM_PARAMETER = 2;
	private static final int COL_VL_PARAMETER = 3;

	private static final int COL_USER_CREATED = 4;
	private static final int COL_USER_ALTERED = 4;

	public ParameterSetter(SetterAdapterType setterAdapterType, ParameterEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_EMPRESA, getEntity().getEmpresa().getId());
		ps.setString(COL_NM_PARAMETER, getEntity().getNameParameter());
		ps.setString(COL_VL_PARAMETER, getEntity().getValue());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_EMPRESA, getEntity().getEmpresa().getId());
		ps.setString(COL_NM_PARAMETER, getEntity().getNameParameter());
		ps.setString(COL_VL_PARAMETER, getEntity().getValue());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
