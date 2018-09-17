package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularIsentoColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularIsentoColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.TitularIsentoColsDefEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.TitularIsentoColsDefUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularIsentoColsDefService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TitularIsentoColsDefServiceImpl extends
		AbstractServiceImpl<TitularIsentoColsDefUi, TitularIsentoColsDefEntity, TitularIsentoColsDef>
		implements TitularIsentoColsDefService {

	private static final Logger LOGGER = LogManager
			.getLogger(TitularIsentoColsDefServiceImpl.class);

	@Autowired
	private TitularIsentoColsDefDao titularIsentoColsDefDao;

	@Autowired
	private TitularIsentoColsDefUiMapper uiMapper;

	@Autowired
	private TitularIsentoColsDefEntityMapper entityMapper;

	@Override
	protected TitularIsentoColsDefUi createUi() {
		return new TitularIsentoColsDefUi();
	}

	@Override
	protected TitularIsentoColsDefEntity createEntity() {
		return new TitularIsentoColsDefEntity();
	}

	@Override
	protected AbstractDao<TitularIsentoColsDefEntity> getDao() {
		return titularIsentoColsDefDao;
	}

	@Override
	protected AbstractMapper<TitularIsentoColsDef, TitularIsentoColsDefUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<TitularIsentoColsDef, TitularIsentoColsDefEntity> getEntityMapper() {
		return entityMapper;
	}

}
