package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DependenteResumoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteResumo;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteResumoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DependenteResumoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DependenteResumoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteResumoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DependenteResumoServiceImpl
		extends AbstractServiceImpl<DependenteResumoUi, DependenteResumoEntity, DependenteResumo>
		implements DependenteResumoService {

	private static final Logger LOGGER = LogManager.getLogger(DependenteResumoServiceImpl.class);

	@Autowired
	private DependenteResumoUiMapper uiMapper;

	@Autowired
	private DependenteResumoEntityMapper entityMapper;

	@Autowired
	private DependenteResumoDao dependenteResumoDao;

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
		return dependenteResumoDao;
	}

	@Override
	protected AbstractMapper<DependenteResumo, DependenteResumoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<DependenteResumo, DependenteResumoEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<DependenteResumoUi> listByEempresa(EmpresaUi empresaUi) throws ServiceException {
		List<DependenteResumoUi> dependenteResumoUis;

		try {
			LOGGER.info("BEGIN");

			dependenteResumoUis = entityToUi(dependenteResumoDao.listByEmpresaId(empresaUi.getId()));

			LOGGER.info("END");
			return dependenteResumoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
