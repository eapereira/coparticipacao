package br.com.spread.qualicorp.wso2.coparticipacao.batch.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.TitularResumoBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.TitularResumoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularResumo;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularResumoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.TitularResumoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.TitularResumoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularResumoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TitularResumoBatchServiceImpl
		extends AbstractBatchServiceImpl<TitularResumoUi, TitularResumoEntity, TitularResumo>
		implements TitularResumoBatchService {

	private static final Logger LOGGER = LogManager.getLogger(TitularResumoBatchServiceImpl.class);

	@Autowired
	private TitularResumoBatchDao titularResumoBatchDao;

	@Autowired
	private TitularResumoUiMapper uiMapper;

	@Autowired
	private TitularResumoEntityMapper entityMapper;

	public List<TitularResumoUi> listByEmpresa(EmpresaUi empresaUi) throws ServiceException {
		List<TitularResumoUi> titularResumoUis;

		try {
			LOGGER.info("BEGIN");

			titularResumoUis = entityToUi(titularResumoBatchDao.listByEmpresaId(empresaUi.getId()));

			LOGGER.info("END");
			return titularResumoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected TitularResumoUi createUi() {
		return new TitularResumoUi();
	}

	@Override
	protected TitularResumoEntity createEntity() {
		return new TitularResumoEntity();
	}

	@Override
	protected AbstractDao<TitularResumoEntity> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractMapper<TitularResumo, TitularResumoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<TitularResumo, TitularResumoEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	protected AbstractBatchDao<TitularResumoEntity> getJdbcDao() {
		return titularResumoBatchDao;
	}
}
