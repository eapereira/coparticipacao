package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class ArquivoInputSetter
		extends PreparedStatementSetterAdapter<ArquivoInputEntity> {

	private static final int COL_ID_CONTRATO = 1;
	private static final int COL_NM_ARQUIVO_REGEXP = 2;
	private static final int COL_DESCR_ARQUIVO = 3;
	private static final int COL_TP_ARQUIVO = 4;
	private static final int COL_TP_USE = 5;
	private static final int COL_NUM_SKIP_LINES = 6;

	private static final int COL_USER_CREATED = 7;
	private static final int COL_USER_ALTERED = 7;

	public ArquivoInputSetter(
			SetterAdapterType setterAdapterType,
			ArquivoInputEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps)
			throws SQLException {
		ps.setLong(COL_ID_CONTRATO, getEntity().getContrato().getId());
		ps.setString(COL_NM_ARQUIVO_REGEXP, getEntity().getNameArquivoRegexp());
		ps.setString(COL_DESCR_ARQUIVO, getEntity().getDescrArquivo());
		ps.setInt(COL_TP_ARQUIVO, getEntity().getArquivoType().getId());
		ps.setInt(COL_TP_USE, getEntity().getUseType().getId());
		ps.setInt(COL_NUM_SKIP_LINES, getEntity().getSkipLines());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps)
			throws SQLException {
		// TODO Auto-generated method stub
		ps.setLong(COL_ID_CONTRATO, getEntity().getContrato().getId());
		ps.setString(COL_NM_ARQUIVO_REGEXP, getEntity().getNameArquivoRegexp());
		ps.setString(COL_DESCR_ARQUIVO, getEntity().getDescrArquivo());
		ps.setInt(COL_TP_ARQUIVO, getEntity().getArquivoType().getId());
		ps.setInt(COL_TP_USE, getEntity().getUseType().getId());
		ps.setInt(COL_NUM_SKIP_LINES, getEntity().getSkipLines());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
