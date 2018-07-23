package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DesconhecidoDetailDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DesconhecidoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DesconhecidoDetailEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DesconhecidoDetailUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.AbstractJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.DesconhecidoDetailJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DesconhecidoDetailServiceImpl extends
		AbstractServiceImpl<DesconhecidoDetailUi, DesconhecidoDetailEntity, DesconhecidoDetail>
		implements DesconhecidoDetailService {

	private static final Logger LOGGER = LogManager
			.getLogger(DesconhecidoDetailServiceImpl.class);

	@Autowired
	private DesconhecidoDetailUiMapper uiMapper;

	@Autowired
	private DesconhecidoDetailEntityMapper entityMapper;

	@Autowired
	private DesconhecidoDetailDao desconhecidoDetailDao;

	@Autowired
	private LancamentoDetailService lancamentoDetailService;

	@Autowired
	private DesconhecidoDetailJdbcDao desconhecidoDetailJdbcDao;

	@Override
	protected DesconhecidoDetailUi createUi() {
		return new DesconhecidoDetailUi();
	}

	@Override
	protected DesconhecidoDetailEntity createEntity() {
		return new DesconhecidoDetailEntity();
	}

	@Override
	protected AbstractDao<DesconhecidoDetailEntity> getDao() {
		return desconhecidoDetailDao;
	}

	@Override
	protected AbstractMapper<DesconhecidoDetail, DesconhecidoDetailUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<DesconhecidoDetail, DesconhecidoDetailEntity> getEntityMapper() {
		return entityMapper;
	}

	public void defineDesconhecidoDetailValue(
			DesconhecidoDetail desconhecidoDetail,
			Object value,
			ArquivoInputColsDefUi arquivoInputColsDefUi)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (ColDefType.INT.equals(arquivoInputColsDefUi.getType())) {
				desconhecidoDetail.setIntValue((Integer) value);
			} else if (ColDefType.LONG
					.equals(arquivoInputColsDefUi.getType())) {
				desconhecidoDetail.setLongValue((Long) value);
			} else if (ColDefType.DOUBLE
					.equals(arquivoInputColsDefUi.getType())) {
				desconhecidoDetail.setBigDecimalValue((BigDecimal) value);
			} else if (ColDefType.DATE
					.equals(arquivoInputColsDefUi.getType())) {
				desconhecidoDetail.setDateValue(((LocalDate) value));
			} else if (ColDefType.STRING
					.equals(arquivoInputColsDefUi.getType())) {
				desconhecidoDetail.setStringValue((String) value);
			} else {
				LOGGER.info(
						"Não foi definido um tipo válido para a coluna [{}]:",
						arquivoInputColsDefUi.getNameColumn());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void createDesconhecidoDetail(
			DesconhecidoUi desconhecidoUi,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		Object value;

		try {
			LOGGER.info("BEGIN");

			for (ArquivoInputColsDefUi arquivoInputColsDefUi : coParticipacaoContext
					.getArquivoInputColsDefUis()) {

				value = coParticipacaoContext
						.getColumnValue(arquivoInputColsDefUi);

				createDesconhecidoDetail(
						desconhecidoUi,
						coParticipacaoContext,
						arquivoInputColsDefUi,
						value);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Object getValue(DesconhecidoDetailUi desconhecidoDetailUi)
			throws ServiceException {
		ArquivoInputColsDefUi arquivoInputColsDefUi;
		Object value = null;

		try {
			LOGGER.info("BEGIN");

			arquivoInputColsDefUi = (ArquivoInputColsDefUi) desconhecidoDetailUi
					.getArquivoInputColsDef();

			if (ColDefType.INT.equals(arquivoInputColsDefUi.getType())) {
				value = desconhecidoDetailUi.getIntValue();
			} else if (ColDefType.LONG
					.equals(arquivoInputColsDefUi.getType())) {
				value = desconhecidoDetailUi.getLongValue();
			} else if (ColDefType.DOUBLE
					.equals(arquivoInputColsDefUi.getType())) {
				value = desconhecidoDetailUi.getBigDecimalValue();
			} else if (ColDefType.DATE
					.equals(arquivoInputColsDefUi.getType())) {
				value = desconhecidoDetailUi.getDateValue();
			} else if (ColDefType.STRING
					.equals(arquivoInputColsDefUi.getType())) {
				value = desconhecidoDetailUi.getStringValue();
			} else {
				LOGGER.info(
						"Não foi definido um tipo válido para a coluna [{}]:",
						arquivoInputColsDefUi.getNameColumn());
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void createDesconhecidoDetail(
			DesconhecidoUi desconhecidoUi,
			CoParticipacaoContext coParticipacaoContext,
			LancamentoUi lancamentoUi) throws ServiceException {
		ArquivoInputColsDefUi arquivoInputColsDefUi;
		Object value;

		try {
			LOGGER.info("BEGIN");

			for (LancamentoDetail lancamentoDetail : lancamentoUi
					.getLancamentoDetails()) {
				arquivoInputColsDefUi = (ArquivoInputColsDefUi) lancamentoDetail
						.getArquivoInputColsDef();

				value = lancamentoDetailService
						.getFieldValue(arquivoInputColsDefUi, lancamentoDetail);

				createDesconhecidoDetail(
						desconhecidoUi,
						coParticipacaoContext,
						arquivoInputColsDefUi,
						value);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void createDesconhecidoDetail(
			DesconhecidoUi desconhecidoUi,
			CoParticipacaoContext coParticipacaoContext,
			ArquivoInputColsDefUi arquivoInputColsDefUi,
			Object value) throws ServiceException {
		DesconhecidoDetailUi desconhecidoDetail;

		try {
			LOGGER.info("BEGIN");

			desconhecidoDetail = new DesconhecidoDetailUi();

			LOGGER.info(
					"Creating DesconhecidoDetail for column[{}] with value [{}]:",
					arquivoInputColsDefUi.getNameColumn(),
					value);

			defineDesconhecidoDetailValue(
					desconhecidoDetail,
					value,
					arquivoInputColsDefUi);

			desconhecidoDetail.setArquivoInputColsDef(arquivoInputColsDefUi);
			desconhecidoDetail.setCreated(LocalDateTime.now());
			desconhecidoDetail.setAltered(LocalDateTime.now());
			desconhecidoDetail.setUserCreated(coParticipacaoContext.getUser());
			desconhecidoDetail.setUserAltered(coParticipacaoContext.getUser());

			desconhecidoUi.addDesconhecidoDetail(desconhecidoDetail);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected void logBatchInfo(DesconhecidoDetailUi desconhecidoDetail)
			throws ServiceException {
		LOGGER.debug(
				"ID_DESCONHECIDO:............ [{}]",
				desconhecidoDetail.getDesconhecido().getId());
		LOGGER.debug(
				"COLUMN_NAME:................ [{}]",
				desconhecidoDetail.getArquivoInputColsDef().getNameColumn());
		LOGGER.debug(
				"COLUMN_TYPE:................ [{}]",
				desconhecidoDetail.getArquivoInputColsDef().getType()
						.getDescription());
		LOGGER.debug(
				"VL_DOUBLE:.................. [{}]",
				desconhecidoDetail.getBigDecimalValue());
		LOGGER.debug(
				"VL_DATE:.................... [{}]",
				desconhecidoDetail.getDateValue());
		LOGGER.debug(
				"VL_INT:..................... [{}]",
				desconhecidoDetail.getIntValue());
		LOGGER.debug(
				"VL_LONG:.................... [{}]",
				desconhecidoDetail.getLongValue());
		LOGGER.debug(
				"VL_STRING:.................. [{}]",
				desconhecidoDetail.getStringValue());
	}

	@Override
	protected AbstractJdbcDao<DesconhecidoDetailEntity> getJdbcDao() {
		return desconhecidoDetailJdbcDao;
	}
}
