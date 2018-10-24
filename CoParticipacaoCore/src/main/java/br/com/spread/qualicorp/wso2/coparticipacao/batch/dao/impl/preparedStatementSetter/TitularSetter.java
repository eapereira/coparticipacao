package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class TitularSetter extends BeneficiarioSetter<TitularEntity> {

	private static final int COL_ID_CONTRATO = 1;
	private static final int COL_NR_MATRICULA = 2;
	private static final int COL_NM_TITULAR = 3;
	private static final int COL_NR_CPF = 4;
	private static final int COL_DT_NASCIMENTO = 5;
	private static final int COL_DT_ADMISSAO = 6;
	private static final int COL_NM_LABEL = 7;
	private static final int COL_NR_REF_CODE = 8;
	private static final int COL_NR_MATRICULA_EMPRESA = 9;

	private static final int COL_DT_DEMISSAO = 101;

	private static final int COL_USER_CREATED = 102;
	private static final int COL_USER_ALTERED = 102;

	private static final int COL_ID = 103;

	public TitularSetter(SetterAdapterType setterAdapterType, TitularEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		setCommonValues(ps);

		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		setCommonValues(ps);

		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());

		ps.setLong(COL_ID, getEntity().getId());
	}

	protected void setCommonValues(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_CONTRATO, getEntity().getContrato().getId());
		ps.setLong(COL_NR_MATRICULA, getEntity().getMatricula());
		ps.setString(COL_NM_TITULAR, getEntity().getNameTitular());
		ps.setLong(COL_NR_CPF, getEntity().getCpf());
		ps.setDate(COL_DT_NASCIMENTO, DateUtils.dateToSqlDate(getEntity().getDtNascimento()));
		ps.setDate(COL_DT_ADMISSAO, DateUtils.dateToSqlDate(getEntity().getDtAdmissao()));

		if (StringUtils.isNotBlank(getEntity().getLabel())) {
			ps.setString(COL_NM_LABEL, getEntity().getLabel());
		} else {
			ps.setNull(COL_NM_LABEL, Types.VARCHAR);
		}

		if (getEntity().getReferenceCode() != null) {
			ps.setLong(COL_NR_REF_CODE, getEntity().getReferenceCode());
		} else {
			ps.setNull(COL_NR_REF_CODE, Types.BIGINT);
		}

		if (getEntity().getMatriculaEmpresa() != null) {
			ps.setLong(COL_NR_MATRICULA_EMPRESA, getEntity().getMatriculaEmpresa());
		} else {
			ps.setNull(COL_NR_MATRICULA_EMPRESA, Types.BIGINT);
		}

		setBeneficiarioDetailValues(ps, getEntity().getBeneficiarioDetail());

		if (getEntity().getDtDemissao() != null) {
			ps.setDate(COL_DT_DEMISSAO, DateUtils.dateToSqlDate(getEntity().getDtDemissao()));
		} else {
			ps.setNull(COL_DT_DEMISSAO, Types.DATE);
		}
	}

}
