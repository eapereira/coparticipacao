package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.TitularResumoBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.rowmapper.TitularResumoRowMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularResumoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class TitularResumoBatchDaoImpl extends AbstractBatchDaoImpl<TitularResumoEntity>
		implements TitularResumoBatchDao {

	private static final Logger LOGGER = LogManager.getLogger(TitularResumoBatchDaoImpl.class);

	public TitularResumoBatchDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<TitularResumoEntity> listByEmpresaId(Long empresaId) throws DaoException {
		List<TitularResumoEntity> titularResumoEntities;

		try {
			LOGGER.info("BEGIN");

			titularResumoEntities = query(
					"listByEmpresaId",
					new ListByEmpresaIdPreparedStatementSetter(empresaId),
					new TitularResumoRowMapper());

			LOGGER.info("END");
			return titularResumoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			TitularResumoEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return null;
	}

	private class ListByEmpresaIdPreparedStatementSetter implements PreparedStatementSetter {
		private static final int COL_EMPRESA_ID = 1;

		private Long empresaId;

		public ListByEmpresaIdPreparedStatementSetter(Long empresaId) {
			this.empresaId = empresaId;
		}

		public void setValues(PreparedStatement ps) throws SQLException {
			ps.setLong(COL_EMPRESA_ID, empresaId);
		}
	}
}
