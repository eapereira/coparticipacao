package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputTitularIsentoColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularIsentoColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.InputTitularIsentoColsDefEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.InputTitularIsentoColsDefUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputTitularIsentoColsDefService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class InputTitularIsentoColsDefServiceImpl extends
		AbstractServiceImpl<InputTitularIsentoColsDefUi, InputTitularIsentoColsDefEntity, InputTitularIsentoColsDef>
		implements InputTitularIsentoColsDefService {

	private static final Logger LOGGER = LogManager
			.getLogger(InputTitularIsentoColsDefServiceImpl.class);

	@Autowired
	private InputTitularIsentoColsDefDao inputTitularIsentoColsDefDao;

	@Autowired
	private InputTitularIsentoColsDefUiMapper uiMapper;

	@Autowired
	private InputTitularIsentoColsDefEntityMapper entityMapper;

	@Override
	protected InputTitularIsentoColsDefUi createUi() {
		return new InputTitularIsentoColsDefUi();
	}

	@Override
	protected InputTitularIsentoColsDefEntity createEntity() {
		return new InputTitularIsentoColsDefEntity();
	}

	@Override
	protected AbstractDao<InputTitularIsentoColsDefEntity> getDao() {
		return inputTitularIsentoColsDefDao;
	}

	@Override
	protected AbstractMapper<InputTitularIsentoColsDef, InputTitularIsentoColsDefUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<InputTitularIsentoColsDef, InputTitularIsentoColsDefEntity> getEntityMapper() {
		return entityMapper;
	}

}
