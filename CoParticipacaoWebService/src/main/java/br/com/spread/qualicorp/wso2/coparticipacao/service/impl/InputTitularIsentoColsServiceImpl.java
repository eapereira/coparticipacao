package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputTitularIsentoColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularIsentoColsEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.InputTitularIsentoColsEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.InputTitularIsentoColsUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoColsUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class InputTitularIsentoColsServiceImpl extends
		AbstractServiceImpl<InputTitularIsentoColsUi, InputTitularIsentoColsEntity, InputTitularIsentoCols> {

	private static final Logger LOGGER = LogManager
			.getLogger(InputTitularIsentoColsServiceImpl.class);

	@Autowired
	private InputTitularIsentoColsDao inputTitularIsentoColsDao;

	@Autowired
	private InputTitularIsentoColsUiMapper uiMapper;

	@Autowired
	private InputTitularIsentoColsEntityMapper entityMapper;

	@Override
	protected InputTitularIsentoColsUi createUi() {
		return new InputTitularIsentoColsUi();
	}

	@Override
	protected InputTitularIsentoColsEntity createEntity() {
		return new InputTitularIsentoColsEntity();
	}

	@Override
	protected AbstractDao<InputTitularIsentoColsEntity> getDao() {
		return inputTitularIsentoColsDao;
	}

	@Override
	protected AbstractMapper<InputTitularIsentoCols, InputTitularIsentoColsUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<InputTitularIsentoCols, InputTitularIsentoColsEntity> getEntityMapper() {
		return entityMapper;
	}

}
