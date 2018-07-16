package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputDependenteIsentoColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteIsentoColsEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.InputDependenteIsentoColsEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.InputDependenteIsentoColsUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputDependenteIsentoColsService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class InputDependenteIsentoColsServiceImpl extends
		AbstractServiceImpl<InputDependenteIsentoColsUi, InputDependenteIsentoColsEntity, InputDependenteIsentoCols>
		implements InputDependenteIsentoColsService {

	private static final Logger LOGGER = LogManager
			.getLogger(InputDependenteIsentoColsServiceImpl.class);

	@Autowired
	private InputDependenteIsentoColsDao dependenteIsentoColsDao;

	@Autowired
	private InputDependenteIsentoColsUiMapper uiMapper;

	@Autowired
	private InputDependenteIsentoColsEntityMapper entityMapper;

	@Override
	protected InputDependenteIsentoColsUi createUi() {
		return new InputDependenteIsentoColsUi();
	}

	@Override
	protected InputDependenteIsentoColsEntity createEntity() {
		return new InputDependenteIsentoColsEntity();
	}

	@Override
	protected AbstractDao<InputDependenteIsentoColsEntity> getDao() {
		return dependenteIsentoColsDao;
	}

	@Override
	protected AbstractMapper<InputDependenteIsentoCols, InputDependenteIsentoColsUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<InputDependenteIsentoCols, InputDependenteIsentoColsEntity> getEntityMapper() {
		return entityMapper;
	}

}
