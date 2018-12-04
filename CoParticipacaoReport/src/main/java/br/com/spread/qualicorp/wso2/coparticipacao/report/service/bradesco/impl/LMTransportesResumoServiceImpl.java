package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.LMTransportesResumoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.LMTransportesResumoViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.LMTransportesResumoViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.LMTransportesResumoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.LMTransportesResumoView;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco.LMTransportesResumoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.LMTransportesResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LMTransportesResumoServiceImpl
		extends AbstractServiceImpl<LMTransportesResumoViewUi, LMTransportesResumoViewEntity, LMTransportesResumoView>
		implements LMTransportesResumoService {

	private static final Logger LOGGER = LogManager.getLogger(LMTransportesResumoServiceImpl.class);

	@Autowired
	private LMTransportesResumoDao automindResumoDao;

	@Autowired
	private LMTransportesResumoViewUiMapper uiMapper;

	@Autowired
	private LMTransportesResumoViewEntityMapper entityMapper;

	public List<LMTransportesResumoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<LMTransportesResumoViewUi> automindResumoViewUis;

		try {
			LOGGER.info("BEGIN");

			automindResumoViewUis = entityToUi(automindResumoDao.listByMesAndAno(mes, ano));

			LOGGER.info("END");
			return automindResumoViewUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected LMTransportesResumoViewUi createUi() {
		return new LMTransportesResumoViewUi();
	}

	@Override
	protected LMTransportesResumoViewEntity createEntity() {
		return new LMTransportesResumoViewEntity();
	}

	@Override
	protected AbstractDao<LMTransportesResumoViewEntity> getDao() {
		return automindResumoDao;
	}

	@Override
	protected AbstractMapper<LMTransportesResumoView, LMTransportesResumoViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<LMTransportesResumoView, LMTransportesResumoViewEntity> getEntityMapper() {
		return entityMapper;
	}
}
