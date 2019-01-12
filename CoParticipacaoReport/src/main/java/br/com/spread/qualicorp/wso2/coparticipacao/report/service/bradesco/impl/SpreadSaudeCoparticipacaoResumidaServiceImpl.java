package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.SpreadSaudeCoparticipacaoResumidaViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.SpreadSaudeCoparticipacaoResumidaViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.SpreadSaudeCoparticipacaoResumidaViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.SpreadSaudeCoparticipacaoResumidaViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.SpreadSaudeCoparticipacaoResumidaView;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco.SpreadSaudeCoparticipacaoResumidaDao;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.SpreadSaude;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.SpreadSaudeCoparticipacaoResumidaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class SpreadSaudeCoparticipacaoResumidaServiceImpl extends
		AbstractServiceImpl<SpreadSaudeCoparticipacaoResumidaViewUi, SpreadSaudeCoparticipacaoResumidaViewEntity, SpreadSaudeCoparticipacaoResumidaView>
		implements SpreadSaudeCoparticipacaoResumidaService {

	private static final Logger LOGGER = LogManager.getLogger(SpreadSaudeCoparticipacaoResumidaServiceImpl.class);

	@Autowired
	private SpreadSaudeCoparticipacaoResumidaViewUiMapper uiMapper;

	@Autowired
	private SpreadSaudeCoparticipacaoResumidaViewEntityMapper entityMapper;

	@Autowired
	private SpreadSaudeCoparticipacaoResumidaDao spreadSaudeCoparticipacaoResumidaDao;

	@Override
	public List<SpreadSaudeCoparticipacaoResumidaViewUi> listByMesAndAno(Integer mes, Integer ano)
			throws ServiceException {
		List<SpreadSaudeCoparticipacaoResumidaViewUi> spreadSaudeCoparticipacaoResumidaViewUis;

		try {
			LOGGER.info("BEGIN");

			spreadSaudeCoparticipacaoResumidaViewUis = entityToUi(
					spreadSaudeCoparticipacaoResumidaDao
							.listByMesAndAno(mes, ano, SpreadSaude.getAtivos(), SpreadSaude.getInativos()));

			LOGGER.info("END");
			return spreadSaudeCoparticipacaoResumidaViewUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected SpreadSaudeCoparticipacaoResumidaViewUi createUi() {
		return new SpreadSaudeCoparticipacaoResumidaViewUi();
	}

	@Override
	protected SpreadSaudeCoparticipacaoResumidaViewEntity createEntity() {
		return new SpreadSaudeCoparticipacaoResumidaViewEntity();
	}

	@Override
	protected AbstractDao<SpreadSaudeCoparticipacaoResumidaViewEntity> getDao() {
		return spreadSaudeCoparticipacaoResumidaDao;
	}

	@Override
	protected AbstractMapper<SpreadSaudeCoparticipacaoResumidaView, SpreadSaudeCoparticipacaoResumidaViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<SpreadSaudeCoparticipacaoResumidaView, SpreadSaudeCoparticipacaoResumidaViewEntity> getEntityMapper() {
		return entityMapper;
	}

}
