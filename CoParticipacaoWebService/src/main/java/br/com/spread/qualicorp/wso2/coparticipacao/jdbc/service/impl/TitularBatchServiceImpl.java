package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.TitularEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.TitularUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.AbstractJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.TitularJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.TitularBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.TitularDetailBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularDetailService;

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
	private TitularDetailService titularDetailService;

	@Autowired
	private TitularEntityMapper entityMapper;

	@Autowired
	private TitularDetailBatchService titularDetailBatchService;

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
	protected void logBatchInfo(TitularUi titularUi) throws ServiceException {
		LOGGER.debug("ID_TITULAR:........................[{}]", titularUi.getId());
		LOGGER.debug("NM_TITULAR:........................[{}]", titularUi.getNameTitular());
		LOGGER.debug("NR_CPF:............................[{}]", titularUi.getCpf());
		LOGGER.debug("NR_MATRICULA:......................[{}]", titularUi.getMatricula());
		LOGGER.debug("NR_MATRICULA_EMPRESA:..............[{}]", titularUi.getMatriculaEmpresa());
		LOGGER.debug("DT_NASCIMENTO:.....................[{}]", titularUi.getDtNascimento());
		LOGGER.debug("DT_ADMISSAO:.......................[{}]", titularUi.getDtAdmissao());
	}

	@Override
	protected AbstractDao<TitularEntity> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveBatch(List<TitularUi> titularUis) throws ServiceException {
		List<TitularDetailUi> titularDetailUis;

		try {
			LOGGER.info("BEGIN");

			titularDetailUis = new ArrayList<TitularDetailUi>();

			for (TitularUi titularUi : titularUis) {
				super.saveBatch(titularUi);
			}

			for (TitularUi titularUi : titularUis) {
				logBatchInfo(titularUi);

				for (TitularDetail titularDetail : titularUi.getTitularDetails()) {
					/*LOGGER.debug(
							"TitularDetailUi.[{}] with value [{}]",
							titularDetail.getArquivoInputColsDef().getNameColumn(),
							titularDetailService.getFieldValue((TitularDetailUi) titularDetail));*/

					titularDetailUis.add((TitularDetailUi) titularDetail);

					if (titularDetailUis.size() % AbstractJdbcDao.BATCH_SIZE == 0) {
						titularDetailBatchService.saveBatch(titularDetailUis);
						titularDetailUis.clear();
					}
				}
			}

			if (!titularDetailUis.isEmpty()) {
				titularDetailBatchService.saveBatch(titularDetailUis);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

}
