package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.LancamentoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.LancamentoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.AbstractJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.LancamentoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.LancamentoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.LancamentoDetailBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LancamentoBatchServiceImpl extends AbstractBatchServiceImpl<LancamentoUi, LancamentoEntity, Lancamento>
		implements LancamentoBatchService {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoBatchServiceImpl.class);

	@Autowired
	private LancamentoUiMapper uiMapper;

	@Autowired
	private LancamentoEntityMapper entityMapper;

	@Autowired
	private LancamentoJdbcDao lancamentoJdbcDao;

	@Autowired
	private LancamentoDetailBatchService lancamentoDetailBatchService;

	@Override
	protected LancamentoUi createUi() {
		return new LancamentoUi();
	}

	@Override
	protected LancamentoEntity createEntity() {
		return new LancamentoEntity();
	}

	@Override
	protected AbstractDao<LancamentoEntity> getDao() {
		return null;
	}

	@Override
	protected AbstractMapper<Lancamento, LancamentoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Lancamento, LancamentoEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	protected AbstractJdbcDao<LancamentoEntity> getJdbcDao() {
		return lancamentoJdbcDao;
	}

	@Override
	protected void logBatchInfo(LancamentoUi lancamentoUi) throws ServiceException {
		TitularUi titularUi = (TitularUi) lancamentoUi.getTitular();
		DependenteUi dependenteUi = (DependenteUi) lancamentoUi.getDependente();

		LOGGER.debug("ID ............................... [{}]:", lancamentoUi.getId());
		LOGGER.debug("CD_CONTRATO ...................... [{}]:", lancamentoUi.getContrato().getCdContrato());
		LOGGER.debug("CD_MES ........................... [{}]:", lancamentoUi.getMes());
		LOGGER.debug("CD_ANO ........................... [{}]:", lancamentoUi.getAno());
		LOGGER.debug("ID_TITULAR ....................... [{}]:", titularUi.getId());
		LOGGER.debug("NM_TITULAR ....................... [{}]:", titularUi.getNameTitular());
		LOGGER.debug("CPF_TITULAR ...................... [{}]:", titularUi.getCpf());
		LOGGER.debug("MATRICULA_TITULAR ................ [{}]:", titularUi.getMatricula());
		LOGGER.debug("DT_NASC_TITULAR .................. [{}]:", titularUi.getDtNascimento());
		LOGGER.debug("DT_NASC_ADMISSAO ................. [{}]:", titularUi.getDtAdmissao());
		LOGGER.debug("VL_PRINCIPAL ..................... [{}]:", lancamentoUi.getValorPrincipal());

		if (dependenteUi != null) {
			LOGGER.debug("ID_DEPENDENTE .................... [{}]:", dependenteUi.getId());
			LOGGER.debug("NM_DEPENDENTE .................... [{}]:", dependenteUi.getNameDependente());
			LOGGER.debug("CPF_DEPENDENTE ................... [{}]:", dependenteUi.getCpf());
			LOGGER.debug("MATRICULA_DEPENDENTE ............. [{}]:", dependenteUi.getMatricula());
			LOGGER.debug("MATRICULA DEPENDENTE EMPRESA: .... [{}]:", dependenteUi.getMatricula());
			LOGGER.debug("DT_NASC_DEPENDENTE ............... [{}]:", dependenteUi.getDtNascimento());
		}

	}

	@Override
	public void saveBatch(List<LancamentoUi> lancamentoUis) throws ServiceException {
		List<LancamentoDetailUi> lancamentoDetailUis;

		for (LancamentoUi lancamentoUi : lancamentoUis) {
			super.saveBatch(lancamentoUi);
		}

		lancamentoDetailUis = new ArrayList<LancamentoDetailUi>();

		for (LancamentoUi lancamentoUi : lancamentoUis) {
			logBatchInfo(lancamentoUi);

			for (LancamentoDetail lancamentoDetail : lancamentoUi.getLancamentoDetails()) {
				lancamentoDetailUis.add((LancamentoDetailUi) lancamentoDetail);

				if (lancamentoDetailUis.size() % AbstractJdbcDao.BATCH_SIZE == 0) {
					lancamentoDetailBatchService.saveBatch(lancamentoDetailUis);
					lancamentoDetailUis.clear();
				}
			}
		}

		if (!lancamentoDetailUis.isEmpty()) {
			lancamentoDetailBatchService.saveBatch(lancamentoDetailUis);
		}
	}

}
