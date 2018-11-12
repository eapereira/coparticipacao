package br.com.spread.qualicorp.wso2.coparticipacao.service.impl.view.bradesco;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco.AutomindCoparticipacaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.AutomindCoparticipacaoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.AutomindCoparticipacaoViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.AutomindCoparticipacaoViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.AutomindCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.AutomindCoparticipacaoView;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.AutomindCoparticipacaoService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class AutomindCoparticipacaoServiceImpl extends
		AbstractServiceImpl<AutomindCoparticipacaoViewUi, AutomindCoparticipacaoViewEntity, AutomindCoparticipacaoView>
		implements AutomindCoparticipacaoService {

	private static final Logger LOGGER = LogManager.getLogger(AutomindCoparticipacaoServiceImpl.class);

	@Autowired
	private AutomindCoparticipacaoDao automindCoparticipacaoDao;

	@Autowired
	private AutomindCoparticipacaoViewUiMapper uiMapper;

	@Autowired
	private AutomindCoparticipacaoViewEntityMapper entityMapper;

	public List<AutomindCoparticipacaoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<AutomindCoparticipacaoViewUi> automindCoparticipacaoViewUis;

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
	protected AutomindCoparticipacaoViewUi createUi() {
		return new AutomindCoparticipacaoViewUi();
	}

	@Override
	protected AutomindCoparticipacaoViewEntity createEntity() {
		return new AutomindCoparticipacaoViewEntity();
	}

	@Override
	protected AbstractDao<AutomindCoparticipacaoViewEntity> getDao() {
		return automindCoparticipacaoDao;
	}

	@Override
	protected AbstractMapper<AutomindCoparticipacaoView, AutomindCoparticipacaoViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<AutomindCoparticipacaoView, AutomindCoparticipacaoViewEntity> getEntityMapper() {
		return entityMapper;
	}
}
