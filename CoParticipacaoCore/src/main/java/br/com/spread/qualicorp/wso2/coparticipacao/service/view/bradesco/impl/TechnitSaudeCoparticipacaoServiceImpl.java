package br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco.TechnitSaudeCoparticipacaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.TechnitSaudeCoparticipacaoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.TechnitSaudeCoparticipacaoViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.TechnitSaudeCoparticipacaoViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitSaudeCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.TechnitSaudeCoparticipacaoView;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.TechnitSaudeCoparticipacaoService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TechnitSaudeCoparticipacaoServiceImpl extends
		AbstractServiceImpl<TechnitSaudeCoparticipacaoViewUi, TechnitSaudeCoparticipacaoViewEntity, TechnitSaudeCoparticipacaoView>
		implements TechnitSaudeCoparticipacaoService {

	private static final Logger LOGGER = LogManager.getLogger(TechnitSaudeCoparticipacaoServiceImpl.class);

	@Autowired
	private TechnitSaudeCoparticipacaoDao automindCoparticipacaoDao;

	@Autowired
	private TechnitSaudeCoparticipacaoViewUiMapper uiMapper;

	@Autowired
	private TechnitSaudeCoparticipacaoViewEntityMapper entityMapper;

	public List<TechnitSaudeCoparticipacaoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<TechnitSaudeCoparticipacaoViewUi> automindCoparticipacaoViewUis;

		try {
			LOGGER.info("BEGIN");

			automindCoparticipacaoViewUis = entityToUi(automindCoparticipacaoDao.listByMesAndAno(mes, ano));

			LOGGER.info("END");
			return automindCoparticipacaoViewUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected TechnitSaudeCoparticipacaoViewUi createUi() {
		return new TechnitSaudeCoparticipacaoViewUi();
	}

	@Override
	protected TechnitSaudeCoparticipacaoViewEntity createEntity() {
		return new TechnitSaudeCoparticipacaoViewEntity();
	}

	@Override
	protected AbstractDao<TechnitSaudeCoparticipacaoViewEntity> getDao() {
		return automindCoparticipacaoDao;
	}

	@Override
	protected AbstractMapper<TechnitSaudeCoparticipacaoView, TechnitSaudeCoparticipacaoViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<TechnitSaudeCoparticipacaoView, TechnitSaudeCoparticipacaoViewEntity> getEntityMapper() {
		return entityMapper;
	}
}
