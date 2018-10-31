package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.BeneficiarioColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.BeneficiarioColsEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.BeneficiarioColsEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.BeneficiarioColsUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class BeneficiarioColsServiceImpl
		extends AbstractServiceImpl<BeneficiarioColsUi, BeneficiarioColsEntity, BeneficiarioCols>
		implements BeneficiarioColsService {

	private static final Logger LOGGER = LogManager.getLogger(BeneficiarioColsServiceImpl.class);

	@Autowired
	private BeneficiarioColsDao beneficiarioColsDao;

	@Autowired
	private BeneficiarioColsUiMapper uiMapper;

	@Autowired
	private BeneficiarioColsEntityMapper entityMapper;

	@Override
	protected BeneficiarioColsUi createUi() {
		return new BeneficiarioColsUi();
	}

	@Override
	protected BeneficiarioColsEntity createEntity() {
		return new BeneficiarioColsEntity();
	}

	@Override
	protected BeneficiarioColsDao getDao() {
		return beneficiarioColsDao;
	}

	@Override
	protected BeneficiarioColsUiMapper getUiMapper() {
		return uiMapper;
	}

	@Override
	protected BeneficiarioColsEntityMapper getEntityMapper() {
		return entityMapper;
	}

	public List<BeneficiarioColsUi> listByArquivoInputId(ArquivoInputUi arquivoInputUi) throws ServiceException {
		List<BeneficiarioColsUi> beneficiarioColsUis;

		try {
			LOGGER.info("BEGIN");

			beneficiarioColsUis = entityToUi(beneficiarioColsDao.listByArquivoInputId(arquivoInputUi.getId()));

			LOGGER.info("END");
			return beneficiarioColsUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<BeneficiarioColsUi> listByArquivoInputSheetId(ArquivoInputSheetUi arquivoInputSheetUi)
			throws ServiceException {
		List<BeneficiarioColsUi> beneficiarioColsUis;

		try {
			LOGGER.info("BEGIN");

			beneficiarioColsUis = entityToUi(
					beneficiarioColsDao.listByArquivoInputSheetId(arquivoInputSheetUi.getId()));

			LOGGER.info("END");
			return beneficiarioColsUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
