package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.SpreadSaudeCoparticipacaoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.SpreadSaudeCoparticipacaoViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.SpreadSaudeCoparticipacaoViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.SpreadSaudeCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.SpreadSaudeCoparticipacaoView;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco.SpreadSaudeCoparticipacaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.SpreadSaudeCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class SpreadSaudeCoparticipacaoServiceImpl extends
		AbstractServiceImpl<SpreadSaudeCoparticipacaoViewUi, SpreadSaudeCoparticipacaoViewEntity, SpreadSaudeCoparticipacaoView>
		implements SpreadSaudeCoparticipacaoService {

	private static final Logger LOGGER = LogManager.getLogger(SpreadSaudeCoparticipacaoServiceImpl.class);

	@Autowired
	private SpreadSaudeCoparticipacaoViewUiMapper uiMapper;

	@Autowired
	private SpreadSaudeCoparticipacaoViewEntityMapper entityMapper;

	@Autowired
	private SpreadSaudeCoparticipacaoDao spreadSaudeCoparticipacaoDao;

	@Override
	public List<SpreadSaudeCoparticipacaoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<SpreadSaudeCoparticipacaoViewUi> spreadSaudeCoparticipacaoViewUis;

		try {
			LOGGER.info("BEGIN");

			spreadSaudeCoparticipacaoViewUis = entityToUi(spreadSaudeCoparticipacaoDao.listByMesAndAno(mes, ano));

			LOGGER.info("END");
			return spreadSaudeCoparticipacaoViewUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected SpreadSaudeCoparticipacaoViewUi createUi() {
		return new SpreadSaudeCoparticipacaoViewUi();
	}

	@Override
	protected SpreadSaudeCoparticipacaoViewEntity createEntity() {
		return new SpreadSaudeCoparticipacaoViewEntity();
	}

	@Override
	protected AbstractDao<SpreadSaudeCoparticipacaoViewEntity> getDao() {
		return spreadSaudeCoparticipacaoDao;
	}

	@Override
	protected AbstractMapper<SpreadSaudeCoparticipacaoView, SpreadSaudeCoparticipacaoViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<SpreadSaudeCoparticipacaoView, SpreadSaudeCoparticipacaoViewEntity> getEntityMapper() {
		return entityMapper;
	}

}
