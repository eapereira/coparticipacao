package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ContratoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class ContratoSetter extends PreparedStatementSetterAdapter<ContratoEntity> {

	private static final int COL_ID_EMPRESA = 1;
	private static final int COL_CD_CONTRATO = 2;
	private static final int COL_NM_CONTRATO = 3;

	private static final int COL_USER_CREATED = 4;
	private static final int COL_USER_ALTERED = 4;

	public ContratoSetter(SetterAdapterType setterAdapterType, ContratoEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_EMPRESA, getEntity().getEmpresa().getId());
		ps.setString(COL_CD_CONTRATO, getEntity().getCdContrato());
		ps.setString(COL_NM_CONTRATO, getEntity().getNameContrato());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_EMPRESA, getEntity().getEmpresa().getId());
		ps.setString(COL_CD_CONTRATO, getEntity().getCdContrato());
		ps.setString(COL_NM_CONTRATO, getEntity().getNameContrato());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserCreated().getId());
	}


}
