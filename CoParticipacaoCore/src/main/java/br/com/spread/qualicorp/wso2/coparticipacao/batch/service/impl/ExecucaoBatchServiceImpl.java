package br.com.spread.qualicorp.wso2.coparticipacao.batch.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.ExecucaoBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.ExecucaoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Execucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ExecucaoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ExecucaoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ExecucaoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ExecucaoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ExecucaoBatchServiceImpl extends AbstractBatchServiceImpl<ExecucaoUi, ExecucaoEntity, Execucao>
		implements ExecucaoBatchService {

	private static final Logger LOGGER = LogManager.getLogger(ExecucaoBatchServiceImpl.class);

	@Autowired
	private ExecucaoUiMapper uiMapper;

	@Autowired
	private ExecucaoEntityMapper entityMapper;

	@Autowired
	private ExecucaoBatchDao execucaoBatchDao;

	@Override
	protected ExecucaoUi createUi() {
		return new ExecucaoUi();
	}

	@Override
	protected ExecucaoEntity createEntity() {
		return new ExecucaoEntity();
	}

	@Override
	protected AbstractDao<ExecucaoEntity> getDao() {
		return null;
	}

	@Override
	protected AbstractMapper<Execucao, ExecucaoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Execucao, ExecucaoEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	protected AbstractBatchDao<ExecucaoEntity> getJdbcDao() {
		return execucaoBatchDao;
	}
}
