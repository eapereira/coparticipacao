package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraValorEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class RegraValorSetter extends PreparedStatementSetterAdapter<RegraValorEntity> {

	private static final int COL_ID_REGRA_OPERATION = 1;
	private static final int COL_VL_REGRA_VALOR = 2;

	private static final int COL_USER_CREATED = 3;
	private static final int COL_USER_ALTERED = 3;

	public RegraValorSetter(SetterAdapterType setterAdapterType, RegraValorEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_REGRA_OPERATION, getEntity().getRegraOperation().getId());
		ps.setBigDecimal(COL_VL_REGRA_VALOR, getEntity().getValor());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_REGRA_OPERATION, getEntity().getRegraOperation().getId());
		ps.setBigDecimal(COL_VL_REGRA_VALOR, getEntity().getValor());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
