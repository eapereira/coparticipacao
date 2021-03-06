package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalResultDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.OperationType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalResult;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalResultEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraConditionalResultEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraConditionalResultUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalResultUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalResultService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraConditionalResultServiceImpl
		extends AbstractServiceImpl<RegraConditionalResultUi, RegraConditionalResultEntity, RegraConditionalResult>
		implements RegraConditionalResultService {

	private static final Logger LOGGER = LogManager.getLogger(RegraConditionalResultServiceImpl.class);

	@Autowired
	private RegraConditionalResultDao regraConditionalResultDao;

	@Autowired
	private RegraConditionalResultUiMapper uiMapper;

	@Autowired
	private RegraConditionalResultEntityMapper entityMapper;

	public RegraConditionalResultServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected RegraConditionalResultUi createUi() {
		return new RegraConditionalResultUi();
	}

	@Override
	protected RegraConditionalResultEntity createEntity() {
		return new RegraConditionalResultEntity();
	}

	@Override
	protected AbstractDao<RegraConditionalResultEntity> getDao() {
		return regraConditionalResultDao;
	}

	@Override
	protected AbstractMapper<RegraConditionalResult, RegraConditionalResultUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraConditionalResult, RegraConditionalResultEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<RegraConditionalResultUi> listByRegraConditional(RegraConditionalUi regraConditionalUi)
			throws ServiceException {
		List<RegraConditionalResultUi> regraConditionalResultUis;

		try {
			LOGGER.info("BEGIN");

			regraConditionalResultUis = entityToUi(
					regraConditionalResultDao.listByRegraConditionalId(regraConditionalUi.getId()));

			LOGGER.info("END");
			return regraConditionalResultUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
