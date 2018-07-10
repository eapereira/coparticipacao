package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalOperationDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalOperationEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraConditionalOperationEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraConditionalOperationUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalOperationUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraConditionalOperationServiceImpl extends
		AbstractServiceImpl<RegraConditionalOperationUi, RegraConditionalOperationEntity, RegraConditionalOperation> {

	private static final Logger LOGGER = LogManager
			.getLogger(RegraConditionalOperationServiceImpl.class);

	@Autowired
	private RegraConditionalOperationDao regraConditionalOperationDao;

	@Autowired
	private RegraConditionalOperationUiMapper uiMapper;

	@Autowired
	private RegraConditionalOperationEntityMapper entityMapper;

	public RegraConditionalOperationServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected RegraConditionalOperationUi createUi() {
		return new RegraConditionalOperationUi();
	}

	@Override
	protected RegraConditionalOperationEntity createEntity() {
		return new RegraConditionalOperationEntity();
	}

	@Override
	protected AbstractDao<RegraConditionalOperationEntity> getDao() {
		return regraConditionalOperationDao;
	}

	@Override
	protected AbstractMapper<RegraConditionalOperation, RegraConditionalOperationUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraConditionalOperation, RegraConditionalOperationEntity> getEntityMapper() {
		return entityMapper;
	}

}
