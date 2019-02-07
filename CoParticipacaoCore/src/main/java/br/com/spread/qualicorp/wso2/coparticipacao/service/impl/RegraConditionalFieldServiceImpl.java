package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalFieldDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalFieldEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraConditionalFieldEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraConditionalFieldUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalFieldUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalOperationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalFieldService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraConditionalFieldServiceImpl
		extends AbstractServiceImpl<RegraConditionalFieldUi, RegraConditionalFieldEntity, RegraConditionalField>
		implements RegraConditionalFieldService {

	private static final Logger LOGGER = LogManager.getLogger(RegraConditionalFieldServiceImpl.class);

	@Autowired
	private RegraConditionalFieldDao regraConditionalFieldDao;

	@Autowired
	private RegraConditionalFieldEntityMapper entityMapper;

	@Autowired
	private RegraConditionalFieldUiMapper uiMapper;

	public RegraConditionalFieldServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected RegraConditionalFieldUi createUi() {
		return new RegraConditionalFieldUi();
	}

	@Override
	protected RegraConditionalFieldEntity createEntity() {
		return new RegraConditionalFieldEntity();
	}

	@Override
	protected AbstractDao<RegraConditionalFieldEntity> getDao() {
		return regraConditionalFieldDao;
	}

	@Override
	protected AbstractMapper<RegraConditionalField, RegraConditionalFieldUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraConditionalField, RegraConditionalFieldEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<RegraConditionalFieldUi> listByRegraConditionalOperation(
			RegraConditionalOperationUi regraConditionalOperationUi) throws ServiceException {
		List<RegraConditionalFieldUi> regraConditionalFieldUis;

		try {
			LOGGER.info("BEGIN");

			regraConditionalFieldUis = entityToUi(
					regraConditionalFieldDao.listByRegraConditionalOperationId(regraConditionalOperationUi.getId()));

			LOGGER.info("END");
			return regraConditionalFieldUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
