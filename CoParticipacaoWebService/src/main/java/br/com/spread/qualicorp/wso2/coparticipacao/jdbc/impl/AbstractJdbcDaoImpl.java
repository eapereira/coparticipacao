package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.AbstractJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.xml.QueryUtils;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class AbstractJdbcDaoImpl<ENTITY extends AbstractDomain>
		implements AbstractJdbcDao<ENTITY> {

	private static final Logger LOGGER = LogManager
			.getLogger(AbstractJdbcDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Class<ENTITY> entityClass;

	private QueryUtils queryUtils;

	public AbstractJdbcDaoImpl() throws DaoException {
		try {
			LOGGER.info("BEGIN");
			entityClass = (Class<ENTITY>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];

			queryUtils = new QueryUtils(entityClass.getSimpleName());
			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(ENTITY entity) throws DaoException {
		String sql;

		try {
			LOGGER.info("BEGIN");

			if (entity.getId() == null) {
				sql = queryUtils.getQueryById(getInsertSql());
				jdbcTemplate
						.update(sql, getUpdatePreparedStatementSetter(entity));
			} else {
				sql = queryUtils.getQueryById(getUpdateSql());
				jdbcTemplate
						.update(sql, getInsertPreparedStatementSetter(entity));
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(ENTITY entity) throws DaoException {
		String sql;

		try {
			LOGGER.info("BEGIN");

			if (entity.getId() == null) {
				sql = queryUtils.getQueryById(getDeleteSql());
				jdbcTemplate
						.update(sql, getDeletePreparedStatementSetter(entity));
			} else {
				throw new DaoException(
						"Registro em [{}] com ID null:",
						entityClass.getName());
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

			entities = jdbcTemplate
					.query(sql, preparedStatementSetter, rowMapper);

			LOGGER.info("END");
			return entities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	protected List<ENTITY> querySql(
			String sql,
			ENTITY entity,
			RowMapper<ENTITY> rowMapper) throws DaoException {
		List<ENTITY> entities;

		try {
			LOGGER.info("BEGIN");

			entities = jdbcTemplate.query(
					sql,
					getQueryPreparedStatementSetter(entity),
					rowMapper);

			LOGGER.info("END");
			return entities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public Integer save(List<ENTITY> entities) throws DaoException {
		Integer rowCount = NumberUtils.INTEGER_ZERO;

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

	@Transactional(propagation = Propagation.REQUIRED)
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

		sb.append(entityClass.getName());
		sb.append(".");
		sb.append("insert");

		return sb.toString();
	}

	protected String getUpdateSql() throws DaoException {
		StringBuilder sb = new StringBuilder();

		sb.append(entityClass.getName());
		sb.append(".");
		sb.append("update");

		return sb.toString();
	}

	protected String getDeleteSql() throws DaoException {
		StringBuilder sb = new StringBuilder();

		sb.append(entityClass.getName());
		sb.append(".");
		sb.append("delete");

		return sb.toString();
	}

	protected PreparedStatementSetter getQueryPreparedStatementSetter(
			ENTITY entity) throws DaoException {
		return getPreparedStatementSetter(entity, SetterAdapterType.SELECT);
	}

	protected PreparedStatementSetter getInsertPreparedStatementSetter(
			ENTITY entity) throws DaoException {
		return getPreparedStatementSetter(entity, SetterAdapterType.INSERT);
	}

	protected PreparedStatementSetter getUpdatePreparedStatementSetter(
			ENTITY entity) throws DaoException {
		return getPreparedStatementSetter(entity, SetterAdapterType.UPDATE);
	}

	protected PreparedStatementSetter getDeletePreparedStatementSetter(
			ENTITY entity) throws DaoException {
		return getPreparedStatementSetter(entity, SetterAdapterType.DELETE);
	}

	protected abstract PreparedStatementSetter getPreparedStatementSetter(
			ENTITY entity,
			SetterAdapterType setterAdapterType) throws DaoException;

}
