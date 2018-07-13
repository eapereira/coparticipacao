package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class TitularSetter extends PreparedStatementSetterAdapter<TitularEntity> {

	private static final int COL_NR_MATRICULA = 1;
	private static final int COL_NM_TITULAR = 2;
	private static final int COL_NR_CPF = 3;
	private static final int COL_DT_NASCIMENTO = 4;
	private static final int COL_DT_ADMISSAO = 5;

	private static final int COL_USER_CREATED = 6;
	private static final int COL_USER_ALTERED = 6;

	public TitularSetter(SetterAdapterType setterAdapterType, TitularEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_NR_MATRICULA, getEntity().getMatricula());
		ps.setString(COL_NM_TITULAR, getEntity().getNameTitular());
		ps.setString(COL_NR_CPF, getEntity().getCpf());
		ps.setDate(COL_DT_NASCIMENTO, DateUtils.dateToSqlDate(getEntity().getDtNascimento()));
		ps.setDate(COL_DT_ADMISSAO, DateUtils.dateToSqlDate(getEntity().getDtAdmissao()));
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_NR_MATRICULA, getEntity().getMatricula());
		ps.setString(COL_NM_TITULAR, getEntity().getNameTitular());
		ps.setString(COL_NR_CPF, getEntity().getCpf());
		ps.setDate(COL_DT_NASCIMENTO, DateUtils.dateToSqlDate(getEntity().getDtNascimento()));
		ps.setDate(COL_DT_ADMISSAO, DateUtils.dateToSqlDate(getEntity().getDtAdmissao()));
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
