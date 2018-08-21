package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import java.sql.Types;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.CoparticipacaoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.EmpresaSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class CoparticipacaoJdbcDaoImpl extends AbstractJdbcDaoImpl<EmpresaEntity> implements CoparticipacaoJdbcDao {

	private static final Logger LOGGER = LogManager.getLogger(CoparticipacaoJdbcDaoImpl.class);

	public CoparticipacaoJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void clearCoparticipacao(Long empresaId) throws DaoException {
		SimpleJdbcCall jdbcCall;
		SqlParameterSource sqlParameterSource;

		try {
			LOGGER.info("BEGIN");

			jdbcCall = new SimpleJdbcCall(getJdbcTemplate());
			jdbcCall.withProcedureName("PROC_CLEAR_COPARTICIPACAO");

			if (empresaId != null) {
				sqlParameterSource = new MapSqlParameterSource().addValue("PARAM_ID_EMPRESA", empresaId, Types.BIGINT);
			} else {
				sqlParameterSource = new MapSqlParameterSource().addValue("PARAM_ID_EMPRESA", null, Types.BIGINT);
			}

			jdbcCall.execute(sqlParameterSource);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			EmpresaEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new EmpresaSetter(setterAdapterType, entity);
	}

}
