package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraFieldDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraFieldEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraFieldEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraFieldUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraFieldUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraOperationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraFieldService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraFieldServiceImpl extends AbstractServiceImpl<RegraFieldUi, RegraFieldEntity, RegraField>
		implements RegraFieldService {

	private static final Logger LOGGER = LogManager.getLogger(RegraFieldServiceImpl.class);

	@Autowired
	private RegraFieldDao regraFieldDao;

	@Autowired
	private RegraFieldUiMapper uiMapper;

	@Autowired
	private RegraFieldEntityMapper entityMapper;

	@Override
	protected AbstractDao<RegraFieldEntity> getDao() {
		return regraFieldDao;
	}

	@Override
	protected RegraFieldUi createUi() {
		return new RegraFieldUi();
	}

	@Override
	protected RegraFieldEntity createEntity() {
		return new RegraFieldEntity();
	}

	@Override
	protected AbstractMapper<RegraField, RegraFieldUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraField, RegraFieldEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<RegraFieldUi> listByRegraOperationId(RegraOperationUi regraOperationUi) throws ServiceException {
		List<RegraFieldUi> regraFieldUis;

		try {
			LOGGER.info("BEGIN");

			regraFieldUis = entityToUi(regraFieldDao.listByRegraOperationId(regraOperationUi.getId()));

			LOGGER.info("END");
			return regraFieldUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
