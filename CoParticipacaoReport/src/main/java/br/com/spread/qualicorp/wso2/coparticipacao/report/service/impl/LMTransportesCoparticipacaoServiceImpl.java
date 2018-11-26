package br.com.spread.qualicorp.wso2.coparticipacao.report.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.LMTransportesCoparticipacaoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.LMTransportesCoparticipacaoViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.LMTransportesCoparticipacaoViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.LMTransportesCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.LMTransportesCoparticipacaoView;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.LMTransportesCoparticipacaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.LMTransportesCoparticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LMTransportesCoparticipacaoServiceImpl extends
		AbstractServiceImpl<LMTransportesCoparticipacaoViewUi, LMTransportesCoparticipacaoViewEntity, LMTransportesCoparticipacaoView>
		implements LMTransportesCoparticipacaoService {

	private static final Logger LOGGER = LogManager.getLogger(LMTransportesCoparticipacaoServiceImpl.class);

	@Autowired
	private LMTransportesCoparticipacaoDao automindCoparticipacaoDao;

	@Autowired
	private LMTransportesCoparticipacaoViewUiMapper uiMapper;

	@Autowired
	private LMTransportesCoparticipacaoViewEntityMapper entityMapper;

	public List<LMTransportesCoparticipacaoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<LMTransportesCoparticipacaoViewUi> automindCoparticipacaoViewUis;

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
	protected LMTransportesCoparticipacaoViewUi createUi() {
		return new LMTransportesCoparticipacaoViewUi();
	}

	@Override
	protected LMTransportesCoparticipacaoViewEntity createEntity() {
		return new LMTransportesCoparticipacaoViewEntity();
	}

	@Override
	protected AbstractDao<LMTransportesCoparticipacaoViewEntity> getDao() {
		return automindCoparticipacaoDao;
	}

	@Override
	protected AbstractMapper<LMTransportesCoparticipacaoView, LMTransportesCoparticipacaoViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<LMTransportesCoparticipacaoView, LMTransportesCoparticipacaoViewEntity> getEntityMapper() {
		return entityMapper;
	}
}
