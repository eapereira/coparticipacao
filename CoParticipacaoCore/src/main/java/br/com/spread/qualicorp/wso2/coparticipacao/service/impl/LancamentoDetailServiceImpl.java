package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.AbstractBatchDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.LancamentoDetailJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoDetailDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.LancamentoDetailEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.LancamentoDetailUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LancamentoDetailServiceImpl
		extends AbstractServiceImpl<LancamentoDetailUi, LancamentoDetailEntity, LancamentoDetail>
		implements LancamentoDetailService {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoDetailServiceImpl.class);

	@Autowired
	private LancamentoDetailDao lancamentoDetailDao;

	@Autowired
	private LancamentoDetailUiMapper uiMapper;

	@Autowired
	private LancamentoDetailEntityMapper entityMapper;

	@Autowired
	private LancamentoDetailJdbcDao lancamentoDetailJdbcDao;

	@Override
	protected AbstractDao<LancamentoDetailEntity> getDao() {
		return lancamentoDetailDao;
	}

	@Override
	protected LancamentoDetailUi createUi() {
		return new LancamentoDetailUi();
	}

	@Override
	protected LancamentoDetailEntity createEntity() {
		return new LancamentoDetailEntity();
	}

	@Override
	protected AbstractMapper<LancamentoDetail, LancamentoDetailUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<LancamentoDetail, LancamentoDetailEntity> getEntityMapper() {
		return entityMapper;
	}

	public void storeLancamentoDetail(
			LancamentoUi lancamentoUi,
			ArquivoInputColsDefUi arquivoInputColsDefUi,
			Object value,
			UserUi userUi) throws ServiceException {
		LancamentoDetailUi lancamentoDetailUi;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Adding LancamentoDetail column [{}]:", arquivoInputColsDefUi.getNameColumn());

			lancamentoDetailUi = new LancamentoDetailUi();
			lancamentoDetailUi.setArquivoInputColsDef(arquivoInputColsDefUi);
			lancamentoDetailUi.setLancamento(lancamentoUi);

			updateLancamentoDetailValue(lancamentoDetailUi, arquivoInputColsDefUi.getType(), value);

			lancamentoDetailUi.setUserCreated(userUi);
			lancamentoDetailUi.setUserAltered(userUi);
			lancamentoDetailUi.setCreated(LocalDateTime.now());
			lancamentoDetailUi.setAltered(LocalDateTime.now());

			lancamentoUi.addLancamentoDetail(lancamentoDetailUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateLancamentoDetail(
			LancamentoDetailUi lancamentoDetailUi,
			ArquivoInputColsDefUi arquivoInputColsDefUi,
			Object value) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			updateLancamentoDetailValue(lancamentoDetailUi, arquivoInputColsDefUi.getType(), value);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	private void updateLancamentoDetailValue(LancamentoDetailUi lancamentoDetailUi, ColDefType colDefType, Object value)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (ColDefType.INT.equals(colDefType)) {
				lancamentoDetailUi.setIntValue((Integer) value);
			} else if (ColDefType.LONG.equals(colDefType)) {
				lancamentoDetailUi.setLongValue((Long) value);
			} else if (ColDefType.DOUBLE.equals(colDefType)) {
				lancamentoDetailUi.setBigDecimalValue((BigDecimal) value);
				lancamentoDetailUi.getLancamento().setValorPrincipal((BigDecimal) value);
			} else if (ColDefType.DATE.equals(colDefType)) {
				lancamentoDetailUi.setDateValue(((LocalDate) value));
			} else if (ColDefType.STRING.equals(colDefType)) {
				lancamentoDetailUi.setStringValue((String) value);
			} else {
				LOGGER.info(
						"Não foi definido um tipo válido para a coluna [{}]:",
						lancamentoDetailUi.getArquivoInputColsDef().getNameColumn());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public BigDecimal getFieldValueAsBigDecimal(
			ArquivoInputColsDef arquivoInputColsDef,
			LancamentoDetail lancamentoDetail) throws ServiceException {
		BigDecimal value;

		try {
			LOGGER.info("BEGIN");

			value = BigDecimal.ZERO;

			if (arquivoInputColsDef.getId().equals(lancamentoDetail.getArquivoInputColsDef().getId())) {
				if (ColDefType.INT.equals(arquivoInputColsDef.getType())) {
					value = BigDecimal.valueOf(lancamentoDetail.getIntValue());
				} else if (ColDefType.LONG.equals(arquivoInputColsDef.getType())) {
					value = BigDecimal.valueOf(lancamentoDetail.getLongValue());
				} else if (ColDefType.DOUBLE.equals(arquivoInputColsDef.getType())) {
					value = lancamentoDetail.getBigDecimalValue();
				} else {
					throw new ServiceException(
							"The column ArquivoInputColsDefUi[%s] must be DOUBLE type to use with Regra:",
							arquivoInputColsDef.getNameColumn());
				}
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Object getFieldValue(ArquivoInputColsDef arquivoInputColsDef, LancamentoDetail lancamentoDetail)
			throws ServiceException {
		Object value;

		try {
			LOGGER.info("BEGIN");

			value = null;

			if (arquivoInputColsDef.getId().equals(lancamentoDetail.getArquivoInputColsDef().getId())) {
				if (ColDefType.INT.equals(arquivoInputColsDef.getType())) {
					value = lancamentoDetail.getIntValue();
				} else if (ColDefType.LONG.equals(arquivoInputColsDef.getType())) {
					value = lancamentoDetail.getLongValue();
				} else if (ColDefType.DOUBLE.equals(arquivoInputColsDef.getType())) {
					value = lancamentoDetail.getBigDecimalValue();
				} else if (ColDefType.DATE.equals(arquivoInputColsDef.getType())) {
					value = lancamentoDetail.getDateValue();
				} else if (ColDefType.STRING.equals(arquivoInputColsDef.getType())) {
					value = lancamentoDetail.getStringValue();
				} else {
					throw new ServiceException(
							"The column ArquivoInputColsDefUi[{}] must be [INT, LONG, DOUBLE, DATE OR STRING] types to use with Regra:");
				}
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public LancamentoDetailUi findByArquivoInputColsDefId(
			LancamentoUi lancamentoUi,
			ArquivoInputColsDefUi arquivoInputColsDefUi) throws ServiceException {
		LancamentoDetailUi lancamentoDetailUi = null;

		try {
			LOGGER.info("BEGIN");

			for (LancamentoDetail lancamentoDetail : lancamentoUi.getLancamentoDetails()) {
				if (lancamentoDetail.getArquivoInputColsDef().getId().equals(arquivoInputColsDefUi.getId())) {
					lancamentoDetailUi = (LancamentoDetailUi) lancamentoDetail;
				}
			}

			LOGGER.info("END");
			return lancamentoDetailUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public void showLancamentoDetailInfo(LancamentoUi lancamentoUi) throws ServiceException {
		ArquivoInputColsDef arquivoInputColsDef;

		try {
			LOGGER.info("BEGIN");

			for (LancamentoDetail lancamentoDetail : lancamentoUi.getLancamentoDetails()) {
				arquivoInputColsDef = lancamentoDetail.getArquivoInputColsDef();

				LOGGER.info(
						"LancamentoDetail with column [{}] and value [{}]:",
						arquivoInputColsDef.getNameColumn(),
						getFieldValue(arquivoInputColsDef, lancamentoDetail));
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public Object getFieldValue(ArquivoInputColsDef arquivoInputColsDef, LancamentoUi lancamentoUi)
			throws ServiceException {
		Object value = null;

		try {
			LOGGER.info("BEGIN");

			LOGGER.debug("Searching for LancamentoDetail column[{}]:", arquivoInputColsDef.getNameColumn());

			for (LancamentoDetail lancamentoDetail : lancamentoUi.getLancamentoDetails()) {
				LOGGER.debug(
						"Reading LancaentoDetail column [{}]:",
						lancamentoDetail.getArquivoInputColsDef().getNameColumn());

				if (lancamentoDetail.getArquivoInputColsDef().getId().equals(arquivoInputColsDef.getId())) {
					value = getFieldValue(arquivoInputColsDef, lancamentoDetail);

					LOGGER.debug(
							"Found LancamentoDetail column [{}] with value [{}]:",
							arquivoInputColsDef.getNameColumn(),
							value);
					break;
				}
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void deleteByMesAndAno(ContratoUi contratoUi, int mes, int ano) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			lancamentoDetailJdbcDao.deleteByContratoAndMesAndAno(contratoUi.getId(), mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<LancamentoDetailUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException {
		List<LancamentoDetailUi> lancamentoDetailUis;

		try {
			LOGGER.info("BEGIN");

			lancamentoDetailUis = entityToUi(lancamentoDetailDao.listByEmpresaId(empresaUi.getId()));

			LOGGER.info("END");
			return lancamentoDetailUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
