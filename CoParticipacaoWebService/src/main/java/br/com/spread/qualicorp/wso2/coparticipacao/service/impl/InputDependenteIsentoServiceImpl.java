package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputDependenteIsentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteIsentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.InputDependenteIsentoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.InputDependenteIsentoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputDependenteIsentoService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class InputDependenteIsentoServiceImpl extends
		AbstractServiceImpl<InputDependenteIsentoUi, InputDependenteIsentoEntity, InputDependenteIsento>
		implements InputDependenteIsentoService {

	private static final Logger LOGGER = LogManager
			.getLogger(InputDependenteIsentoServiceImpl.class);

	@Autowired
	private InputDependenteIsentoDao inputDependenteIsentoDao;

	@Autowired
	private InputDependenteIsentoUiMapper uiMapper;

	@Autowired
	private InputDependenteIsentoEntityMapper entityMapper;

	@Override
	protected InputDependenteIsentoUi createUi() {
		return new InputDependenteIsentoUi();
	}

	@Override
	protected InputDependenteIsentoEntity createEntity() {
		return new InputDependenteIsentoEntity();
	}

	@Override
	protected AbstractDao<InputDependenteIsentoEntity> getDao() {
		return inputDependenteIsentoDao;
	}

	@Override
	protected AbstractMapper<InputDependenteIsento, InputDependenteIsentoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<InputDependenteIsento, InputDependenteIsentoEntity> getEntityMapper() {
		return entityMapper;
	}
}
