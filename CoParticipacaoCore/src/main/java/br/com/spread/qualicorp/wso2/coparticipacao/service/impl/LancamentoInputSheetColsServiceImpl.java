package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoInputSheetColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputSheetColsEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.LancamentoInputSheetColsEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.LancamentoInputSheetColsUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoInputSheetColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LancamentoInputSheetColsServiceImpl extends
		AbstractServiceImpl<LancamentoInputSheetColsUi, LancamentoInputSheetColsEntity, LancamentoInputSheetCols>
		implements LancamentoInputSheetColsService {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoInputSheetColsServiceImpl.class);

	@Autowired
	private LancamentoInputSheetColsDao lancamentoInputSheetColsDao;

	@Autowired
	private LancamentoInputSheetColsUiMapper uiMapper;

	@Autowired
	private LancamentoInputSheetColsEntityMapper entityMapper;

	@Override
	protected LancamentoInputSheetColsUi createUi() {
		return new LancamentoInputSheetColsUi();
	}

	@Override
	protected LancamentoInputSheetColsEntity createEntity() {
		return new LancamentoInputSheetColsEntity();
	}

	@Override
	protected AbstractDao<LancamentoInputSheetColsEntity> getDao() {
		return lancamentoInputSheetColsDao;
	}

	@Override
	protected AbstractMapper<LancamentoInputSheetCols, LancamentoInputSheetColsUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<LancamentoInputSheetCols, LancamentoInputSheetColsEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<LancamentoInputSheetColsUi> listByArquivoInputSheet(ArquivoInputSheetUi arquivoInputSheetUi)
			throws ServiceException {
		List<LancamentoInputSheetColsUi> lancamentoInputSheetColsUis;

		try {
			LOGGER.info("BEGIN");

			lancamentoInputSheetColsUis = entityToUi(
					lancamentoInputSheetColsDao.listByArquivoInputSheetId(arquivoInputSheetUi.getId()));

			LOGGER.info("END");
			return lancamentoInputSheetColsUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
