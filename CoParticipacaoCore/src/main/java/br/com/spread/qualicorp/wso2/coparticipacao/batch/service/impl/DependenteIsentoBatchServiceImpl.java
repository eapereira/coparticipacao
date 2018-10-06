package br.com.spread.qualicorp.wso2.coparticipacao.batch.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.DependenteIsentoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DependenteIsentoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DependenteIsentoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DependenteIsentoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DependenteIsentoBatchServiceImpl
		extends AbstractBatchServiceImpl<DependenteIsentoUi, DependenteIsentoEntity, DependenteIsento>
		implements DependenteIsentoBatchService {

	private static final Logger LOGGER = LogManager.getLogger(DependenteIsentoBatchServiceImpl.class);

	@Autowired
	private DependenteIsentoJdbcDao dependenteIsentoJdbcDao;

	@Autowired
	private DependenteIsentoUiMapper uiMapper;

	@Autowired
	private DependenteIsentoEntityMapper entityMapper;

	@Override
	protected DependenteIsentoUi createUi() {
		return new DependenteIsentoUi();
	}

	@Override
	protected DependenteIsentoEntity createEntity() {
		return new DependenteIsentoEntity();
	}

	@Override
	protected AbstractDao<DependenteIsentoEntity> getDao() {
		return null;
	}

	@Override
	protected AbstractMapper<DependenteIsento, DependenteIsentoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<DependenteIsento, DependenteIsentoEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	protected AbstractBatchDao<DependenteIsentoEntity> getJdbcDao() {
		return dependenteIsentoJdbcDao;
	}

}
