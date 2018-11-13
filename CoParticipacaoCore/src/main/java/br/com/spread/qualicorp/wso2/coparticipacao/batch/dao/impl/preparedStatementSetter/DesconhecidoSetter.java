package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DesconhecidoSetter extends PreparedStatementSetterAdapter<DesconhecidoEntity> {

	private static final int COL_ID_CONTRATO = 1;
	private static final int COL_CD_MES = 2;
	private static final int COL_CD_ANO = 3;

	private static final int COL_NR_MATRICULA = 4;
	private static final int COL_NM_BENEFICIARIO = 5;
	private static final int COL_NR_CPF = 6;
	private static final int COL_DT_NASCIMENTO = 7;
	private static final int COL_VL_PRINCIPAL = 8;
	private static final int COL_NR_MATRICULA_EMPRESA = 9;
	private static final int COL_NM_TITULAR = 10;

	private static final int COL_VL_FATOR_MODERADOR = 11;
	private static final int COL_VL_FATOR_MODERADOR_INSS = 12;
	private static final int COL_DESCR_PROFISSAO = 13;
	private static final int COL_NR_MATRICULA_ESPECIAL = 14;
	private static final int COL_NR_SUBFATURA = 15;

	private static final int COL_USER_CREATED = 16;
	private static final int COL_USER_ALTERED = 17;

	private static final int COL_ID = 18;

	public DesconhecidoSetter(SetterAdapterType setterAdapterType, DesconhecidoEntity entity) {
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

		ps.setLong(COL_USER_ALTERED, getEntity().getUserCreated().getId());
		ps.setLong(COL_ID, getEntity().getId());
	}

	protected void setCommonValues(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_CONTRATO, getEntity().getContrato().getId());
		ps.setInt(COL_CD_MES, getEntity().getMes());
		ps.setInt(COL_CD_ANO, getEntity().getAno());

		if (getEntity().getMatricula() != null) {
			ps.setLong(COL_NR_MATRICULA, getEntity().getMatricula());
		} else {
			ps.setNull(COL_NR_MATRICULA, Types.BIGINT);
		}

		if (getEntity().getCpf() != null) {
			ps.setLong(COL_NR_CPF, getEntity().getCpf());
		} else {
			ps.setNull(COL_NR_CPF, Types.BIGINT);
		}

		if (StringUtils.isNotBlank(getEntity().getNameBeneficiario())) {
			ps.setString(COL_NM_BENEFICIARIO, getEntity().getNameBeneficiario());
		} else {
			ps.setNull(COL_NM_BENEFICIARIO, Types.VARCHAR);
		}

		if (getEntity().getDtNascimento() != null) {
			ps.setDate(COL_DT_NASCIMENTO, DateUtils.localDateToSqlDate(getEntity().getDtNascimento()));
		} else {
			ps.setNull(COL_DT_NASCIMENTO, Types.DATE);
		}

		if (getEntity().getMatricula() != null) {
			ps.setLong(COL_NR_MATRICULA, getEntity().getMatricula());
		} else {
			ps.setNull(COL_NR_MATRICULA, Types.BIGINT);
		}

		if (getEntity().getValorPrincipal() != null) {
			ps.setBigDecimal(COL_VL_PRINCIPAL, getEntity().getValorPrincipal());
		} else {
			ps.setNull(COL_VL_PRINCIPAL, Types.DOUBLE);
		}

		if (getEntity().getMatriculaEmpresa() != null) {
			ps.setLong(COL_NR_MATRICULA_EMPRESA, getEntity().getMatriculaEmpresa());
		} else {
			ps.setNull(COL_NR_MATRICULA_EMPRESA, Types.BIGINT);
		}

		if (StringUtils.isNotBlank(getEntity().getNameTitular())) {
			ps.setString(COL_NM_TITULAR, getEntity().getNameTitular());
		} else {
			ps.setNull(COL_NM_TITULAR, Types.VARCHAR);
		}

		setString(ps, COL_DESCR_PROFISSAO, getEntity().getBeneficiarioDetail().getProfissao());
		setLong(ps, COL_NR_MATRICULA_ESPECIAL, getEntity().getBeneficiarioDetail().getMatriculaEspecial());
		setBigDecimal(ps, COL_VL_FATOR_MODERADOR, getEntity().getBeneficiarioDetail().getFatorModerador());
		setString(ps, COL_NR_SUBFATURA, getEntity().getBeneficiarioDetail().getSubFatura());
		setBigDecimal(ps, COL_VL_FATOR_MODERADOR_INSS, getEntity().getBeneficiarioDetail().getFatorModeradorInss());
	}
}
