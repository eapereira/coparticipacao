package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputTitularIsentoColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularIsentoColsEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.InputTitularIsentoColsEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.InputTitularIsentoColsUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputTitularIsentoColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class InputTitularIsentoColsServiceImpl extends
		AbstractServiceImpl<InputTitularIsentoColsUi, InputTitularIsentoColsEntity, InputTitularIsentoCols>
		implements InputTitularIsentoColsService {

	private static final Logger LOGGER = LogManager
			.getLogger(InputTitularIsentoColsServiceImpl.class);

	@Autowired
	private InputTitularIsentoColsDao inputTitularIsentoColsDao;

	@Autowired
	private InputTitularIsentoColsUiMapper uiMapper;

	@Autowired
	private InputTitularIsentoColsEntityMapper entityMapper;

	@Override
	protected InputTitularIsentoColsUi createUi() {
		return new InputTitularIsentoColsUi();
	}

	@Override
	protected InputTitularIsentoColsEntity createEntity() {
		return new InputTitularIsentoColsEntity();
	}

	@Override
	protected AbstractDao<InputTitularIsentoColsEntity> getDao() {
		return inputTitularIsentoColsDao;
	}

	@Override
	protected AbstractMapper<InputTitularIsentoCols, InputTitularIsentoColsUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<InputTitularIsentoCols, InputTitularIsentoColsEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<InputTitularIsentoColsUi> listByInputTitularIsentoId(
			InputTitularIsentoUi inputTitularIsentoUi) throws ServiceException {
		List<InputTitularIsentoColsUi> inputTitularIsentoColsUis;

		try {
			LOGGER.info("BEGIN");

			inputTitularIsentoColsUis = entityToUi(
					inputTitularIsentoColsDao.listByInputTitularIsentoId(
							inputTitularIsentoUi.getId()));

			LOGGER.info("END");
			return inputTitularIsentoColsUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<InputTitularIsentoColsUi> listByArquivoInputId(
			ArquivoInputUi arquivoInputUi) throws ServiceException {
		List<InputTitularIsentoColsUi> inputTitularIsentoColsUis;

		try {
			LOGGER.info("BEGIN");

			inputTitularIsentoColsUis = entityToUi(
					inputTitularIsentoColsDao
							.listByArquivoInputId(arquivoInputUi.getId()));

			LOGGER.info("END");
			return inputTitularIsentoColsUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
