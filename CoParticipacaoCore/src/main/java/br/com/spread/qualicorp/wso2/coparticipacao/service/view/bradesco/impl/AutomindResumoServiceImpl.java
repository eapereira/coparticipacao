package br.com.spread.qualicorp.wso2.coparticipacao.service.impl.view.bradesco;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco.AutomindResumoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.AutomindResumoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.AutomindResumoViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.AutomindResumoViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.AutomindResumoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.AutomindResumoView;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco.AutomindResumoService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class AutomindResumoServiceImpl
		extends AbstractServiceImpl<AutomindResumoViewUi, AutomindResumoViewEntity, AutomindResumoView>
		implements AutomindResumoService {

	private static final Logger LOGGER = LogManager.getLogger(AutomindResumoServiceImpl.class);

	@Autowired
	private AutomindResumoDao automindResumoDao;

	@Autowired
	private AutomindResumoViewUiMapper uiMapper;

	@Autowired
	private AutomindResumoViewEntityMapper entityMapper;

	public List<AutomindResumoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<AutomindResumoViewUi> automindResumoViewUis;

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
	protected AutomindResumoViewUi createUi() {
		return new AutomindResumoViewUi();
	}

	@Override
	protected AutomindResumoViewEntity createEntity() {
		return new AutomindResumoViewEntity();
	}

	@Override
	protected AbstractDao<AutomindResumoViewEntity> getDao() {
		return automindResumoDao;
	}

	@Override
	protected AbstractMapper<AutomindResumoView, AutomindResumoViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<AutomindResumoView, AutomindResumoViewEntity> getEntityMapper() {
		return entityMapper;
	}
}
