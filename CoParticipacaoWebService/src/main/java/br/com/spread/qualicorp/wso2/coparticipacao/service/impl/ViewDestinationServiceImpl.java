package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ViewDestinationDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestination;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ViewDestinationEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ViewDestinationEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ViewDestinationUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ViewDestinationServiceImpl extends
		AbstractServiceImpl<ViewDestinationUi, ViewDestinationEntity, ViewDestination>
		implements ViewDestinationService {

	private static final Logger LOGGER = LogManager
			.getLogger(ViewDestinationServiceImpl.class);

	@Autowired
	private ViewDestinationDao viewDestinationDao;

	@Autowired
	private ViewDestinationUiMapper uiMapper;

	@Autowired
	private ViewDestinationEntityMapper entityMapper;

	@Override
	protected ViewDestinationUi createUi() {
		return new ViewDestinationUi();
	}

	@Override
	protected ViewDestinationEntity createEntity() {
		return new ViewDestinationEntity();
	}

	@Override
	protected AbstractDao<ViewDestinationEntity> getDao() {
		return viewDestinationDao;
	}

	@Override
	protected AbstractMapper<ViewDestination, ViewDestinationUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ViewDestination, ViewDestinationEntity> getEntityMapper() {
		return entityMapper;
	}

}
