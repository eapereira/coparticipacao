package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.TitularIsentoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularIsentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularIsentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.TitularIsentoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.TitularIsentoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularIsentoService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TitularIsentoServiceImpl extends AbstractServiceImpl<TitularIsentoUi, TitularIsentoEntity, TitularIsento>
		implements TitularIsentoService {

	private static final Logger LOGGER = LogManager.getLogger(TitularIsentoServiceImpl.class);

	@Autowired
	private TitularIsentoDao titularIsentoDao;

	@Autowired
	private TitularIsentoUiMapper uiMapper;

	@Autowired
	private TitularIsentoEntityMapper entityMapper;

	@Autowired
	private TitularIsentoJdbcDao titularIsentoJdbcDao;

	@Override
	protected TitularIsentoUi createUi() {
		return new TitularIsentoUi();
	}

	@Override
	protected TitularIsentoEntity createEntity() {
		return new TitularIsentoEntity();
	}

	@Override
	protected AbstractDao<TitularIsentoEntity> getDao() {
		return titularIsentoDao;
	}

	@Override
	protected AbstractMapper<TitularIsento, TitularIsentoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<TitularIsento, TitularIsentoEntity> getEntityMapper() {
		return entityMapper;
	}

	public void deleteByMesAndAno(EmpresaUi empresaUi, Integer mes, Integer ano) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			titularIsentoDao.deleteByMesAndAno(empresaUi.getId(), mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected void logBatchInfo(TitularIsentoUi titularIsentoUi) throws ServiceException {
		TitularUi titularUi = (TitularUi) titularIsentoUi.getTitular();

		LOGGER.debug("TitularIsento.ID: ...................... [{}]", titularIsentoUi.getId());
		LOGGER.debug("TitularIsento.MES: ..................... [{}]", titularIsentoUi.getMes());
		LOGGER.debug("TitularIsento.ANO: ..................... [{}]", titularIsentoUi.getAno());
		LOGGER.debug("TitularIsento.ISENTO_TYPE: ............. [{}]", titularIsentoUi.getIsentoType().getDescription());

		LOGGER.debug("TitularIsento.Titular.ID: .............. [{}]", titularUi.getId());
		LOGGER.debug("TitularIsento.Titular.NAME: ............ [{}]", titularUi.getNameTitular());
		LOGGER.debug("TitularIsento.Titular.CPF: ............. [{}]", titularUi.getCpf());
		LOGGER.debug("TitularIsento.Titular.MATRICULA: ....... [{}]", titularUi.getMatricula());
		LOGGER.debug("TitularIsento.Titular.DT_NASCIMENTO: ... [{}]", titularUi.getDtNascimento());
	}

	public List<TitularIsentoUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException {
		List<TitularIsentoUi> titularIsentoUis;

		try {
			LOGGER.info("BEGIN");

			titularIsentoUis = entityToUi(titularIsentoDao.listByEmpresaId(empresaUi.getId()));

			LOGGER.info("END");
			return titularIsentoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void deleteByContratoAndMesAndAno(ContratoUi contratoUi, Integer mes, Integer ano) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			titularIsentoDao.deleteByContratoAndMesAndAno(contratoUi.getId(), mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
