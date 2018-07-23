package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DesconhecidoSetter
		extends PreparedStatementSetterAdapter<DesconhecidoEntity> {

	private static final int COL_ID_CONTRATO = 1;
	private static final int COL_CD_MES = 2;
	private static final int COL_CD_ANO = 3;

	private static final int COL_USER_CREATED = 4;
	private static final int COL_USER_ALTERED = 4;
	
	private static final int COL_ID = 5;

	public DesconhecidoSetter(
			SetterAdapterType setterAdapterType,
			DesconhecidoEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps)
			throws SQLException {
		ps.setLong(COL_ID_CONTRATO, getEntity().getContrato().getId());
		ps.setInt(COL_CD_MES, getEntity().getMes());
		ps.setInt(COL_CD_ANO, getEntity().getAno());

		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps)
			throws SQLException {
		ps.setLong(COL_ID, getEntity().getId());
		
		ps.setLong(COL_ID_CONTRATO, getEntity().getContrato().getId());
		ps.setInt(COL_CD_MES, getEntity().getMes());
		ps.setInt(COL_CD_ANO, getEntity().getAno());

		ps.setLong(COL_USER_ALTERED, getEntity().getUserCreated().getId());
	}

}
