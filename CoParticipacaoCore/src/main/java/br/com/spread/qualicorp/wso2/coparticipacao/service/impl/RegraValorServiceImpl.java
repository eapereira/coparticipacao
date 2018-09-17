package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraValorDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraValor;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraValorEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraValorEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraValorUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraOperationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraValorUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraValorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraValorServiceImpl extends AbstractServiceImpl<RegraValorUi, RegraValorEntity, RegraValor>
		implements RegraValorService {
	private static final Logger LOGGER = LogManager.getLogger(RegraValorServiceImpl.class);

	@Autowired
	private RegraValorDao regraValorDao;

	@Autowired
	private RegraValorUiMapper uiMapper;

	@Autowired
	private RegraValorEntityMapper entityMapper;

	@Override
	protected AbstractDao<RegraValorEntity> getDao() {
		return regraValorDao;
	}

	@Override
	protected RegraValorUi createUi() {
		return new RegraValorUi();
	}

	@Override
	protected RegraValorEntity createEntity() {
		return new RegraValorEntity();
	}

	@Override
	protected AbstractMapper<RegraValor, RegraValorUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraValor, RegraValorEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<RegraValorUi> listByRegraOperationId(RegraOperationUi regraOperationUi) throws ServiceException {
		List<RegraValorUi> regraValorUis;

		try {
			LOGGER.info("BEGIN");

			regraValorUis = entityToUi(regraValorDao.listByRegraOperationId(regraOperationUi.getId()));

			LOGGER.info("END");
			return regraValorUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
