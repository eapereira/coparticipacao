package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.TitularEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.TitularUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.AbstractJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.TitularJdbcDao;
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

	@Autowired
	private TitularJdbcDao titularJdbcDao;

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

	@Override
	protected AbstractJdbcDao<TitularEntity> getJdbcDao() {
		return titularJdbcDao;
	}

	@Override
	public void saveBatch(List<TitularUi> uis) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			super.saveBatch(uis);

			for (Titular titular : uis) {
				LOGGER.debug(
						"ID_TITULAR:........................[{}]",
						titular.getId());
				LOGGER.debug(
						"NM_TITULAR:........................[{}]",
						titular.getNameTitular());
				LOGGER.debug(
						"NR_MATRICULA_TITULAR:..............[{}]",
						titular.getMatricula());

				for (Dependente dependente : titular.getDependentes()) {
					LOGGER.debug(
							"NM_DEPENDENTE:.....................[{}]",
							dependente.getNameDependente());
					LOGGER.debug(
							"NR_MATRICULA:......................[{}]",
							dependente.getMatricula());
					LOGGER.debug(
							"NR_CPF_DEPENDENTE:.................[{}]",
							dependente.getCpf());
					LOGGER.debug(
							"DT_NASCIMENTO:.....................[{}]",
							dependente.getDtNascimento());
					LOGGER.debug(
							"TP_DEPENDENTE:.....................[{}]",
							dependente.getTpDependente().getDescription());

					dependenteService.saveBatch((DependenteUi) dependente);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

}
