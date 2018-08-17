package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoInputColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputColsEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.LancamentoInputColsEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.LancamentoInputColsUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoInputColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LancamentoInputColsServiceImpl
		extends AbstractServiceImpl<LancamentoInputColsUi, LancamentoInputColsEntity, LancamentoInputCols>
		implements LancamentoInputColsService {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoInputColsServiceImpl.class);

	@Autowired
	private LancamentoInputColsDao lancamentoInputColsDao;

	@Autowired
	private LancamentoInputColsUiMapper uiMapper;

	@Autowired
	private LancamentoInputColsEntityMapper entityMapper;

	public List<LancamentoInputColsUi> listByArquivoInputId(ArquivoInputUi arquivoInputUi) throws ServiceException {
		List<LancamentoInputColsUi> lancamentoInputColsUis;

		try {
			LOGGER.info("BEGIN");

			lancamentoInputColsUis = entityToUi(lancamentoInputColsDao.listByArquivoInputId(arquivoInputUi.getId()));

			LOGGER.info("END");
			return lancamentoInputColsUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected LancamentoInputColsUi createUi() {
		return new LancamentoInputColsUi();
	}

	@Override
	protected LancamentoInputColsEntity createEntity() {
		return new LancamentoInputColsEntity();
	}

	@Override
	protected AbstractDao<LancamentoInputColsEntity> getDao() {
		return lancamentoInputColsDao;
	}

	@Override
	protected AbstractMapper<LancamentoInputCols, LancamentoInputColsUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<LancamentoInputCols, LancamentoInputColsEntity> getEntityMapper() {
		return entityMapper;
	}

}
