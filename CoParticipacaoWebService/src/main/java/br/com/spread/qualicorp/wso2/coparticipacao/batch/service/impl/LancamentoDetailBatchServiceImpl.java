package br.com.spread.qualicorp.wso2.coparticipacao.batch.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.LancamentoDetailJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.LancamentoDetailBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.LancamentoDetailEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.LancamentoDetailUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LancamentoDetailBatchServiceImpl
		extends AbstractBatchServiceImpl<LancamentoDetailUi, LancamentoDetailEntity, LancamentoDetail>
		implements LancamentoDetailBatchService {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoDetailBatchServiceImpl.class);

	@Autowired
	private LancamentoDetailUiMapper uiMapper;

	@Autowired
	private LancamentoDetailEntityMapper entityMapper;

	@Autowired
	private LancamentoDetailJdbcDao lancamentoDetailJdbcDao;

	@Override
	protected AbstractDao<LancamentoDetailEntity> getDao() {
		return null;
	}

	@Override
	protected LancamentoDetailUi createUi() {
		return new LancamentoDetailUi();
	}

	@Override
	protected LancamentoDetailEntity createEntity() {
		return new LancamentoDetailEntity();
	}

	@Override
	protected AbstractMapper<LancamentoDetail, LancamentoDetailUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<LancamentoDetail, LancamentoDetailEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	protected AbstractJdbcDao<LancamentoDetailEntity> getJdbcDao() {
		return lancamentoDetailJdbcDao;
	}

}
