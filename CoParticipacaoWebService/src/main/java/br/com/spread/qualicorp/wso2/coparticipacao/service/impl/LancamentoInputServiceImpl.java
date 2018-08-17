package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoInputDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.LancamentoInputEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.LancamentoInputUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoInputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LancamentoInputServiceImpl
		extends AbstractServiceImpl<LancamentoInputUi, LancamentoInputEntity, LancamentoInput>
		implements LancamentoInputService {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoInputServiceImpl.class);

	@Autowired
	private LancamentoInputDao inputLancamentoDao;

	@Autowired
	private LancamentoInputUiMapper uiMapper;

	@Autowired
	private LancamentoInputEntityMapper entityMapper;

	@Override
	protected AbstractDao<LancamentoInputEntity> getDao() {
		return inputLancamentoDao;
	}

	@Override
	protected LancamentoInputUi createUi() {
		return new LancamentoInputUi();
	}

	@Override
	protected LancamentoInputEntity createEntity() {
		return new LancamentoInputEntity();
	}

	@Override
	protected AbstractMapper<LancamentoInput, LancamentoInputUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<LancamentoInput, LancamentoInputEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<LancamentoInputUi> listByArquivoInputId(Long id) throws ServiceException {
		List<LancamentoInputUi> inputLancamentoUis;
		List<LancamentoInputEntity> entities;

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
