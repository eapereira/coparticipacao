package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ExecucaoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class ExecucaoSetter extends PreparedStatementSetterAdapter<ExecucaoEntity> {

	private static final int COL_ID_EMPRESA = 1;
	private static final int COL_TP_STATUS = 2;

	private static final int COL_USER_CREATED = 3;
	private static final int COL_USER_ALTERED = 3;

	private static final int COL_ID = 4;

	public ExecucaoSetter(SetterAdapterType setterAdapterType, ExecucaoEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		setCommonValues(ps);

		setLong(ps, COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		setCommonValues(ps);

		setLong(ps, COL_USER_ALTERED, getEntity().getUserAltered().getId());
		setLong(ps, COL_ID, getEntity().getId());
	}

	@Override
	protected void setCommonValues(PreparedStatement ps) throws SQLException {
		setLong(ps, COL_ID_EMPRESA, getEntity().getEmpresa().getId());
		setInt(ps, COL_TP_STATUS, getEntity().getExecucaoType().getId());
	}

}
