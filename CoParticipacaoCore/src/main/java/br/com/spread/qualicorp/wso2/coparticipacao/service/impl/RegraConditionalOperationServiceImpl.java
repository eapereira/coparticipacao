package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalOperationDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalOperationEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraConditionalOperationEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraConditionalOperationUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalFieldUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalOperationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalValorUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalFieldService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalOperationService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalValorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraConditionalOperationServiceImpl extends
		AbstractServiceImpl<RegraConditionalOperationUi, RegraConditionalOperationEntity, RegraConditionalOperation>
		implements RegraConditionalOperationService {

	private static final Logger LOGGER = LogManager.getLogger(RegraConditionalOperationServiceImpl.class);

	@Autowired
	private RegraConditionalOperationDao regraConditionalOperationDao;

	@Autowired
	private RegraConditionalOperationUiMapper uiMapper;

	@Autowired
	private RegraConditionalOperationEntityMapper entityMapper;

	@Autowired
	private RegraConditionalFieldService regraConditionalFieldService;

	@Autowired
	private RegraConditionalValorService regraConditionalValorService;

	public RegraConditionalOperationServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected RegraConditionalOperationUi createUi() {
		return new RegraConditionalOperationUi();
	}

	@Override
	protected RegraConditionalOperationEntity createEntity() {
		return new RegraConditionalOperationEntity();
	}

	@Override
	protected AbstractDao<RegraConditionalOperationEntity> getDao() {
		return regraConditionalOperationDao;
	}

	@Override
	protected AbstractMapper<RegraConditionalOperation, RegraConditionalOperationUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraConditionalOperation, RegraConditionalOperationEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<RegraConditionalOperationUi> listByRegraConditional(RegraConditionalUi regraConditionalUi)
			throws ServiceException {
		List<RegraConditionalOperationUi> regraConditionalOperationUis;
		List<RegraConditionalFieldUi> regraConditionalFieldUis;
		List<RegraConditionalValorUi> regraConditionalValorUis;

		try {
			LOGGER.info("BEGIN");

			regraConditionalOperationUis = entityToUi(
					regraConditionalOperationDao.listByRegraConditionalId(regraConditionalUi.getId()));

			for (RegraConditionalOperationUi regraConditionalOperationUi : regraConditionalOperationUis) {
				regraConditionalFieldUis = regraConditionalFieldService
						.listByRegraConditionalOperation(regraConditionalOperationUi);
				regraConditionalValorUis = regraConditionalValorService
						.listByRegraConditionalOperation(regraConditionalOperationUi);

				regraConditionalOperationUi.getRegraConditionalFields().addAll(regraConditionalFieldUis);
				regraConditionalOperationUi.getRegraConditionalValors().addAll(regraConditionalValorUis);
			}

			LOGGER.info("END");
			return regraConditionalOperationUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
