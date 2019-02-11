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

	private static final int COL_DT_DEMISSAO = 115;

	private static final int COL_USER_CREATED = 116;
	private static final int COL_USER_ALTERED = 116;

	private static final int COL_ID = 117;

	public TitularSetter(SetterAdapterType setterAdapterType, TitularEntity entity) {
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

	protected void setCommonValues(PreparedStatement ps) throws SQLException {
		setLong(ps, COL_ID_CONTRATO, getEntity().getContrato().getId());
		setLong(ps, COL_NR_MATRICULA, getEntity().getMatricula());
		setString(ps, COL_NM_TITULAR, getEntity().getNameTitular());
		setLong(ps, COL_NR_CPF, getEntity().getCpf());
		setDate(ps, COL_DT_NASCIMENTO, getEntity().getDtNascimento());
		setDate(ps, COL_DT_ADMISSAO, getEntity().getDtAdmissao());
		setString(ps, COL_NM_LABEL, getEntity().getLabel());
		setLong(ps, COL_NR_REF_CODE, getEntity().getReferenceCode());
		setLong(ps, COL_NR_MATRICULA_EMPRESA, getEntity().getMatriculaEmpresa());

		setBeneficiarioDetailValues(ps, getEntity().getBeneficiarioDetail());

		setDate(ps, COL_DT_DEMISSAO, getEntity().getDtDemissao());
	}

}
