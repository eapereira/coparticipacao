package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputTitularIsentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularIsentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.InputTitularIsentoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.InputTitularIsentoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputTitularIsentoService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class InputTitularIsentoServiceImpl extends
		AbstractServiceImpl<InputTitularIsentoUi, InputTitularIsentoEntity, InputTitularIsento>
		implements InputTitularIsentoService {

	private static final Logger LOGGER = LogManager
			.getLogger(InputTitularIsentoServiceImpl.class);

	@Autowired
	private InputTitularIsentoDao inputTitularIsentoDao;

	@Autowired
	private InputTitularIsentoUiMapper uiMapper;

	@Autowired
	private InputTitularIsentoEntityMapper entityMapper;

	@Override
	protected InputTitularIsentoUi createUi() {
		return new InputTitularIsentoUi();
	}

	@Override
	protected InputTitularIsentoEntity createEntity() {
		return new InputTitularIsentoEntity();
	}

	@Override
	protected AbstractDao<InputTitularIsentoEntity> getDao() {
		return inputTitularIsentoDao;
	}

	@Override
	protected AbstractMapper<InputTitularIsento, InputTitularIsentoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<InputTitularIsento, InputTitularIsentoEntity> getEntityMapper() {
		return entityMapper;
	}

}
