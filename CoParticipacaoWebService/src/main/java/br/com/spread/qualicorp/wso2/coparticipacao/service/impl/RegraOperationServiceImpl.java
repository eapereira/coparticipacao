package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraOperationDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraOperationEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraOperationEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraOperationUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraOperationUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraOperationServiceImpl extends
		AbstractServiceImpl<RegraOperationUi, RegraOperationEntity, RegraOperation> {
	private static final Logger LOGGER = LogManager
			.getLogger(RegraOperationServiceImpl.class);

	@Autowired
	private RegraOperationDao regraOperationDao;

	@Autowired
	private RegraOperationUiMapper uiMapper;

	@Autowired
	private RegraOperationEntityMapper entityMapper;

	@Override
	protected AbstractDao<RegraOperationEntity> getDao() {
		return regraOperationDao;
	}

	@Override
	protected RegraOperationUi createUi() {
		return new RegraOperationUi();
	}

	@Override
	protected RegraOperationEntity createEntity() {
		return new RegraOperationEntity();
	}

	@Override
	protected AbstractMapper<RegraOperation, RegraOperationUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraOperation, RegraOperationEntity> getEntityMapper() {
		return entityMapper;
	}

}
