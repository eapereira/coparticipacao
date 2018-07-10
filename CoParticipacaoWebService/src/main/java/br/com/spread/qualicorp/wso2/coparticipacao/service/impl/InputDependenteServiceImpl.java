package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputDependenteDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.InputDependenteEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.InputDependenteUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputDependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class InputDependenteServiceImpl extends
		AbstractServiceImpl<InputDependenteUi, InputDependenteEntity, InputDependente>
		implements InputDependenteService {

	private static final Logger LOGGER = LogManager
			.getLogger(InputDependenteServiceImpl.class);

	@Autowired
	private InputDependenteDao inputDependenteDao;

	@Autowired
	private InputDependenteUiMapper uiMapper;

	@Autowired
	private InputDependenteEntityMapper entityMapper;

	@Override
	protected AbstractDao<InputDependenteEntity> getDao() {
		return inputDependenteDao;
	}

	public List<InputDependenteUi> listByArquivoInputColsDefId(Long id)
			throws ServiceException {
		List<InputDependenteUi> inputDependenteUis;
		List<InputDependenteEntity> inputDependenteEntities;

		try {
			LOGGER.info("BEGIN");

			inputDependenteEntities = inputDependenteDao
					.listByArquivoInputColsDefId(id);
			inputDependenteUis = entityToUi(inputDependenteEntities);

			LOGGER.info("END");
			return inputDependenteUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<InputDependenteUi> listByArquivoInputId(Long id)
			throws ServiceException {
		List<InputDependenteUi> inputDependenteUis;
		List<InputDependenteEntity> inputDependenteEntities;

		try {
			LOGGER.info("BEGIN");

			inputDependenteEntities = inputDependenteDao
					.listByArquivoInputId(id);
			inputDependenteUis = entityToUi(inputDependenteEntities);

			LOGGER.info("END");
			return inputDependenteUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected InputDependenteUi createUi() {
		return new InputDependenteUi();
	}

	@Override
	protected InputDependenteEntity createEntity() {
		return new InputDependenteEntity();
	}

	@Override
	protected AbstractMapper<InputDependente, InputDependenteUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<InputDependente, InputDependenteEntity> getEntityMapper() {
		return entityMapper;
	}

}
