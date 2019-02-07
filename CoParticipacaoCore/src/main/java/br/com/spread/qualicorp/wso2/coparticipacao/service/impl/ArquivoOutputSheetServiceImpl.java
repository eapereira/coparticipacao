package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoOutputSheetDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputSheetEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoOutputSheetEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoOutputSheetUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ArquivoOutputSheetServiceImpl extends AbstractServiceImpl<ArquivoOutputSheetUi, ArquivoOutputSheetEntity, ArquivoOutputSheet>
		implements ArquivoOutputSheetService {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoOutputSheetServiceImpl.class);

	@Autowired
	private ArquivoOutputSheetDao arquivoOutputSheetDao;

	@Autowired
	private ArquivoOutputSheetUiMapper uiMapper;

	@Autowired
	private ArquivoOutputSheetEntityMapper entityMapper;

	@Override
	protected ArquivoOutputSheetUi createUi() {
		return new ArquivoOutputSheetUi();
	}

	@Override
	protected ArquivoOutputSheetEntity createEntity() {
		return new ArquivoOutputSheetEntity();
	}

	@Override
	protected AbstractDao<ArquivoOutputSheetEntity> getDao() {
		return arquivoOutputSheetDao;
	}

	@Override
	protected AbstractMapper<ArquivoOutputSheet, ArquivoOutputSheetUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ArquivoOutputSheet, ArquivoOutputSheetEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<ArquivoOutputSheetUi> listByArquivoOutputId(ArquivoOutputUi arquivoOutputUi) throws ServiceException {
		List<ArquivoOutputSheetUi> arquivoOutputSheetUis;

		try {
			LOGGER.info("BEGIN");

			arquivoOutputSheetUis = entityToUi(arquivoOutputSheetDao.listByArquivoOutputId(arquivoOutputUi.getId()));

			LOGGER.info("END");
			return arquivoOutputSheetUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
