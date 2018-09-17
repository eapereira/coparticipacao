package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoExecucaoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class ArquivoExecucaoSetter extends PreparedStatementSetterAdapter<ArquivoExecucaoEntity> {

	private static final int COL_NM_ARQUIVO_INPUT = 1;
	private static final int COL_NM_ARQUIVO_OUTPUT = 2;
	private static final int COL_ID_CONTRATO = 3;
	private static final int COL_CD_MES = 4;
	private static final int COL_CD_ANO = 5;
	private static final int COL_DT_STARTED = 6;
	private static final int COL_DT_FINNISHED = 7;
	private static final int COL_TP_STATUS = 8;
	private static final int COL_DESCR_ERROR_MESSAGE = 9;

	private static final int COL_USER_CREATED = 10;
	private static final int COL_USER_ALTERED = 10;

	private static final int COL_ID = 11;

	public ArquivoExecucaoSetter(SetterAdapterType setterAdapterType, ArquivoExecucaoEntity entity) {
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

	@Override
	protected void setCommonValues(PreparedStatement ps) throws SQLException {
		ps.setString(COL_NM_ARQUIVO_INPUT, getEntity().getNameArquivoInput());

		if (StringUtils.isNotBlank(getEntity().getNameArquivoOutput())) {
			ps.setString(COL_NM_ARQUIVO_OUTPUT, getEntity().getNameArquivoOutput());
		} else {
			ps.setNull(COL_NM_ARQUIVO_OUTPUT, Types.VARCHAR);
		}

		ps.setLong(COL_ID_CONTRATO, getEntity().getContrato().getId());
		ps.setInt(COL_CD_MES, getEntity().getMes());
		ps.setInt(COL_CD_ANO, getEntity().getAno());

		if (getEntity().getStarted() != null) {
			ps.setTimestamp(COL_DT_STARTED, DateUtils.dateTimeToTimestamp(getEntity().getStarted()));
		} else {
			ps.setNull(COL_DT_STARTED, Types.TIMESTAMP);
		}

		if (getEntity().getFinnished() != null) {
			ps.setTimestamp(COL_DT_FINNISHED, DateUtils.dateTimeToTimestamp(getEntity().getFinnished()));
		} else {
			ps.setNull(COL_DT_FINNISHED, Types.TIMESTAMP);
		}

		ps.setInt(COL_TP_STATUS, getEntity().getStatusExecucaoType().getId());

		if (StringUtils.isNotBlank(getEntity().getErrorMessage())) {
			ps.setString(COL_DESCR_ERROR_MESSAGE, getEntity().getErrorMessage());
		} else {
			ps.setNull(COL_DESCR_ERROR_MESSAGE, Types.VARCHAR);
		}
	}

}
