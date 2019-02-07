package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraResultDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraResult;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraResultEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraResultEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraResultUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraResultUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraResultService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraResultServiceImpl extends AbstractServiceImpl<RegraResultUi, RegraResultEntity, RegraResult>
		implements RegraResultService {

	private static final Logger LOGGER = LogManager.getLogger(RegraResultServiceImpl.class);

	@Autowired
	private RegraResultDao regraResultDao;

	@Autowired
	private RegraResultEntityMapper entityMapper;

	@Autowired
	private RegraResultUiMapper uiMapper;

	@Override
	protected RegraResultUi createUi() {
		return new RegraResultUi();
	}

	@Override
	protected RegraResultEntity createEntity() {
		return new RegraResultEntity();
	}

	@Override
	protected AbstractDao<RegraResultEntity> getDao() {
		return regraResultDao;
	}

	@Override
	protected AbstractMapper<RegraResult, RegraResultUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraResult, RegraResultEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<RegraResultUi> listByRegraId(RegraUi regraUi) throws ServiceException {
		List<RegraResultUi> regraResultUis;

		try {
			LOGGER.info("BEGIN");

			regraResultUis = entityToUi(regraResultDao.listByRegraId(regraUi.getId()));

			LOGGER.info("END");
			return regraResultUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
