package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.IsentoInputSheetDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.IsentoInputSheetEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.IsentoInputSheetEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.IsentoInputSheetUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoInputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class IsentoInputSheetServiceImpl
		extends AbstractServiceImpl<IsentoInputSheetUi, IsentoInputSheetEntity, IsentoInputSheet>
		implements IsentoInputSheetService {

	private static final Logger LOGGER = LogManager.getLogger(IsentoInputSheetServiceImpl.class);

	@Autowired
	private IsentoInputSheetDao isentoInputSheetDao;

	@Autowired
	private IsentoInputSheetUiMapper uiMapper;

	@Autowired
	private IsentoInputSheetEntityMapper entityMapper;

	public List<IsentoInputSheetUi> listByArquivoInputId(ArquivoInputUi arquivoInputUi) throws ServiceException {
		List<IsentoInputSheetUi> isentoInputSheetUis;

		try {
			LOGGER.info("BEGIN");

			isentoInputSheetUis = entityToUi(isentoInputSheetDao.listByArquivoInputId(arquivoInputUi.getId()));

			LOGGER.info("END");
			return isentoInputSheetUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected IsentoInputSheetUi createUi() {
		return new IsentoInputSheetUi();
	}

	@Override
	protected IsentoInputSheetEntity createEntity() {
		return new IsentoInputSheetEntity();
	}

	@Override
	protected AbstractDao<IsentoInputSheetEntity> getDao() {
		return isentoInputSheetDao;
	}

	@Override
	protected AbstractMapper<IsentoInputSheet, IsentoInputSheetUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<IsentoInputSheet, IsentoInputSheetEntity> getEntityMapper() {
		return entityMapper;
	}

	public IsentoInputSheetUi findByArquivoInputSheet(ArquivoInputSheetUi arquivoInputSheetUi) throws ServiceException {
		IsentoInputSheetUi isentoInputSheetUi;

		try {
			LOGGER.info("BEGIN");

			isentoInputSheetUi = entityToUi(isentoInputSheetDao.findByArquivoInputSheet(arquivoInputSheetUi.getId()));

			LOGGER.info("END");
			return isentoInputSheetUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
