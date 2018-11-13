package br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco.TechnitOdontoCoparticipacaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.TechnitOdontoCoparticipacaoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.TechnitOdontoCoparticipacaoViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.TechnitOdontoCoparticipacaoViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitOdontoCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.TechnitOdontoCoparticipacaoView;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.TechnitOdontoCoparticipacaoService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TechnitOdontoCoparticipacaoServiceImpl extends
		AbstractServiceImpl<TechnitOdontoCoparticipacaoViewUi, TechnitOdontoCoparticipacaoViewEntity, TechnitOdontoCoparticipacaoView>
		implements TechnitOdontoCoparticipacaoService {

	private static final Logger LOGGER = LogManager.getLogger(TechnitOdontoCoparticipacaoServiceImpl.class);

	@Autowired
	private TechnitOdontoCoparticipacaoDao automindCoparticipacaoDao;

	@Autowired
	private TechnitOdontoCoparticipacaoViewUiMapper uiMapper;

	@Autowired
	private TechnitOdontoCoparticipacaoViewEntityMapper entityMapper;

	public List<TechnitOdontoCoparticipacaoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<TechnitOdontoCoparticipacaoViewUi> automindCoparticipacaoViewUis;

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
	protected TechnitOdontoCoparticipacaoViewUi createUi() {
		return new TechnitOdontoCoparticipacaoViewUi();
	}

	@Override
	protected TechnitOdontoCoparticipacaoViewEntity createEntity() {
		return new TechnitOdontoCoparticipacaoViewEntity();
	}

	@Override
	protected AbstractDao<TechnitOdontoCoparticipacaoViewEntity> getDao() {
		return automindCoparticipacaoDao;
	}

	@Override
	protected AbstractMapper<TechnitOdontoCoparticipacaoView, TechnitOdontoCoparticipacaoViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<TechnitOdontoCoparticipacaoView, TechnitOdontoCoparticipacaoViewEntity> getEntityMapper() {
		return entityMapper;
	}
}
