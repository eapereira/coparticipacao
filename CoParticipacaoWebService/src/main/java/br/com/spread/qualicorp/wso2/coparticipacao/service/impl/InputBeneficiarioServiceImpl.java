package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputBeneficiarioDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputBeneficiario;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputBeneficiarioEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.InputBeneficiarioEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.InputBeneficiarioUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputBeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputBeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class InputBeneficiarioServiceImpl extends
		AbstractServiceImpl<InputBeneficiarioUi, InputBeneficiarioEntity, InputBeneficiario>
		implements InputBeneficiarioService {

	private static final Logger LOGGER = LogManager
			.getLogger(InputBeneficiarioServiceImpl.class);

	@Autowired
	private InputBeneficiarioDao inputBeneficiarioDao;

	@Autowired
	private InputBeneficiarioUiMapper uiMapper;

	@Autowired
	private InputBeneficiarioEntityMapper entityMapper;

	@Override
	protected InputBeneficiarioUi createUi() {
		return new InputBeneficiarioUi();
	}

	@Override
	protected InputBeneficiarioEntity createEntity() {
		return new InputBeneficiarioEntity();
	}

	@Override
	protected AbstractDao<InputBeneficiarioEntity> getDao() {
		return inputBeneficiarioDao;
	}

	@Override
	protected AbstractMapper<InputBeneficiario, InputBeneficiarioUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<InputBeneficiario, InputBeneficiarioEntity> getEntityMapper() {
		return entityMapper;
	}

	public InputBeneficiarioUi findByArquivoInputId(
			ArquivoInputUi arquivoInputUi) throws ServiceException {
		InputBeneficiarioUi inputBeneficiarioUi;

		try {
			LOGGER.info("BEGIN");

			inputBeneficiarioUi = entityToUi(
					inputBeneficiarioDao
							.findByArquivoInputId(arquivoInputUi.getId()));

			LOGGER.info("END");
			return inputBeneficiarioUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
