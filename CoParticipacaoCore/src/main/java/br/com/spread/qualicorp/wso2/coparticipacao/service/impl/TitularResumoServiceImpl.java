package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularResumoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularResumo;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularResumoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.TitularResumoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.TitularResumoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularResumoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularResumoService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TitularResumoServiceImpl extends AbstractServiceImpl<TitularResumoUi, TitularResumoEntity, TitularResumo>
		implements TitularResumoService {

	private static final Logger LOGGER = LogManager.getLogger(TitularResumoServiceImpl.class);

	@Autowired
	private TitularResumoUiMapper uiMapper;

	@Autowired
	private TitularResumoEntityMapper entityMapper;

	@Autowired
	private TitularResumoDao titularResumoDao;

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
		return titularResumoDao;
	}

	@Override
	protected AbstractMapper<TitularResumo, TitularResumoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<TitularResumo, TitularResumoEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<TitularResumoUi> listByEmpresa(EmpresaUi empresaUi) throws ServiceException {
		List<TitularResumoUi> titularResumoUis;

		try {
			LOGGER.info("BEGIN");

			titularResumoUis = entityToUi(titularResumoDao.listByEmpresaId(empresaUi.getId()));

			LOGGER.info("END");
			return titularResumoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}
}
