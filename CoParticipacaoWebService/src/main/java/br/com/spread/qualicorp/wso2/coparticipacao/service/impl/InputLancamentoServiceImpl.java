package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputLancamentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputLancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputLancamentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.InputLancamentoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.InputLancamentoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputLancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputLancamentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class InputLancamentoServiceImpl extends
		AbstractServiceImpl<InputLancamentoUi, InputLancamentoEntity, InputLancamento>
		implements InputLancamentoService {

	private static final Logger LOGGER = LogManager
			.getLogger(InputLancamentoServiceImpl.class);

	@Autowired
	private InputLancamentoDao inputLancamentoDao;

	@Autowired
	private InputLancamentoUiMapper uiMapper;

	@Autowired
	private InputLancamentoEntityMapper entityMapper;

	@Override
	protected AbstractDao<InputLancamentoEntity> getDao() {
		return inputLancamentoDao;
	}

	public List<InputLancamentoUi> listByArquivoInputColsDefId(Long id)
			throws ServiceException {
		List<InputLancamentoUi> inputLancamentoUis;
		List<InputLancamentoEntity> entities;

		try {
			LOGGER.info("BEGIN");
			entities = inputLancamentoDao.listByArquivoInputColsDefId(id);

			inputLancamentoUis = entityToUi(entities);

			LOGGER.info("END");
			return inputLancamentoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected InputLancamentoUi createUi() {
		return new InputLancamentoUi();
	}

	@Override
	protected InputLancamentoEntity createEntity() {
		return new InputLancamentoEntity();
	}

	@Override
	protected AbstractMapper<InputLancamento, InputLancamentoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<InputLancamento, InputLancamentoEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<InputLancamentoUi> listByArquivoInputId(Long id)
			throws ServiceException {
		List<InputLancamentoUi> inputLancamentoUis;
		List<InputLancamentoEntity> entities;

		try {
			LOGGER.info("BEGIN");
			entities = inputLancamentoDao.listByArquivoInputId(id);

			inputLancamentoUis = entityToUi(entities);

			LOGGER.info("END");
			return inputLancamentoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
