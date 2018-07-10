package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputTitularDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.InputTitularEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.InputTitularUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputTitularService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class InputTitularServiceImpl extends
		AbstractServiceImpl<InputTitularUi, InputTitularEntity, InputTitular>
		implements InputTitularService {

	private static final Logger LOGGER = LogManager
			.getLogger(InputTitularServiceImpl.class);

	@Autowired
	private InputTitularDao inputTitularDao;

	@Autowired
	private InputTitularUiMapper uiMapper;

	@Autowired
	private InputTitularEntityMapper entityMapper;

	@Override
	protected AbstractDao<InputTitularEntity> getDao() {
		return inputTitularDao;
	}

	public List<InputTitularUi> listByArquivoInputColsDefId(Long id)
			throws ServiceException {
		List<InputTitularUi> inputTitularUis;
		List<InputTitularEntity> inputTitularEntities;

		try {
			LOGGER.info("BEGIN");

			inputTitularEntities = inputTitularDao
					.listByArquivoInputColsDefId(id);
			inputTitularUis = entityToUi(inputTitularEntities);

			LOGGER.info("END");
			return inputTitularUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<InputTitularUi> listByArquivoInputId(Long id)
			throws ServiceException {
		List<InputTitularUi> inputTitularUis;
		List<InputTitularEntity> inputTitularEntities;

		try {
			LOGGER.info("BEGIN");

			inputTitularEntities = inputTitularDao.listByArquivoInputId(id);
			inputTitularUis = entityToUi(inputTitularEntities);

			LOGGER.info("END");
			return inputTitularUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected InputTitularUi createUi() {
		return new InputTitularUi();
	}

	@Override
	protected InputTitularEntity createEntity() {
		return new InputTitularEntity();
	}

	@Override
	protected AbstractMapper<InputTitular, InputTitularUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<InputTitular, InputTitularEntity> getEntityMapper() {
		return entityMapper;
	}

}
