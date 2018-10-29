package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoInputSheetDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputSheetEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.LancamentoInputSheetEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.LancamentoInputSheetUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoInputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LancamentoInputSheetServiceImpl
		extends AbstractServiceImpl<LancamentoInputSheetUi, LancamentoInputSheetEntity, LancamentoInputSheet>
		implements LancamentoInputSheetService {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoInputSheetServiceImpl.class);

	@Autowired
	private LancamentoInputSheetDao lancamentoInputSheetDao;

	@Autowired
	private LancamentoInputSheetUiMapper uiMapper;

	@Autowired
	private LancamentoInputSheetEntityMapper entityMapper;

	@Override
	protected LancamentoInputSheetUi createUi() {
		return new LancamentoInputSheetUi();
	}

	@Override
	protected LancamentoInputSheetEntity createEntity() {
		return new LancamentoInputSheetEntity();
	}

	@Override
	protected AbstractDao<LancamentoInputSheetEntity> getDao() {
		return lancamentoInputSheetDao;
	}

	@Override
	protected AbstractMapper<LancamentoInputSheet, LancamentoInputSheetUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<LancamentoInputSheet, LancamentoInputSheetEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<LancamentoInputSheetUi> listByArquivoInput(ArquivoInputUi arquivoInputUi) throws ServiceException {
		List<LancamentoInputSheetUi> lancamentoInputSheetUis;

		try {
			LOGGER.info("BEGIN");

			lancamentoInputSheetUis = entityToUi(lancamentoInputSheetDao.listByArquivoInputId(arquivoInputUi.getId()));

			LOGGER.info("END");
			return lancamentoInputSheetUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
