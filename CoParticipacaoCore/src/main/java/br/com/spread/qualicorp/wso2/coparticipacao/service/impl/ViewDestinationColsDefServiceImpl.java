package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ViewDestinationColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestinationColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ViewDestinationColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ViewDestinationColsDefEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ViewDestinationColsDefUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationColsDefService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ViewDestinationColsDefServiceImpl extends AbstractServiceImpl<ViewDestinationColsDefUi, ViewDestinationColsDefEntity, ViewDestinationColsDef>
		implements ViewDestinationColsDefService {

	private static final Logger LOGGER = LogManager.getLogger(ViewDestinationColsDefServiceImpl.class);

	@Autowired
	private ViewDestinationColsDefDao viewDestinationColsDefDao;

	@Autowired
	private ViewDestinationColsDefUiMapper uiMapper;

	@Autowired
	private ViewDestinationColsDefEntityMapper entityMapper;

	@Override
	protected ViewDestinationColsDefUi createUi() {
		return new ViewDestinationColsDefUi();
	}

	@Override
	protected ViewDestinationColsDefEntity createEntity() {
		return new ViewDestinationColsDefEntity();
	}

	@Override
	protected AbstractDao<ViewDestinationColsDefEntity> getDao() {
		return viewDestinationColsDefDao;
	}

	@Override
	protected AbstractMapper<ViewDestinationColsDef, ViewDestinationColsDefUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ViewDestinationColsDef, ViewDestinationColsDefEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<ViewDestinationColsDefUi> listByViewDestinationId(ViewDestinationUi viewDestinationUi) throws ServiceException {
		List<ViewDestinationColsDefUi> viewDestinationColsDefUis;

		try {
			LOGGER.info("BEGIN");

			viewDestinationColsDefUis = entityToUi(viewDestinationColsDefDao.listByViewDestinationId(viewDestinationUi.getId()));

			LOGGER.info("END");
			return viewDestinationColsDefUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
