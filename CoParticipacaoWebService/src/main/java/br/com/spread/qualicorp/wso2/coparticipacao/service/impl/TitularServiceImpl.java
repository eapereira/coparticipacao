package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.TitularEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.TitularUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TitularServiceImpl
		extends AbstractServiceImpl<TitularUi, TitularEntity, Titular>
		implements TitularService {

	private static final Logger LOGGER = LogManager
			.getLogger(TitularServiceImpl.class);

	@Autowired
	private TitularDao titularDao;

	@Autowired
	private DependenteService dependenteService;

	@Autowired
	private TitularUiMapper uiMapper;

	@Autowired
	private TitularEntityMapper entityMapper;

	@Override
	protected AbstractDao<TitularEntity> getDao() {
		return titularDao;
	}

	public TitularUi findByCpf(String cpf) throws ServiceException {
		TitularUi titularUi = null;
		DependenteUi dependenteUi;

		try {
			LOGGER.info("BEGIN");
			titularUi = entityToUi(titularDao.findByCpf(cpf));

			if (titularUi == null) {
				dependenteUi = dependenteService.findByCpf(cpf);

				if (dependenteUi != null) {
					titularUi = (TitularUi) dependenteUi.getTitular();
				} else {
					LOGGER.info("Não existe títular para o CPF [{}]:", cpf);
				}
			}

			LOGGER.info("END");
			return titularUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<TitularUi> listByEmpresaId(Long id) throws ServiceException {
		List<TitularUi> titularUis;

		try {
			LOGGER.info("BEGIN");

			titularUis = entityToUi(titularDao.listByEmpresaId(id));

			LOGGER.info("END");
			return titularUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected TitularUi createUi() {
		return new TitularUi();
	}

	@Override
	protected TitularEntity createEntity() {
		return new TitularEntity();
	}

	@Override
	protected AbstractMapper<Titular, TitularUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Titular, TitularEntity> getEntityMapper() {
		return entityMapper;
	}

}
