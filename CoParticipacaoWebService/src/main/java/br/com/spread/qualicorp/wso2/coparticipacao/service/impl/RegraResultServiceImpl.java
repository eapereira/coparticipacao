package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraResultDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraResult;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraResultEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraResultEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraResultUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraResultUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraResultServiceImpl extends
		AbstractServiceImpl<RegraResultUi, RegraResultEntity, RegraResult> {

	private static final Logger LOGGER = LogManager
			.getLogger(RegraResultServiceImpl.class);

	@Autowired
	private RegraResultDao regraResultDao;

	@Autowired
	private RegraResultEntityMapper entityMapper;

	@Autowired
	private RegraResultUiMapper uiMapper;

	@Override
	protected RegraResultUi createUi() {
		return new RegraResultUi();
	}

	@Override
	protected RegraResultEntity createEntity() {
		return new RegraResultEntity();
	}

	@Override
	protected AbstractDao<RegraResultEntity> getDao() {
		return regraResultDao;
	}

	@Override
	protected AbstractMapper<RegraResult, RegraResultUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraResult, RegraResultEntity> getEntityMapper() {
		return entityMapper;
	}

}
