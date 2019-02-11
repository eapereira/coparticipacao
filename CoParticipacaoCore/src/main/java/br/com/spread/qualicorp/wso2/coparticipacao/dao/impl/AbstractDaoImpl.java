package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;
import br.com.spread.qualicorp.wso2.coparticipacao.xml.QueryUtils;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Transactional(transactionManager = AbstractDao.TRANSACTION_MANAGER)
public abstract class AbstractDaoImpl<ENTITY extends AbstractDomain> implements AbstractDao<ENTITY> {
	private static final Logger LOGGER = LogManager.getLogger(AbstractDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	private Class<ENTITY> entityClass;

	private QueryUtils queryUtils;

	@SuppressWarnings("unchecked")
	public AbstractDaoImpl() throws DaoException {

		try {
			LOGGER.info("BEGIN");

			entityClass = (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];

			queryUtils = new QueryUtils(this.getClass().getSimpleName(), "/jpql");
			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public ENTITY findById(Long id) throws DaoException {
		ENTITY entity;
		StringBuilder sb;
		Query query;

		try {
			LOGGER.info("BEGIN");
			sb = new StringBuilder();
			sb.append("select e from ");
			sb.append(entityClass.getSimpleName());
			sb.append(" e ");
			sb.append("where e.id = :id ");

			query = entityManager.createQuery(sb.toString());
			query.setParameter("id", id);

			entity = (ENTITY) query.getSingleResult();

			LOGGER.info("END");
			return entity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public List<ENTITY> listAll() throws DaoException {
		List<ENTITY> entities;
		StringBuilder sb;
		Query query;

		try {
			LOGGER.info("BEGIN");
			sb = new StringBuilder();
			sb.append("select e from ");
			sb.append(entityClass.getSimpleName());
			sb.append(" e ");

			LOGGER.debug("Executing JPQL query [{}]:", sb.toString());
			query = entityManager.createQuery(sb.toString());

			entities = query.getResultList();

			LOGGER.info("END");
			return entities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, transactionManager = TRANSACTION_MANAGER)
	public void delete(ENTITY entity) throws DaoException {
		try {
			LOGGER.info("BEGIN");

			entityManager.remove(entity);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, transactionManager = TRANSACTION_MANAGER)
	public void save(List<ENTITY> entities) throws DaoException {
		try {
			LOGGER.info("BEGIN");

			for (ENTITY entity : entities) {
				save(entity);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, transactionManager = TRANSACTION_MANAGER)
	public ENTITY save(ENTITY entity) throws DaoException {
		try {
			LOGGER.info("BEGIN");

			if (entity.getId() != null) {
				entity = merge(entity);
			} else {
				entity.setVersion(NumberUtils.LONG_ZERO);
			}

			entityManager.persist(entity);

			LOGGER.info("END");
			return entity;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	protected Query createQuery(String queryId) throws DaoException {
		Query query;
		String jpql;

		try {
			LOGGER.info("BEGIN");

			jpql = queryUtils.getQueryById(queryId);

			LOGGER.debug("Executing JPQL query:");
			LOGGER.debug(jpql);
			query = entityManager.createQuery(jpql);

			LOGGER.info("END");
			return query;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	protected <T extends AbstractDomain> T merge(T entity) throws DaoException {
		T entityNew;
		try {
			LOGGER.info("BEGIN");

			entityNew = entityManager.merge(entity);

			LOGGER.info("END");
			return entityNew;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	protected <T extends AbstractDomain> void reflesh(T entity) throws DaoException {
		try {
			LOGGER.info("BEGIN");

			entityManager.refresh(entity);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}

	}
}
