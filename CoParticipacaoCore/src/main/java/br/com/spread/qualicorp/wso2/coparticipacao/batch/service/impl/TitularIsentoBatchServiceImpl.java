package br.com.spread.qualicorp.wso2.coparticipacao.batch.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.TitularIsentoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.TitularIsentoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularIsentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.TitularIsentoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.TitularIsentoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TitularIsentoBatchServiceImpl
		extends AbstractBatchServiceImpl<TitularIsentoUi, TitularIsentoEntity, TitularIsento>
		implements TitularIsentoBatchService {

	private static final Logger LOGGER = LogManager.getLogger(TitularIsentoBatchServiceImpl.class);

	@Autowired
	private TitularIsentoJdbcDao titularIsentoJdbcDao;

	@Autowired
	private TitularIsentoUiMapper uiMapper;

	@Autowired
	private TitularIsentoEntityMapper entityMapper;

	@Override
	protected TitularIsentoUi createUi() {
		return new TitularIsentoUi();
	}

	@Override
	protected TitularIsentoEntity createEntity() {
		return new TitularIsentoEntity();
	}

	@Override
	protected AbstractDao<TitularIsentoEntity> getDao() {
		return null;
	}

	@Override
	protected AbstractMapper<TitularIsento, TitularIsentoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<TitularIsento, TitularIsentoEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	protected AbstractBatchDao<TitularIsentoEntity> getJdbcDao() {
		return titularIsentoJdbcDao;
	}
}
