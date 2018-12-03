package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.CelpeSaudeResumoDetailViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.CelpeSaudeResumoDetailViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.CelpeSaudeResumoDetailViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeResumoDetailViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.CelpeSaudeResumoDetailView;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.CelpeSaudeResumoDetailDao;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.CelpeSaudeResumoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class CelpeSaudeResumoDetailServiceImpl extends
		AbstractServiceImpl<CelpeSaudeResumoDetailViewUi, CelpeSaudeResumoDetailViewEntity, CelpeSaudeResumoDetailView>
		implements CelpeSaudeResumoDetailService {

	private static final Logger LOGGER = LogManager.getLogger(CelpeSaudeResumoDetailServiceImpl.class);

	@Autowired
	private CelpeSaudeResumoDetailViewUiMapper uiMapper;

	@Autowired
	private CelpeSaudeResumoDetailViewEntityMapper entityMapper;

	@Autowired
	private CelpeSaudeResumoDetailDao celpeSaudeResumoDao;

	public List<CelpeSaudeResumoDetailViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<CelpeSaudeResumoDetailViewUi> celpeSaudeResumoViewUis;
		try {
			LOGGER.info("BEGIN");

			celpeSaudeResumoViewUis = entityToUi(celpeSaudeResumoDao.listByMesAndAno(mes, ano));

			LOGGER.info("END");
			return celpeSaudeResumoViewUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected CelpeSaudeResumoDetailViewUi createUi() {
		return new CelpeSaudeResumoDetailViewUi();
	}

	@Override
	protected CelpeSaudeResumoDetailViewEntity createEntity() {
		return new CelpeSaudeResumoDetailViewEntity();
	}

	@Override
	protected AbstractDao<CelpeSaudeResumoDetailViewEntity> getDao() {
		return celpeSaudeResumoDao;
	}

	@Override
	protected AbstractMapper<CelpeSaudeResumoDetailView, CelpeSaudeResumoDetailViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<CelpeSaudeResumoDetailView, CelpeSaudeResumoDetailViewEntity> getEntityMapper() {
		return entityMapper;
	}
}
