package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraOperationDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraOperationEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraOperationEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraOperationUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraFieldUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraOperationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraValorUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraFieldService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraOperationService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraValorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraOperationServiceImpl extends
		AbstractServiceImpl<RegraOperationUi, RegraOperationEntity, RegraOperation> implements RegraOperationService {
	private static final Logger LOGGER = LogManager.getLogger(RegraOperationServiceImpl.class);

	@Autowired
	private RegraOperationDao regraOperationDao;

	@Autowired
	private RegraOperationUiMapper uiMapper;

	@Autowired
	private RegraOperationEntityMapper entityMapper;

	@Autowired
	private RegraFieldService regraFieldService;

	@Autowired
	private RegraValorService regraValorService;

	@Override
	protected AbstractDao<RegraOperationEntity> getDao() {
		return regraOperationDao;
	}

	@Override
	protected RegraOperationUi createUi() {
		return new RegraOperationUi();
	}

	@Override
	protected RegraOperationEntity createEntity() {
		return new RegraOperationEntity();
	}

	@Override
	protected AbstractMapper<RegraOperation, RegraOperationUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraOperation, RegraOperationEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<RegraOperationUi> listByRegraId(RegraUi regraUi) throws ServiceException {
		List<RegraOperationUi> regraOperationUis;
		List<RegraFieldUi> regraFieldUis;
		List<RegraValorUi> regraValorUis;

		try {
			LOGGER.info("BEGIN");

			regraOperationUis = entityToUi(regraOperationDao.listByRegraId(regraUi.getId()));

			for (RegraOperationUi regraOperationUi : regraOperationUis) {
				regraFieldUis = regraFieldService.listByRegraOperationId(regraOperationUi);
				regraValorUis = regraValorService.listByRegraOperationId(regraOperationUi);

				regraOperationUi.getRegraFields().addAll(regraFieldUis);
				regraOperationUi.getRegraValors().addAll(regraValorUis);
			}

			LOGGER.info("END");
			return regraOperationUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
