package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DependenteIsentoColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DependenteIsentoColsDefEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DependenteIsentoColsDefUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.dependenteIsentoColsDefService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DependenteIsentoColsDefServiceImpl extends
		AbstractServiceImpl<DependenteIsentoColsDefUi, DependenteIsentoColsDefEntity, DependenteIsentoColsDef>
		implements dependenteIsentoColsDefService {

	private static final Logger LOGGER = LogManager
			.getLogger(DependenteIsentoColsDefServiceImpl.class);

	@Autowired
	private DependenteIsentoColsDefDao dependenteIsentoColsDefDao;

	@Autowired
	private DependenteIsentoColsDefUiMapper uiMapper;

	@Autowired
	private DependenteIsentoColsDefEntityMapper entityMapper;

	@Override
	protected DependenteIsentoColsDefUi createUi() {
		return new DependenteIsentoColsDefUi();
	}

	@Override
	protected DependenteIsentoColsDefEntity createEntity() {
		return new DependenteIsentoColsDefEntity();
	}

	@Override
	protected AbstractDao<DependenteIsentoColsDefEntity> getDao() {
		return dependenteIsentoColsDefDao;
	}

	@Override
	protected AbstractMapper<DependenteIsentoColsDef, DependenteIsentoColsDefUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<DependenteIsentoColsDef, DependenteIsentoColsDefEntity> getEntityMapper() {
		return entityMapper;
	}
}
