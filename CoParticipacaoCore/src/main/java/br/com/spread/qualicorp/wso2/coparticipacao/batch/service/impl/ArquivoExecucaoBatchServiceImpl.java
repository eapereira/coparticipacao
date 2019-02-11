package br.com.spread.qualicorp.wso2.coparticipacao.batch.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.ArquivoExecucaoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.ArquivoExecucaoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoExecucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoExecucaoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoExecucaoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoExecucaoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ArquivoExecucaoBatchServiceImpl
		extends AbstractBatchServiceImpl<ArquivoExecucaoUi, ArquivoExecucaoEntity, ArquivoExecucao>
		implements ArquivoExecucaoBatchService {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoExecucaoBatchServiceImpl.class);

	@Autowired
	private ArquivoExecucaoJdbcDao arquivoExecucaoJdbcDao;

	@Autowired
	private ArquivoExecucaoUiMapper uiMapper;

	@Autowired
	private ArquivoExecucaoEntityMapper entityMapper;

	@Override
	protected ArquivoExecucaoUi createUi() {
		return new ArquivoExecucaoUi();
	}

	@Override
	protected ArquivoExecucaoEntity createEntity() {
		return new ArquivoExecucaoEntity();
	}

	@Override
	protected AbstractMapper<ArquivoExecucao, ArquivoExecucaoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ArquivoExecucao, ArquivoExecucaoEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	protected AbstractDao<ArquivoExecucaoEntity> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractBatchDao<ArquivoExecucaoEntity> getJdbcDao() {
		return arquivoExecucaoJdbcDao;
	}

	@Override
	protected void logBatchInfo(ArquivoExecucaoUi arquivoExecucaoUi) throws ServiceException {
		LOGGER.info("CD_MES: ..................... [{}]", arquivoExecucaoUi.getMes());
		LOGGER.info("CD_ANO: ..................... [{}]", arquivoExecucaoUi.getAno());
		LOGGER.info("CD_CONTRATO: ................ [{}]", arquivoExecucaoUi.getContrato().getCdContrato());
		LOGGER.info("NM_CONTRATO: ................ [{}]", arquivoExecucaoUi.getContrato().getNameContrato());
		LOGGER.info("NM_ARQUIVO_INPUT: ........... [{}]", arquivoExecucaoUi.getNameArquivoInput());
		LOGGER.info("NM_ARQUIVO_OUTPUT: .......... [{}]", arquivoExecucaoUi.getNameArquivoOutput());
		LOGGER.info("STATUS: ..................... [{}]", arquivoExecucaoUi.getStatusExecucaoType().getDescription());
		LOGGER.info("DT_STARTED: ................. [{}]", arquivoExecucaoUi.getStarted());
		LOGGER.info("DT_FINNISHED: ............... [{}]", arquivoExecucaoUi.getFinnished());
		LOGGER.info("ERROR_MESSAGE: .............. [{}]", arquivoExecucaoUi.getErrorMessage());
		LOGGER.info("USER_CREATED: ............... [{}]", arquivoExecucaoUi.getUserCreated().getNameLogin());
	}

}
