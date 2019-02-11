package br.com.spread.qualicorp.wso2.coparticipacao.batch.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.DependenteResumoBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DependenteResumoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteResumo;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteResumoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DependenteResumoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DependenteResumoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteResumoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DependenteResumoBatchServiceImpl
		extends AbstractBatchServiceImpl<DependenteResumoUi, DependenteResumoEntity, DependenteResumo>
		implements DependenteResumoBatchService {

	private static final Logger LOGGER = LogManager.getLogger(DependenteResumoBatchServiceImpl.class);

	@Autowired
	private DependenteResumoBatchDao dependenteResumoBatchDao;

	@Autowired
	private DependenteResumoUiMapper uiMapper;

	@Autowired
	private DependenteResumoEntityMapper entityMapper;

	public List<DependenteResumoUi> listByEmpresa(EmpresaUi empresaUi) throws ServiceException {
		List<DependenteResumoUi> dependenteResumoUis;

		try {
			LOGGER.info("BEGIN");

			dependenteResumoUis = entityToUi(dependenteResumoBatchDao.listByEmpresaId(empresaUi.getId()));

			LOGGER.info("END");
			return dependenteResumoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected DependenteResumoUi createUi() {
		return new DependenteResumoUi();
	}

	@Override
	protected DependenteResumoEntity createEntity() {
		return new DependenteResumoEntity();
	}

	@Override
	protected AbstractDao<DependenteResumoEntity> getDao() {
		return null;
	}

	@Override
	protected AbstractMapper<DependenteResumo, DependenteResumoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<DependenteResumo, DependenteResumoEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	protected AbstractBatchDao<DependenteResumoEntity> getJdbcDao() {
		return dependenteResumoBatchDao;
	}

}
