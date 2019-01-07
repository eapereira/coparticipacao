package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegisterColumnDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegisterColumn;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegisterColumnEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegisterColumnEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegisterColumnUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterColumnUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegisterColumnService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class RegisterColumnServiceImpl extends
		AbstractServiceImpl<RegisterColumnUi, RegisterColumnEntity, RegisterColumn> implements RegisterColumnService {
	private static final Logger LOGGER = LogManager.getLogger(RegisterColumnServiceImpl.class);

	@Autowired
	private RegisterColumnUiMapper uiMapper;

	@Autowired
	private RegisterColumnEntityMapper entityMapper;

	@Autowired
	private RegisterColumnDao registerColumnDao;

	@Override
	protected RegisterColumnUi createUi() {
		return new RegisterColumnUi();
	}

	@Override
	protected RegisterColumnEntity createEntity() {
		return new RegisterColumnEntity();
	}

	@Override
	protected AbstractDao<RegisterColumnEntity> getDao() {
		return registerColumnDao;
	}

	@Override
	protected AbstractMapper<RegisterColumn, RegisterColumnUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegisterColumn, RegisterColumnEntity> getEntityMapper() {
		return entityMapper;
	}

}
