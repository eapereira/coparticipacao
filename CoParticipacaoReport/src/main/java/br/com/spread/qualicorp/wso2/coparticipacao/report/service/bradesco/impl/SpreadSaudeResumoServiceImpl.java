package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.SpreadSaudeResumoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.SpreadSaudeResumoViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.SpreadSaudeResumoViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.SpreadSaudeResumoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.SpreadSaudeResumoView;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco.SpreadSaudeResumoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.SpreadSaude;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.SpreadSaudeResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class SpreadSaudeResumoServiceImpl
		extends AbstractServiceImpl<SpreadSaudeResumoViewUi, SpreadSaudeResumoViewEntity, SpreadSaudeResumoView>
		implements SpreadSaudeResumoService {

	private static final Logger LOGGER = LogManager.getLogger(SpreadSaudeResumoServiceImpl.class);

	@Autowired
	private SpreadSaudeResumoViewUiMapper uiMapper;

	@Autowired
	private SpreadSaudeResumoViewEntityMapper entityMapper;

	@Autowired
	private SpreadSaudeResumoDao spreadSaudeResumoDao;

	@Override
	public List<SpreadSaudeResumoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<SpreadSaudeResumoViewUi> spreadSaudeResumoViewUis;

		try {
			LOGGER.info("BEGIN");

			spreadSaudeResumoViewUis = entityToUi(spreadSaudeResumoDao.listByMesAndAno(mes, ano));

			LOGGER.info("END");
			return spreadSaudeResumoViewUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected SpreadSaudeResumoViewUi createUi() {
		return new SpreadSaudeResumoViewUi();
	}

	@Override
	protected SpreadSaudeResumoViewEntity createEntity() {
		return new SpreadSaudeResumoViewEntity();
	}

	@Override
	protected AbstractDao<SpreadSaudeResumoViewEntity> getDao() {
		return spreadSaudeResumoDao;
	}

	@Override
	protected AbstractMapper<SpreadSaudeResumoView, SpreadSaudeResumoViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<SpreadSaudeResumoView, SpreadSaudeResumoViewEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	public List<SpreadSaudeResumoViewUi> listByMesAndAnoAndInativos(Integer mes, Integer ano) throws ServiceException {
		List<SpreadSaudeResumoViewUi> spreadSaudeResumoViewUis;

		try {
			LOGGER.info("BEGIN");

			spreadSaudeResumoViewUis = entityToUi(
					spreadSaudeResumoDao.listByMesAndAnoAndSubFaturas(mes, ano, SpreadSaude.getInativos()));

			LOGGER.info("END");
			return spreadSaudeResumoViewUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SpreadSaudeResumoViewUi> listByMesAndAnoAndAtivos(Integer mes, Integer ano) throws ServiceException {
		List<SpreadSaudeResumoViewUi> spreadSaudeResumoViewUis;

		try {
			LOGGER.info("BEGIN");

			spreadSaudeResumoViewUis = entityToUi(
					spreadSaudeResumoDao.listByMesAndAnoAndSubFaturas(mes, ano, SpreadSaude.getAtivos()));

			LOGGER.info("END");
			return spreadSaudeResumoViewUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
