package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.DependenteResumoBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.rowmapper.DependenteResumoRowMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteResumoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class DependenteResumoBatchDaoImpl extends AbstractBatchDaoImpl<DependenteResumoEntity>
		implements DependenteResumoBatchDao {

	private static final Logger LOGGER = LogManager.getLogger(DependenteResumoBatchDaoImpl.class);

	public DependenteResumoBatchDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<DependenteResumoEntity> listByEmpresaId(Long empresaId) throws DaoException {
		List<DependenteResumoEntity> dependenteResumoEntities;

		try {
			LOGGER.info("BEGIN");

			dependenteResumoEntities = query(
					"listByEmpresaId",
					new ListByEmpresaPreparedStatementSetter(empresaId),
					new DependenteResumoRowMapper());

			LOGGER.info("END");
			return dependenteResumoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			DependenteResumoEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	private class ListByEmpresaPreparedStatementSetter implements PreparedStatementSetter {

		private static final int COL_EMPRESA_ID = 1;

		private Long empresaId;

		public ListByEmpresaPreparedStatementSetter(Long empresaId) {
			this.empresaId = empresaId;
		}

		public void setValues(PreparedStatement ps) throws SQLException {
			ps.setLong(COL_EMPRESA_ID, empresaId);
		}
	}
}
