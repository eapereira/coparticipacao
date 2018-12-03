package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.CelpeSaudeRateioViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.CelpeSaudeRateioViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.CelpeSaudeRateioViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeRateioViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.CelpeSaudeRateioView;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.CelpeSaudeRateioDao;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.CelpeSaudeRateioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class CelpeSaudeRateioServiceImpl
		extends AbstractServiceImpl<CelpeSaudeRateioViewUi, CelpeSaudeRateioViewEntity, CelpeSaudeRateioView>
		implements CelpeSaudeRateioService {

	private static final Logger LOGGER = LogManager.getLogger(CelpeSaudeRateioServiceImpl.class);

	@Autowired
	private CelpeSaudeRateioViewUiMapper uiMapper;

	@Autowired
	private CelpeSaudeRateioViewEntityMapper entityMapper;

	@Autowired
	private CelpeSaudeRateioDao celpeSaudeRateioDao;

	public List<CelpeSaudeRateioViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<CelpeSaudeRateioViewUi> celpeSaudeRateioViewUis;
		try {
			LOGGER.info("BEGIN");

			celpeSaudeRateioViewUis = entityToUi(celpeSaudeRateioDao.listByMesAndAno(mes, ano));

			LOGGER.info("END");
			return celpeSaudeRateioViewUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected CelpeSaudeRateioViewUi createUi() {
		return new CelpeSaudeRateioViewUi();
	}

	@Override
	protected CelpeSaudeRateioViewEntity createEntity() {
		return new CelpeSaudeRateioViewEntity();
	}

	@Override
	protected AbstractDao<CelpeSaudeRateioViewEntity> getDao() {
		return celpeSaudeRateioDao;
	}

	@Override
	protected AbstractMapper<CelpeSaudeRateioView, CelpeSaudeRateioViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<CelpeSaudeRateioView, CelpeSaudeRateioViewEntity> getEntityMapper() {
		return entityMapper;
	}
}
