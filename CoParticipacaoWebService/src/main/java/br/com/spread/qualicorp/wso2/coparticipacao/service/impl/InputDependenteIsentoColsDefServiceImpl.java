package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputDependenteIsentoColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteIsentoColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.InputDependenteIsentoColsDefEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.InputDependenteIsentoColsDefUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputDependenteIsentoColsDefService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class InputDependenteIsentoColsDefServiceImpl extends
		AbstractServiceImpl<InputDependenteIsentoColsDefUi, InputDependenteIsentoColsDefEntity, InputDependenteIsentoColsDef>
		implements InputDependenteIsentoColsDefService {

	private static final Logger LOGGER = LogManager
			.getLogger(InputDependenteIsentoColsDefServiceImpl.class);

	@Autowired
	private InputDependenteIsentoColsDefDao inputDependenteIsentoColsDefDao;

	@Autowired
	private InputDependenteIsentoColsDefUiMapper uiMapper;

	@Autowired
	private InputDependenteIsentoColsDefEntityMapper entityMapper;

	@Override
	protected InputDependenteIsentoColsDefUi createUi() {
		return new InputDependenteIsentoColsDefUi();
	}

	@Override
	protected InputDependenteIsentoColsDefEntity createEntity() {
		return new InputDependenteIsentoColsDefEntity();
	}

	@Override
	protected AbstractDao<InputDependenteIsentoColsDefEntity> getDao() {
		return inputDependenteIsentoColsDefDao;
	}

	@Override
	protected AbstractMapper<InputDependenteIsentoColsDef, InputDependenteIsentoColsDefUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<InputDependenteIsentoColsDef, InputDependenteIsentoColsDefEntity> getEntityMapper() {
		return entityMapper;
	}
}
