package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class RegraSetter extends PreparedStatementSetterAdapter<RegraEntity> {

	private static final int COL_NM_REGRA = 1;
	private static final int COL_TP_REGRA = 2;
	private static final int COL_CD_ORDEM = 3;
	private static final int COL_ID_ARQUIVO_INPUT_SHEET = 4;

	private static final int COL_USER_CREATED = 5;
	private static final int COL_USER_ALTERED = 5;

	public RegraSetter(SetterAdapterType setterAdapterType, RegraEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setString(COL_NM_REGRA, getEntity().getNameRegra());
		ps.setInt(COL_TP_REGRA, getEntity().getTpRegra().getId());
		ps.setInt(COL_CD_ORDEM, getEntity().getOrdem());
		ps.setLong(COL_ID_ARQUIVO_INPUT_SHEET, getEntity().getArquivoInputSheet().getId());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setString(COL_NM_REGRA, getEntity().getNameRegra());
		ps.setInt(COL_TP_REGRA, getEntity().getTpRegra().getId());
		ps.setInt(COL_CD_ORDEM, getEntity().getOrdem());
		ps.setLong(COL_ID_ARQUIVO_INPUT_SHEET, getEntity().getArquivoInputSheet().getId());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
