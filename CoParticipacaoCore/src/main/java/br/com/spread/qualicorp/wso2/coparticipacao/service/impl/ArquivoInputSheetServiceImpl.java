package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoInputSheetDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputSheetEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoInputSheetEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoInputSheetUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author edson.apereira
 *
 */
@Service
public class ArquivoInputSheetServiceImpl
		extends AbstractServiceImpl<ArquivoInputSheetUi, ArquivoInputSheetEntity, ArquivoInputSheet>
		implements ArquivoInputSheetService {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoInputSheetServiceImpl.class);

	@Autowired
	private ArquivoInputSheetUiMapper uiMapper;

	@Autowired
	private ArquivoInputSheetEntityMapper entityMapper;

	@Autowired
	private ArquivoInputSheetDao arquivoInputSheetDao;

	@Override
	protected ArquivoInputSheetUi createUi() {
		return new ArquivoInputSheetUi();
	}

	@Override
	protected ArquivoInputSheetEntity createEntity() {
		return new ArquivoInputSheetEntity();
	}

	@Override
	protected AbstractDao<ArquivoInputSheetEntity> getDao() {
		return arquivoInputSheetDao;
	}

	@Override
	protected AbstractMapper<ArquivoInputSheet, ArquivoInputSheetUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ArquivoInputSheet, ArquivoInputSheetEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<ArquivoInputSheetUi> listByArquivoInput(ArquivoInputUi arquivoInputUi) throws ServiceException {
		List<ArquivoInputSheetUi> arquivoInputSheetUis;

		try {
			LOGGER.info("BEGIN");

			arquivoInputSheetUis = entityToUi(arquivoInputSheetDao.listByArquivoInputId(arquivoInputUi.getId()));

			LOGGER.info("END");
			return arquivoInputSheetUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
