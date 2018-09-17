package br.com.spread.qualicorp.wso2.coparticipacao.batch.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.AbstractBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Transactional(transactionManager = AbstractService.JDBC_TX)
public abstract class AbstractBatchServiceImpl<UI extends AbstractDomain, ENTITY extends AbstractDomain, INTERFACE extends AbstractDomain>
		/* extends AbstractServiceImpl<UI, ENTITY, INTERFACE> */ implements AbstractBatchService<UI> {

	private static final Logger LOGGER = LogManager.getLogger(AbstractBatchServiceImpl.class);

	protected abstract UI createUi();

	protected abstract ENTITY createEntity();

	protected abstract AbstractDao<ENTITY> getDao();

	protected abstract AbstractMapper<INTERFACE, UI> getUiMapper();

	protected abstract AbstractMapper<INTERFACE, ENTITY> getEntityMapper();

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

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveBatch(List<UI> uis) throws ServiceException {
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
}
