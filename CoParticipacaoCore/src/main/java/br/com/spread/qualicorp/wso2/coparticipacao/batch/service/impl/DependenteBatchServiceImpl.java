package br.com.spread.qualicorp.wso2.coparticipacao.batch.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.DependenteJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DependenteBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DependenteResumoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DependenteEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DependenteUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteResumoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DependenteBatchServiceImpl extends AbstractBatchServiceImpl<DependenteUi, DependenteEntity, Dependente>
		implements DependenteBatchService {

	private static final Logger LOGGER = LogManager.getLogger(DependenteBatchServiceImpl.class);

	@Autowired
	private DependenteUiMapper uiMapper;

	@Autowired
	private DependenteEntityMapper entityMapper;

	@Autowired
	private DependenteJdbcDao dependenteJdbcDao;

	@Autowired
	private DependenteResumoBatchService dependenteResumoBatchService;

	@Override
	protected AbstractDao<DependenteEntity> getDao() {
		return null;
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

	@Override
	protected AbstractBatchDao<DependenteEntity> getJdbcDao() {
		return dependenteJdbcDao;
	}

	@Override
	protected void logBatchInfo(DependenteUi dependenteUi) throws ServiceException {
		TitularUi titularUi = (TitularUi) dependenteUi.getTitular();

		LOGGER.debug("ID_DEPENDENTE:.....................[{}]", dependenteUi.getId());
		LOGGER.debug("NM_DEPENDENTE:.....................[{}]", dependenteUi.getNameDependente());
		LOGGER.debug("NR_MATRICULA:......................[{}]", dependenteUi.getMatricula());
		LOGGER.debug("NR_MATRICULA_EMPRESA:..............[{}]", dependenteUi.getMatriculaEmpresa());
		LOGGER.debug("NR_CPF:............................[{}]", dependenteUi.getCpf());
		LOGGER.debug("DT_NASCIMENTO:.....................[{}]", dependenteUi.getDtNascimento());
		LOGGER.debug("TP_DEPENDENTE:.....................[{}]", dependenteUi.getTpDependente().getDescription());
		LOGGER.debug("ID_TITULAR:........................[{}]", titularUi.getId());
		LOGGER.debug("NM_TITULAR:........................[{}]", titularUi.getNameTitular());
	}

	private void updateDependenteId(CoParticipacaoContext coParticipacaoContext, List<DependenteUi> dependenteUis)
			throws ServiceException {
		List<DependenteResumoUi> dependenteResumoUis;

		try {
			LOGGER.info("BEGIN");

			dependenteResumoUis = dependenteResumoBatchService.listByEmpresa(coParticipacaoContext.getEmpresaUi());

			for (DependenteUi dependenteUi : dependenteUis) {
				for (DependenteResumoUi dependenteResumoUi : dependenteResumoUis) {
					if (dependenteUi.getMatricula().equals(dependenteResumoUi.getMatricula())) {
						if (dependenteUi.getNameDependente().equals(dependenteResumoUi.getNameBeneficiario())) {
							dependenteUi.setId(dependenteResumoUi.getId());
						}
					}
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void saveBatch(CoParticipacaoContext coParticipacaoContext, List<DependenteUi> dependenteUis)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			saveBatch(dependenteUis);
			updateDependenteId(coParticipacaoContext, dependenteUis);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
