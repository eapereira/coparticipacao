package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoOutputDesconhecidoSheetDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecidoSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputDesconhecidoSheetEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoOutputDesconhecidoSheetEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoOutputDesconhecidoSheetUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputDesconhecidoSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputDesconhecidoSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ArquivoOutputDesconhecidoSheetServiceImpl extends
		AbstractServiceImpl<ArquivoOutputDesconhecidoSheetUi, ArquivoOutputDesconhecidoSheetEntity, ArquivoOutputDesconhecidoSheet>
		implements ArquivoOutputDesconhecidoSheetService {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoOutputDesconhecidoSheetServiceImpl.class);

	@Autowired
	private ArquivoOutputDesconhecidoSheetDao arquivoOutputDesconhecidoSheetDao;

	@Autowired
	private ArquivoOutputDesconhecidoSheetUiMapper uiMapper;

	@Autowired
	private ArquivoOutputDesconhecidoSheetEntityMapper entityMapper;

	public List<ArquivoOutputDesconhecidoSheetUi> listByArquivoInputId(ArquivoInputUi arquivoInputUi)
			throws ServiceException {
		List<ArquivoOutputDesconhecidoSheetUi> arquivoOutputDesconhecidoSheetUis;
		try {
			LOGGER.info("BEGIN");

			arquivoOutputDesconhecidoSheetUis = entityToUi(
					arquivoOutputDesconhecidoSheetDao.listByArquivoInputId(arquivoInputUi.getId()));

			LOGGER.info("END");
			return arquivoOutputDesconhecidoSheetUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected ArquivoOutputDesconhecidoSheetUi createUi() {
		return new ArquivoOutputDesconhecidoSheetUi();
	}

	@Override
	protected ArquivoOutputDesconhecidoSheetEntity createEntity() {
		return new ArquivoOutputDesconhecidoSheetEntity();
	}

	@Override
	protected AbstractDao<ArquivoOutputDesconhecidoSheetEntity> getDao() {
		return arquivoOutputDesconhecidoSheetDao;
	}

	@Override
	protected AbstractMapper<ArquivoOutputDesconhecidoSheet, ArquivoOutputDesconhecidoSheetUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ArquivoOutputDesconhecidoSheet, ArquivoOutputDesconhecidoSheetEntity> getEntityMapper() {
		return entityMapper;
	}

}
