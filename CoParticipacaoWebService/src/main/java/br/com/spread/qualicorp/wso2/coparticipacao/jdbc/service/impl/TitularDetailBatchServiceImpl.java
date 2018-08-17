package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.TitularDetailEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.TitularDetailUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.AbstractJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.TitularDetailJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.TitularDetailBatchService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TitularDetailBatchServiceImpl
		extends AbstractBatchServiceImpl<TitularDetailUi, TitularDetailEntity, TitularDetail>
		implements TitularDetailBatchService {

	private static final Logger LOGGER = LogManager.getLogger(TitularDetailBatchServiceImpl.class);

	@Autowired
	private TitularDetailUiMapper uiMapper;

	@Autowired
	private TitularDetailEntityMapper entityMapper;

	@Autowired
	private TitularDetailJdbcDao titularDetailJdbcDao;

	@Override
	protected TitularDetailUi createUi() {
		return new TitularDetailUi();
	}

	@Override
	protected TitularDetailEntity createEntity() {
		return new TitularDetailEntity();
	}

	@Override
	protected AbstractDao<TitularDetailEntity> getDao() {
		return null;
	}

	@Override
	protected AbstractMapper<TitularDetail, TitularDetailUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<TitularDetail, TitularDetailEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	protected AbstractJdbcDao<TitularDetailEntity> getJdbcDao() {
		return titularDetailJdbcDao;
	}
}
