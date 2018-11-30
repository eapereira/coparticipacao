package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class LancamentoSetter extends PreparedStatementSetterAdapter<LancamentoEntity> {
	private static final int COL_ID_TITULAR = 1;
	private static final int COL_ID_DEPENDENTE = 2;
	private static final int COL_ID_CONTRATO = 3;
	private static final int COL_CD_MES = 4;
	private static final int COL_CD_ANO = 5;
	private static final int COL_VL_PRINCIPAL = 6;
	private static final int COL_VL_REEMBOLSO = 7;
	private static final int COL_VL_PARTICIPACAO = 8;
	private static final int COL_DT_UTILIZACAO = 9;

	private static final int COL_USER_CREATED = 10;
	private static final int COL_USER_ALTERED = 10;

	private static final int COL_ID = 11;

	public LancamentoSetter(SetterAdapterType setterAdapterType, LancamentoEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		setCommonsValues(ps);

		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	private void setCommonsValues(PreparedStatement ps) throws SQLException {
		setLong(ps, COL_ID_TITULAR, getEntity().getTitular().getId());

		if (getEntity().getDependente() != null) {
			setLong(ps, COL_ID_DEPENDENTE, getEntity().getDependente().getId());
		} else {
			setLong(ps, COL_ID_DEPENDENTE, null);
		}

		setLong(ps, COL_ID_CONTRATO, getEntity().getContrato().getId());
		setInt(ps, COL_CD_MES, getEntity().getMes());
		setInt(ps, COL_CD_ANO, getEntity().getAno());
		setBigDecimal(ps, COL_VL_PRINCIPAL, getEntity().getValorPrincipal());
		setBigDecimal(ps, COL_VL_REEMBOLSO, getEntity().getValorRembolso());
		setBigDecimal(ps, COL_VL_PARTICIPACAO, getEntity().getValorParticipacao());
		setDate(ps, COL_DT_UTILIZACAO, getEntity().getDtUtilizacao());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		setCommonsValues(ps);

		setLong(ps, COL_USER_ALTERED, getEntity().getUserAltered().getId());
		setLong(ps, COL_ID, getEntity().getId());
	}

}
