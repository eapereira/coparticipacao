package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Transactional(transactionManager = AbstractService.JPA_TX)
public abstract class AbstractServiceImpl<UI extends AbstractDomain, ENTITY extends AbstractDomain, INTERFACE extends AbstractDomain>
		implements AbstractService<UI> {

	private static final Logger LOGGER = LogManager.getLogger(AbstractServiceImpl.class);

	public UI findById(Long id) throws ServiceException {
		UI ui = null;
		ENTITY entity;

		try {
			LOGGER.info("BEGIN");
			entity = getDao().findById(id);

			if (entity != null) {
				ui = entityToUi(entity);
			}

			LOGGER.info("END");
			return ui;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public List<UI> listAll() throws ServiceException {
		List<UI> uis = null;
		List<ENTITY> entities;

		try {
			LOGGER.info("BEGIN");
			uis = new ArrayList<UI>();
			entities = getDao().listAll();

			if (!entities.isEmpty()) {
				uis = entityToUi(entities);
			}

			LOGGER.info("END");
			return uis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected List<UI> entityToUi(List<ENTITY> entities) throws ServiceException {
		List<UI> uis;

		try {
			LOGGER.info("BEGIN");

			uis = new ArrayList<UI>();

			for (ENTITY entity : entities) {
				uis.add(entityToUi(entity));
			}

			LOGGER.info("END");
			return uis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected UI entityToUi(ENTITY entity) throws ServiceException {
		UI ui = null;

		try {
			LOGGER.info("BEGIN");

			if (entity != null) {
				ui = createUi();

				getUiMapper().copyProperties((INTERFACE) entity, ui);
			}

			LOGGER.info("END");
			return ui;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected List<ENTITY> uiToEntity(List<UI> uis) throws ServiceException {
		List<ENTITY> entities;

		try {
			LOGGER.info("BEGIN");

			entities = new ArrayList<ENTITY>();

			for (UI ui : uis) {
				entities.add(uiToEntity(ui));
			}

			LOGGER.info("END");
			return entities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected ENTITY uiToEntity(UI ui) throws ServiceException {
		ENTITY entity = null;

		try {
			LOGGER.info("BEGIN");

			if (ui != null) {
				entity = createEntity();

				getEntityMapper().copyProperties((INTERFACE) ui, entity);
			}

			LOGGER.info("END");
			return entity;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(UI ui) throws ServiceException {
		ENTITY entity;

		try {
			LOGGER.info("BEGIN");

			entity = uiToEntity(ui);

			getDao().delete(entity);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, transactionManager = AbstractService.JPA_TX)
	public void save(UI ui) throws ServiceException {
		ENTITY entity;

		try {
			LOGGER.info("BEGIN");

			logBatchInfo(ui);

			if (ui.getId() == null) {
				ui.setCreated(LocalDateTime.now());
			}

			ui.setAltered(LocalDateTime.now());

			entity = uiToEntity(ui);

			getDao().save(entity);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, transactionManager = AbstractService.JPA_TX)
	public void save(List<UI> uis) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			for (UI ui : uis) {
				save(ui);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, transactionManager = AbstractService.JDBC_TX)
	public void saveBatch(List<UI> uis) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			for (UI ui : uis) {
				saveBatch(ui);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, transactionManager = AbstractService.JDBC_TX)
	public void saveBatch(UI ui) throws ServiceException {
		Long id;

		try {
			LOGGER.info("BEGIN");

			ui.setAltered(LocalDateTime.now());
			ui.setCreated(LocalDateTime.now());

			logBatchInfo(ui);

			if (getJdbcDao() != null) {
				id = getJdbcDao().save(uiToEntity((UI) ui));

				ui.setId(id);
			} else {
				LOGGER.error("There is no JdbcDao define to [{}]:", getClass().getSimpleName());
				throw new ServiceException("There is no JdbcDao define to [%s]:", getClass().getSimpleName());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, transactionManager = AbstractService.JDBC_TX)
	public void saveBatchBlock(List<UI> uis) throws ServiceException {
		List<ENTITY> entitiesInsert;
		List<ENTITY> entitiesUpdate;

		try {
			LOGGER.info("BEGIN");

			entitiesInsert = new ArrayList<ENTITY>();
			entitiesUpdate = new ArrayList<ENTITY>();

			for (UI ui : uis) {
				logBatchInfo(ui);

				if (ui.getId() == null) {
					entitiesInsert.add(uiToEntity(ui));
				} else {
					entitiesUpdate.add(uiToEntity(ui));
				}
			}

			getJdbcDao().insertBatch(entitiesInsert);
			getJdbcDao().updateBatch(entitiesUpdate);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected AbstractBatchDao<ENTITY> getJdbcDao() {
		return null;
	}

	/**
	 * Método para caso o desenvolvedor desejar, imprimir algumas mensagens de
	 * log.
	 * 
	 * @param ui
	 *            Objeto que será gravado no banco.
	 */
	protected void logBatchInfo(UI ui) throws ServiceException {
		// TODO
	}

	protected abstract UI createUi();

	protected abstract ENTITY createEntity();

	protected abstract AbstractDao<ENTITY> getDao();

	protected abstract AbstractMapper<INTERFACE, UI> getUiMapper();

	protected abstract AbstractMapper<INTERFACE, ENTITY> getEntityMapper();

}
