package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputDependenteIsentoColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteIsentoColsEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.InputDependenteIsentoColsEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.InputDependenteIsentoColsUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputDependenteIsentoColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class InputDependenteIsentoColsServiceImpl extends
		AbstractServiceImpl<InputDependenteIsentoColsUi, InputDependenteIsentoColsEntity, InputDependenteIsentoCols>
		implements InputDependenteIsentoColsService {

	private static final Logger LOGGER = LogManager
			.getLogger(InputDependenteIsentoColsServiceImpl.class);

	@Autowired
	private InputDependenteIsentoColsDao inputDependenteIsentoColsDao;

	@Autowired
	private InputDependenteIsentoColsUiMapper uiMapper;

	@Autowired
	private InputDependenteIsentoColsEntityMapper entityMapper;

	@Override
	protected InputDependenteIsentoColsUi createUi() {
		return new InputDependenteIsentoColsUi();
	}

	@Override
	protected InputDependenteIsentoColsEntity createEntity() {
		return new InputDependenteIsentoColsEntity();
	}

	@Override
	protected AbstractDao<InputDependenteIsentoColsEntity> getDao() {
		return inputDependenteIsentoColsDao;
	}

	@Override
	protected AbstractMapper<InputDependenteIsentoCols, InputDependenteIsentoColsUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<InputDependenteIsentoCols, InputDependenteIsentoColsEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<InputDependenteIsentoColsUi> listByInputDependenteIsentoId(
			InputDependenteIsentoUi inputDependenteIsentoUi)
			throws ServiceException {
		List<InputDependenteIsentoColsUi> inputDependenteIsentoCols;

		try {
			LOGGER.info("BEGIN");

			inputDependenteIsentoCols = entityToUi(
					inputDependenteIsentoColsDao.listByInputDependenteIsentoId(
							inputDependenteIsentoUi.getId()));

			LOGGER.info("END");
			return inputDependenteIsentoCols;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<InputDependenteIsentoColsUi> listByArquivoInput(
			ArquivoInputUi arquivoInputUi) throws ServiceException {
		List<InputDependenteIsentoColsUi> inputDependenteIsentoCols;

		try {
			LOGGER.info("BEGIN");

			inputDependenteIsentoCols = entityToUi(
					inputDependenteIsentoColsDao
							.listByArquivoInputId(arquivoInputUi.getId()));

			LOGGER.info("END");
			return inputDependenteIsentoCols;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
