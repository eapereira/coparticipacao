package br.com.spread.qualicorp.wso2.coparticipacao.batch.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.DependenteDetailJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DependenteDetailBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DependenteDetailEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DependenteDetailUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteDetailUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DependenteDetailBatchServiceImpl
		extends AbstractBatchServiceImpl<DependenteDetailUi, DependenteDetailEntity, DependenteDetail>
		implements DependenteDetailBatchService {

	private static final Logger LOGGER = LogManager.getLogger(DependenteDetailBatchServiceImpl.class);

	@Autowired
	private DependenteDetailUiMapper uiMapper;

	@Autowired
	private DependenteDetailEntityMapper entityMapper;

	@Autowired
	private DependenteDetailJdbcDao dependenteDetailJdbcDao;

	@Override
	protected DependenteDetailUi createUi() {
		return new DependenteDetailUi();
	}

	@Override
	protected DependenteDetailEntity createEntity() {
		return new DependenteDetailEntity();
	}

	@Override
	protected AbstractDao<DependenteDetailEntity> getDao() {
		return null;
	}

	@Override
	protected AbstractMapper<DependenteDetail, DependenteDetailUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<DependenteDetail, DependenteDetailEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	protected AbstractBatchDao<DependenteDetailEntity> getJdbcDao() {
		return dependenteDetailJdbcDao;
	}
}
