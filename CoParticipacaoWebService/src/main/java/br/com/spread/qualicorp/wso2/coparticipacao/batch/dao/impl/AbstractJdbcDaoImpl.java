package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.batchPreparedStatementSetter.BatchPreparedStatementSetterAdapter;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.PreparedStatementSetterAdapter;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;
import br.com.spread.qualicorp.wso2.coparticipacao.xml.QueryUtils;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class AbstractJdbcDaoImpl<ENTITY extends AbstractDomain> implements AbstractJdbcDao<ENTITY> {

	private static final Logger LOGGER = LogManager.getLogger(AbstractJdbcDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Class<ENTITY> entityClass;

	private QueryUtils queryUtils;

	public AbstractJdbcDaoImpl() throws DaoException {
		try {
			LOGGER.info("BEGIN");
			entityClass = (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];

			queryUtils = new QueryUtils(entityClass.getSimpleName());
			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Long save(ENTITY entity) throws DaoException {
		String sql;
		KeyHolder keyHolder;

		try {
			LOGGER.info("BEGIN");

			if (entity.getId() == null) {
				keyHolder = new GeneratedKeyHolder();

				sql = queryUtils.getQueryById(getInsertSql());

				LOGGER.debug("using SQL [{}] for INSERT:", sql);
				jdbcTemplate.update(createPreparedStatement(sql, entity), keyHolder);

				LOGGER.debug("Using ID[{}] for INSERT:", keyHolder.getKey().longValue());
				LOGGER.info("END");
				return keyHolder.getKey().longValue();
			} else {
				LOGGER.debug("Using ID[{}] for UPDATE:", entity.getId());

				sql = queryUtils.getQueryById(getUpdateSql());

				LOGGER.debug("using SQL [{}] for UPDATE:", sql);
				jdbcTemplate.update(sql, getUpdatePreparedStatementSetter(entity));

				LOGGER.info("END");
				return entity.getId();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	private PreparedStatementCreator createPreparedStatement(String sql, ENTITY entity) throws DaoException {
		SimplePreparedStatementCreator preparedStatementCreator;

		try {
			LOGGER.info("BEGIN");

			preparedStatementCreator = new SimplePreparedStatementCreator(
					sql,
					getInsertPreparedStatementSetter(entity));

			LOGGER.info("END");
			return preparedStatementCreator;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}

	}

	public void delete(ENTITY entity) throws DaoException {
		String sql;

		try {
			LOGGER.info("BEGIN");

			if (entity.getId() == null) {
				sql = queryUtils.getQueryById(getDeleteSql());
				jdbcTemplate.update(sql, getDeletePreparedStatementSetter(entity));
			} else {
				throw new DaoException("Registro em [{}] com ID null:", entityClass.getName());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	protected List<ENTITY> query(
			String queryId,
			PreparedStatementSetter preparedStatementSetter,
			RowMapper<ENTITY> rowMapper) throws DaoException {
		String sql;
		List<ENTITY> entities;

		try {
			LOGGER.info("BEGIN");
			sql = queryUtils.getQueryById(queryId);

			entities = jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);

			LOGGER.info("END");
			return entities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	protected List<ENTITY> querySql(String sql, ENTITY entity, RowMapper<ENTITY> rowMapper) throws DaoException {
		List<ENTITY> entities;

		try {
			LOGGER.info("BEGIN");

			entities = jdbcTemplate.query(sql, getQueryPreparedStatementSetter(entity), rowMapper);

			LOGGER.info("END");
			return entities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public Long save(List<ENTITY> entities) throws DaoException {
		Long rowCount = NumberUtils.LONG_ZERO;

		try {
			LOGGER.info("BEGIN");

			for (ENTITY entity : entities) {
				save(entity);
				rowCount++;
			}

			LOGGER.info("END");
			return rowCount;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public void insertBatch(List<ENTITY> entities) throws DaoException {
		String sql;
		PreparedStatementSetter preparedStatementSetter;
		BatchPreparedStatementSetterAdapter<ENTITY> batchPreparedStatementSetterAdapter;
		List<ENTITY> batchEntities;
		int rowCount = NumberUtils.INTEGER_ONE;
		ENTITY entityTmp = null;

		try {
			LOGGER.info("BEGIN");

			batchEntities = new ArrayList<ENTITY>();

			sql = queryUtils.getQueryById(getInsertSql());
			LOGGER.debug("using SQL [{}] for Batch INSERT:", sql);

			preparedStatementSetter = getInsertPreparedStatementSetter(entityTmp);

			for (ENTITY entity : entities) {
				batchEntities.add(entity);

				if (rowCount % BATCH_SIZE == 0) {
					batchPreparedStatementSetterAdapter = new BatchPreparedStatementSetterAdapter<ENTITY>(
							(PreparedStatementSetterAdapter<ENTITY>) preparedStatementSetter,
							batchEntities);

					jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetterAdapter);
					batchEntities.clear();
				}

				rowCount++;
			}

			if (!batchEntities.isEmpty()) {
				batchPreparedStatementSetterAdapter = new BatchPreparedStatementSetterAdapter<ENTITY>(
						(PreparedStatementSetterAdapter<ENTITY>) preparedStatementSetter,
						entities);

				jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetterAdapter);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public void updateBatch(List<ENTITY> entities) throws DaoException {
		String sql;
		PreparedStatementSetter preparedStatementSetter;
		BatchPreparedStatementSetterAdapter<ENTITY> batchPreparedStatementSetterAdapter;
		List<ENTITY> batchEntities;
		int rowCount = NumberUtils.INTEGER_ONE;
		ENTITY entityTmp = null;

		try {
			LOGGER.info("BEGIN");

			batchEntities = new ArrayList<ENTITY>();

			sql = queryUtils.getQueryById(getUpdateSql());
			LOGGER.debug("using SQL [{}] for Batch INSERT:", sql);

			preparedStatementSetter = getUpdatePreparedStatementSetter(entityTmp);

			for (ENTITY entity : entities) {
				batchEntities.add(entity);

				if (rowCount % BATCH_SIZE == 0) {
					batchPreparedStatementSetterAdapter = new BatchPreparedStatementSetterAdapter<ENTITY>(
							(PreparedStatementSetterAdapter<ENTITY>) preparedStatementSetter,
							batchEntities);

					jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetterAdapter);
					batchEntities.clear();
				}

				rowCount++;
			}

			if (!batchEntities.isEmpty()) {
				batchPreparedStatementSetterAdapter = new BatchPreparedStatementSetterAdapter<ENTITY>(
						(PreparedStatementSetterAdapter<ENTITY>) preparedStatementSetter,
						entities);

				jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetterAdapter);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public void deleteBatch(List<ENTITY> entities) throws DaoException {
		String sql;
		PreparedStatementSetter preparedStatementSetter;
		BatchPreparedStatementSetterAdapter<ENTITY> batchPreparedStatementSetterAdapter;
		List<ENTITY> batchEntities;
		int rowCount = NumberUtils.INTEGER_ONE;
		ENTITY entityTmp = null;

		try {
			LOGGER.info("BEGIN");

			batchEntities = new ArrayList<ENTITY>();

			sql = queryUtils.getQueryById(getUpdateSql());
			LOGGER.debug("using SQL [{}] for Batch INSERT:", sql);

			preparedStatementSetter = getDeletePreparedStatementSetter(entityTmp);

			for (ENTITY entity : entities) {
				batchEntities.add(entity);

				if (rowCount % BATCH_SIZE == 0) {
					batchPreparedStatementSetterAdapter = new BatchPreparedStatementSetterAdapter<ENTITY>(
							(PreparedStatementSetterAdapter<ENTITY>) preparedStatementSetter,
							batchEntities);

					jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetterAdapter);
					batchEntities.clear();
				}

				rowCount++;
			}

			if (!batchEntities.isEmpty()) {
				batchPreparedStatementSetterAdapter = new BatchPreparedStatementSetterAdapter<ENTITY>(
						(PreparedStatementSetterAdapter<ENTITY>) preparedStatementSetter,
						entities);

				jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetterAdapter);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}
	
	public Integer delete(List<ENTITY> entities) throws DaoException {
		Integer rowCount = NumberUtils.INTEGER_ZERO;

		try {
			LOGGER.info("BEGIN");

			for (ENTITY entity : entities) {
				delete(entity);
				rowCount++;
			}

			LOGGER.info("END");
			return rowCount;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	protected String getInsertSql() throws DaoException {
		StringBuilder sb = new StringBuilder();

		sb.append(entityClass.getSimpleName());
		sb.append(".");
		sb.append("insert");

		return sb.toString();
	}

	protected String getUpdateSql() throws DaoException {
		StringBuilder sb = new StringBuilder();

		sb.append(entityClass.getSimpleName());
		sb.append(".");
		sb.append("update");

		return sb.toString();
	}

	protected String getDeleteSql() throws DaoException {
		StringBuilder sb = new StringBuilder();

		sb.append(entityClass.getSimpleName());
		sb.append(".");
		sb.append("delete");

		return sb.toString();
	}

	protected void update(String queryId, PreparedStatementSetter preparedStatementSetter) throws DaoException {
		String sql;

		try {
			LOGGER.info("BEGIN");

			sql = queryUtils.getQueryById(queryId);

			jdbcTemplate.update(sql, preparedStatementSetter);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	protected PreparedStatementSetter getQueryPreparedStatementSetter(ENTITY entity) throws DaoException {
		return getPreparedStatementSetter(entity, SetterAdapterType.SELECT);
	}

	protected PreparedStatementSetter getInsertPreparedStatementSetter(ENTITY entity) throws DaoException {
		return getPreparedStatementSetter(entity, SetterAdapterType.INSERT);
	}

	protected PreparedStatementSetter getUpdatePreparedStatementSetter(ENTITY entity) throws DaoException {
		return getPreparedStatementSetter(entity, SetterAdapterType.UPDATE);
	}

	protected PreparedStatementSetter getDeletePreparedStatementSetter(ENTITY entity) throws DaoException {
		return getPreparedStatementSetter(entity, SetterAdapterType.DELETE);
	}

	protected abstract PreparedStatementSetter getPreparedStatementSetter(
			ENTITY entity,
			SetterAdapterType setterAdapterType) throws DaoException;

}
