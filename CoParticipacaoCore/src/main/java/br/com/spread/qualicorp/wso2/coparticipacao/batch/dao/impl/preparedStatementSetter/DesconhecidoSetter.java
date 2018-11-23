package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;

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
	private static final int COL_CD_USUARIO = 16;
	private static final int COL_NR_CERTIFICADO = 17;

	private static final int COL_USER_CREATED = 18;
	private static final int COL_USER_ALTERED = 18;

	private static final int COL_ID = 19;

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
		setLong(ps, COL_ID_CONTRATO, getEntity().getContrato().getId());
		setInt(ps, COL_CD_MES, getEntity().getMes());
		setInt(ps, COL_CD_ANO, getEntity().getAno());

		setLong(ps, COL_NR_MATRICULA, getEntity().getMatricula());
		setLong(ps, COL_NR_CPF, getEntity().getCpf());
		setString(ps, COL_NM_BENEFICIARIO, getEntity().getNameBeneficiario());
		setDate(ps, COL_DT_NASCIMENTO, getEntity().getDtNascimento());
		setLong(ps, COL_NR_MATRICULA, getEntity().getMatricula());
		setBigDecimal(ps, COL_VL_PRINCIPAL, getEntity().getValorPrincipal());
		setLong(ps, COL_NR_MATRICULA_EMPRESA, getEntity().getMatriculaEmpresa());
		setString(ps, COL_NM_TITULAR, getEntity().getNameTitular());

		setString(ps, COL_DESCR_PROFISSAO, getEntity().getBeneficiarioDetail().getProfissao());
		setLong(ps, COL_NR_MATRICULA_ESPECIAL, getEntity().getBeneficiarioDetail().getMatriculaEspecial());
		setBigDecimal(ps, COL_VL_FATOR_MODERADOR, getEntity().getBeneficiarioDetail().getFatorModerador());
		setInt(ps, COL_NR_SUBFATURA, getEntity().getBeneficiarioDetail().getSubFatura());
		setBigDecimal(ps, COL_VL_FATOR_MODERADOR_INSS, getEntity().getBeneficiarioDetail().getFatorModeradorInss());
		setString(ps, COL_CD_USUARIO, getEntity().getBeneficiarioDetail().getCdUsuario());
		setLong(ps, COL_NR_CERTIFICADO, getEntity().getBeneficiarioDetail().getCertificado());
	}
}
