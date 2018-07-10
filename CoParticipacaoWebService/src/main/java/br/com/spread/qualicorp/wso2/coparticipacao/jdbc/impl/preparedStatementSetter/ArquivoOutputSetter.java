package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class ArquivoOutputSetter extends PreparedStatementSetterAdapter<ArquivoOutputEntity> {

	private static final int COL_ID_EMPRESA = 1;
	private static final int COL_NM_ARQUIVO_FORMAT = 2;
	private static final int COL_DESCR_ARQUIVO = 3;

	private static final int COL_USER_CREATED = 4;
	private static final int COL_USER_ALTERED = 4;

	public ArquivoOutputSetter(SetterAdapterType setterAdapterType, ArquivoOutputEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_EMPRESA, getEntity().getEmpresa().getId());
		ps.setString(COL_NM_ARQUIVO_FORMAT, getEntity().getNameArquivoFormat());
		ps.setString(COL_DESCR_ARQUIVO, getEntity().getDescrArquivo());

		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_EMPRESA, getEntity().getEmpresa().getId());
		ps.setString(COL_NM_ARQUIVO_FORMAT, getEntity().getNameArquivoFormat());
		ps.setString(COL_DESCR_ARQUIVO, getEntity().getDescrArquivo());

		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
