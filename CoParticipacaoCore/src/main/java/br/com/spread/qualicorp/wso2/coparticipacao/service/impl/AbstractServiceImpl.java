package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(propagation = Propagation.REQUIRED, transactionManager = AbstractService.JPA_TX)
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
	public UI save(UI ui) throws ServiceException {
		ENTITY entity;
		UI uiNew;

		try {
			LOGGER.info("BEGIN");

			logBatchInfo(ui);

			if (ui.getId() == null) {
				ui.setCreated(LocalDateTime.now());
			}

			ui.setAltered(LocalDateTime.now());

			entity = uiToEntity(ui);
			entity = getDao().save(entity);

			uiNew = entityToUi(entity);

			LOGGER.info("END");
			return uiNew;
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
