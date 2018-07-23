package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class DependenteSetter
		extends PreparedStatementSetterAdapter<DependenteEntity> {

	private static final int COL_ID_TITULAR = 1;
	private static final int COL_TP_DEPENDENTE = 2;
	private static final int COL_NM_DEPENDENTE = 3;
	private static final int COL_NR_CPF = 4;
	private static final int COL_DT_NASCIMENTO = 5;
	private static final int COL_NR_MATRICULA = 6;

	private static final int COL_USER_CREATED = 7;
	private static final int COL_USER_ALTERED = 7;

	private static final int COL_ID = 7;

	public DependenteSetter(
			SetterAdapterType setterAdapterType,
			DependenteEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps)
			throws SQLException {
		ps.setLong(COL_ID_TITULAR, getEntity().getTitular().getId());
		ps.setInt(COL_TP_DEPENDENTE, getEntity().getTpDependente().getId());
		ps.setString(COL_NM_DEPENDENTE, getEntity().getNameDependente());
		ps.setString(COL_NR_CPF, getEntity().getCpf());
		ps.setDate(
				COL_DT_NASCIMENTO,
				DateUtils.dateToSqlDate(getEntity().getDtNascimento()));
		ps.setLong(COL_NR_MATRICULA, getEntity().getMatricula());

		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps)
			throws SQLException {
		ps.setLong(COL_ID, getEntity().getId());

		ps.setLong(COL_ID_TITULAR, getEntity().getTitular().getId());
		ps.setInt(COL_TP_DEPENDENTE, getEntity().getTpDependente().getId());
		ps.setString(COL_NM_DEPENDENTE, getEntity().getNameDependente());
		ps.setString(COL_NR_CPF, getEntity().getCpf());
		ps.setDate(
				COL_DT_NASCIMENTO,
				DateUtils.dateToSqlDate(getEntity().getDtNascimento()));
		ps.setLong(COL_NR_MATRICULA, getEntity().getMatricula());

		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
