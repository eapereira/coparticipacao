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
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.TitularJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.TitularEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.TitularUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TitularServiceImpl extends AbstractServiceImpl<TitularUi, TitularEntity, Titular>
		implements TitularService {

	private static final Logger LOGGER = LogManager.getLogger(TitularServiceImpl.class);

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

	@Autowired
	private TitularDetailService titularDetailService;

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

	public List<TitularUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException {
		List<TitularUi> titularUis;

		try {
			LOGGER.info("BEGIN");

			titularUis = entityToUi(titularDao.listByEmpresaId(empresaUi.getId()));

			LOGGER.info("END");
			return titularUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<TitularUi> listByEmpresaIdOrderByCpfAndName(EmpresaUi empresaUi) throws ServiceException {
		List<TitularUi> titularUis;

		try {
			LOGGER.info("BEGIN");

			titularUis = entityToUi(titularDao.listByEmpresaIdOrderByCpfAndName(empresaUi.getId()));

			LOGGER.info("END");
			return titularUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<TitularUi> listByEmpresaIdOrderByMatriculaAndName(EmpresaUi empresaUi) throws ServiceException {
		List<TitularUi> titularUis;

		try {
			LOGGER.info("BEGIN");

			titularUis = entityToUi(titularDao.listByEmpresaIdOrderByMatriculaAndName(empresaUi.getId()));

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
	protected AbstractBatchDao<TitularEntity> getJdbcDao() {
		return titularJdbcDao;
	}

	@Override
	protected void logBatchInfo(TitularUi titularUi) throws ServiceException {
		LOGGER.debug("ID_TITULAR:........................[{}]", titularUi.getId());
		LOGGER.debug("NM_TITULAR:........................[{}]", titularUi.getNameTitular());
		LOGGER.debug("NR_CPF:............................[{}]", titularUi.getCpf());
		LOGGER.debug("NR_MATRICULA:......................[{}]", titularUi.getMatricula());
		LOGGER.debug("NR_MATRICULA_EMPRESA:..............[{}]", titularUi.getMatriculaEmpresa());
		LOGGER.debug("DT_NASCIMENTO:.....................[{}]", titularUi.getDtNascimento());
		LOGGER.debug("DT_ADMISSAO:.......................[{}]", titularUi.getDtAdmissao());
	}

	@Transactional(propagation = Propagation.REQUIRED, transactionManager = AbstractService.JDBC_TX)
	@Override
	public void saveBatch(List<TitularUi> titularUis) throws ServiceException {
		List<TitularDetailUi> titularDetailUis;

		try {
			LOGGER.info("BEGIN");

			titularDetailUis = new ArrayList<TitularDetailUi>();

			super.saveBatch(titularUis);

			for (TitularUi titularUi : titularUis) {
				logBatchInfo(titularUi);

				for (TitularDetail titularDetail : titularUi.getTitularDetails()) {
					LOGGER.debug(
							"TitularDetailUi.[{}] with value [{}]",
							titularDetail.getArquivoInputColsDef().getNameColumn(),
							titularDetailService.getFieldValue((TitularDetailUi) titularDetail));

					titularDetailUis.add((TitularDetailUi) titularDetail);

					if (titularDetailUis.size() % AbstractBatchDao.BATCH_SIZE == 0) {
						titularDetailService.saveBatchBlock(titularDetailUis);
						titularDetailUis.clear();
					}
				}
			}

			if (!titularDetailUis.isEmpty()) {
				titularDetailService.saveBatchBlock(titularDetailUis);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}
}
