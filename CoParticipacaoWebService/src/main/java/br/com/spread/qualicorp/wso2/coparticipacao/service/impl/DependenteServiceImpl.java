package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DependenteDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DependenteEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DependenteUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DependenteServiceImpl
		extends AbstractServiceImpl<DependenteUi, DependenteEntity, Dependente>
		implements DependenteService {

	private static final Logger LOGGER = LogManager
			.getLogger(DependenteServiceImpl.class);

	@Autowired
	private DependenteDao dependenteDao;

	@Autowired
	private DependenteUiMapper uiMapper;;

	@Autowired
	private DependenteEntityMapper entityMapper;

	@Override
	protected AbstractDao<DependenteEntity> getDao() {
		return dependenteDao;
	}

	public DependenteUi findByCpf(String cpf) throws ServiceException {
		DependenteUi dependenteUi;

		try {
			LOGGER.info("BEGIN");
			dependenteUi = entityToUi(dependenteDao.findByCpf(cpf));

			LOGGER.info("END");
			return dependenteUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<DependenteUi> listByEmpresaId(Long id) throws ServiceException {
		List<DependenteUi> dependenteUis;

		try {
			LOGGER.info("BEGIN");
			dependenteUis = entityToUi(dependenteDao.listByEmpresaId(id));

			LOGGER.info("END");
			return dependenteUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected DependenteUi createUi() {
		return new DependenteUi();
	}

	@Override
	protected DependenteEntity createEntity() {
		return new DependenteEntity();
	}

	@Override
	protected AbstractMapper<Dependente, DependenteUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Dependente, DependenteEntity> getEntityMapper() {
		return entityMapper;
	}

}
