package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.DependenteJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DependenteDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DependenteEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DependenteUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DependenteServiceImpl extends AbstractServiceImpl<DependenteUi, DependenteEntity, Dependente>
		implements DependenteService {

	private static final Logger LOGGER = LogManager.getLogger(DependenteServiceImpl.class);

	@Autowired
	private DependenteDao dependenteDao;

	@Autowired
	private DependenteUiMapper uiMapper;;

	@Autowired
	private DependenteEntityMapper entityMapper;

	@Autowired
	private DependenteJdbcDao dependenteJdbcDao;

	@Autowired
	private DependenteDetailService dependenteDetailService;

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

	public List<DependenteUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException {
		List<DependenteUi> dependenteUis;

		try {
			LOGGER.info("BEGIN");

			dependenteUis = entityToUi(dependenteDao.listByEmpresaId(empresaUi.getId()));

			LOGGER.info("END");
			return dependenteUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<DependenteUi> listByEmpresaIdOrderByCpfAndName(EmpresaUi empresaUi) throws ServiceException {
		List<DependenteUi> dependenteUis;

		try {
			LOGGER.info("BEGIN");

			dependenteUis = entityToUi(dependenteDao.listByEmpresaIdOrderByCpfAndName(empresaUi.getId()));

			LOGGER.info("END");
			return dependenteUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<DependenteUi> listByEmpresaIdOrderByMatriculaAndName(EmpresaUi empresaUi) throws ServiceException {
		List<DependenteUi> dependenteUis;

		try {
			LOGGER.info("BEGIN");

			dependenteUis = entityToUi(dependenteDao.listByEmpresaIdOrderByMatriculaAndName(empresaUi.getId()));

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

	@Override
	protected AbstractBatchDao<DependenteEntity> getJdbcDao() {
		return dependenteJdbcDao;
	}

	@Override
	protected void logBatchInfo(DependenteUi dependenteUi) throws ServiceException {
		LOGGER.debug("ID_DEPENDENTE:.....................[{}]", dependenteUi.getId());
		LOGGER.debug("NM_DEPENDENTE:.....................[{}]", dependenteUi.getNameDependente());
		LOGGER.debug("NR_MATRICULA:......................[{}]", dependenteUi.getMatricula());
		LOGGER.debug("NR_CPF_DEPENDENTE:.................[{}]", dependenteUi.getCpf());
		LOGGER.debug("DT_NASCIMENTO:.....................[{}]", dependenteUi.getDtNascimento());
		LOGGER.debug("TP_DEPENDENTE:.....................[{}]", dependenteUi.getTpDependente().getDescription());
	}

	@Transactional(propagation = Propagation.REQUIRED, transactionManager = AbstractService.JDBC_TX)
	@Override
	public void saveBatch(List<DependenteUi> dependenteUis) throws ServiceException {
		List<DependenteDetailUi> dependenteDetailUis;

		try {
			LOGGER.info("BEGIN");

			dependenteDetailUis = new ArrayList<DependenteDetailUi>();

			super.saveBatch(dependenteUis);

			for (DependenteUi titularUi : dependenteUis) {
				for (DependenteDetail dependenteDetail : titularUi.getDependenteDetails()) {
					LOGGER.debug(
							"DependenteDetailUi.[{}] with value [{}]",
							dependenteDetail.getArquivoInputColsDef().getNameColumn(),
							dependenteDetailService.getFieldValue((DependenteDetailUi) dependenteDetail));

					dependenteDetailUis.add((DependenteDetailUi) dependenteDetail);

					if (dependenteDetailUis.size() % AbstractBatchDao.BATCH_SIZE == 0) {
						dependenteDetailService.saveBatchBlock(dependenteDetailUis);
						dependenteDetailUis.clear();
					}
				}
			}

			if (!dependenteDetailUis.isEmpty()) {
				dependenteDetailService.saveBatchBlock(dependenteDetailUis);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}
}