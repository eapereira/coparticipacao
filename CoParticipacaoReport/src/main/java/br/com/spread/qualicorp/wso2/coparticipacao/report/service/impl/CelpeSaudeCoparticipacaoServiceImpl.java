package br.com.spread.qualicorp.wso2.coparticipacao.report.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.CelpeSaudeCoparticipacaoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.CelpeSaudeCoparticipacaoViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.CelpeSaudeCoparticipacaoViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.CelpeSaudeCoparticipacaoView;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.CelpeSaudeCoparticipacaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.CelpeSaudeCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class CelpeSaudeCoparticipacaoServiceImpl extends
		AbstractServiceImpl<CelpeSaudeCoparticipacaoViewUi, CelpeSaudeCoparticipacaoViewEntity, CelpeSaudeCoparticipacaoView>
		implements CelpeSaudeCoparticipacaoService {

	private static final Logger LOGGER = LogManager.getLogger(CelpeSaudeCoparticipacaoServiceImpl.class);

	@Autowired
	private CelpeSaudeCoparticipacaoViewUiMapper uiMapper;

	@Autowired
	private CelpeSaudeCoparticipacaoViewEntityMapper entityMapper;

	@Autowired
	private CelpeSaudeCoparticipacaoDao celpeSaudeCoparticipacaoDao;

	public List<CelpeSaudeCoparticipacaoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<CelpeSaudeCoparticipacaoViewUi> celpeSaudeCoparticipacaoViewUis;
		try {
			LOGGER.info("BEGIN");

			celpeSaudeCoparticipacaoViewUis = entityToUi(celpeSaudeCoparticipacaoDao.listByMesAndAno(mes, ano));

			LOGGER.info("END");
			return celpeSaudeCoparticipacaoViewUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected CelpeSaudeCoparticipacaoViewUi createUi() {
		return new CelpeSaudeCoparticipacaoViewUi();
	}

	@Override
	protected CelpeSaudeCoparticipacaoViewEntity createEntity() {
		return new CelpeSaudeCoparticipacaoViewEntity();
	}

	@Override
	protected AbstractDao<CelpeSaudeCoparticipacaoViewEntity> getDao() {
		return celpeSaudeCoparticipacaoDao;
	}

	@Override
	protected AbstractMapper<CelpeSaudeCoparticipacaoView, CelpeSaudeCoparticipacaoViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<CelpeSaudeCoparticipacaoView, CelpeSaudeCoparticipacaoViewEntity> getEntityMapper() {
		return entityMapper;
	}
}
