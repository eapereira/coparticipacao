package br.com.spread.qualicorp.wso2.coparticipacao.batch.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.TitularJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.TitularBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.TitularResumoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.TitularEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.TitularUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularResumoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TitularBatchServiceImpl extends AbstractBatchServiceImpl<TitularUi, TitularEntity, Titular>
		implements TitularBatchService {

	private static final Logger LOGGER = LogManager.getLogger(TitularBatchServiceImpl.class);

	@Autowired
	private TitularJdbcDao titularJdbcDao;

	@Autowired
	private TitularUiMapper uiMapper;

	@Autowired
	private TitularEntityMapper entityMapper;

	@Autowired
	private TitularResumoBatchService titularResumoBatchService;

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
		LOGGER.debug("DT_DEMISSAO:.......................[{}]", titularUi.getDtDemissao());
		LOGGER.debug("USER_CREATED:......................[{}]", titularUi.getUserCreated().getNameLogin());

		if (titularUi.getUserAltered() != null) {
			LOGGER.debug("USER_ALTERED:......................[{}]", titularUi.getUserAltered().getNameLogin());
		}
	}

	@Override
	protected AbstractDao<TitularEntity> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveBatch(CoParticipacaoContext coParticipacaoContext, List<TitularUi> titularUis)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			saveBatch(titularUis);
			updateTitularId(coParticipacaoContext, titularUis);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void updateTitularId(CoParticipacaoContext coParticipacaoContext, List<TitularUi> titularUis)
			throws ServiceException {
		List<TitularResumoUi> titularResumoUis;

		try {
			LOGGER.info("BEGIN");

			titularResumoUis = titularResumoBatchService.listByEmpresa(coParticipacaoContext.getEmpresaUi());

			for (TitularUi titularUi : titularUis) {
				for (TitularResumoUi titularResumoUi : titularResumoUis) {
					if (titularUi.getCpf().equals(titularResumoUi.getCpf())) {
						titularUi.setId(titularResumoUi.getId());
					}
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
