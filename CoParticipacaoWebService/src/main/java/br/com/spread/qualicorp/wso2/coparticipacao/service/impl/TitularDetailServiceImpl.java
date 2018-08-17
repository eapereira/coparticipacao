package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularDetailDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.TitularDetailEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.TitularDetailUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.AbstractJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.TitularDetailJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularDetailService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class TitularDetailServiceImpl extends AbstractServiceImpl<TitularDetailUi, TitularDetailEntity, TitularDetail>
		implements TitularDetailService {

	private static final Logger LOGGER = LogManager.getLogger(TitularDetailServiceImpl.class);

	@Autowired
	private TitularDetailDao titularDetailDao;

	@Autowired
	private TitularDetailUiMapper uiMapper;

	@Autowired
	private TitularDetailEntityMapper entityMapper;

	@Autowired
	private TitularDetailJdbcDao titularDetailJdbcDao;

	@Override
	protected TitularDetailUi createUi() {
		return new TitularDetailUi();
	}

	@Override
	protected TitularDetailEntity createEntity() {
		return new TitularDetailEntity();
	}

	@Override
	protected AbstractDao<TitularDetailEntity> getDao() {
		return titularDetailDao;
	}

	@Override
	protected AbstractMapper<TitularDetail, TitularDetailUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<TitularDetail, TitularDetailEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	protected AbstractJdbcDao<TitularDetailEntity> getJdbcDao() {
		return titularDetailJdbcDao;
	}

	public Object getFieldValue(TitularDetailUi titularDetailUi) throws ServiceException {
		Object value = null;
		ArquivoInputColsDefUi arquivoInputColsDefUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputColsDefUi = (ArquivoInputColsDefUi) titularDetailUi.getArquivoInputColsDef();

			if (ColDefType.INT.equals(arquivoInputColsDefUi.getType())) {
				value = titularDetailUi.getIntValue();
			} else if (ColDefType.LONG.equals(arquivoInputColsDefUi.getType())) {
				value = titularDetailUi.getLongValue();
			} else if (ColDefType.DOUBLE.equals(arquivoInputColsDefUi.getType())) {
				value = titularDetailUi.getBigDecimalValue();
			} else if (ColDefType.DATE.equals(arquivoInputColsDefUi.getType())) {
				value = titularDetailUi.getDateValue();
			} else if (ColDefType.STRING.equals(arquivoInputColsDefUi.getType())) {
				value = titularDetailUi.getStringValue();
			} else {
				throw new ServiceException(
						"The column ArquivoInputColsDefUi[{}] must be [INT, LONG, DOUBLE, DATE OR STRING] types:");
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public TitularDetailUi createTitularDetail(
			TitularUi titularUi,
			ArquivoInputColsDefUi arquivoInputColsDefUi,
			Object value) throws ServiceException {
		TitularDetailUi titularDetailUi;

		try {
			LOGGER.info("BEGIN");

			titularDetailUi = new TitularDetailUi();
			titularDetailUi.setArquivoInputColsDef(arquivoInputColsDefUi);

			if (ColDefType.INT.equals(arquivoInputColsDefUi.getType())) {
				titularDetailUi.setIntValue((Integer) value);
			} else if (ColDefType.LONG.equals(arquivoInputColsDefUi.getType())) {
				titularDetailUi.setLongValue((Long) value);
			} else if (ColDefType.DOUBLE.equals(arquivoInputColsDefUi.getType())) {
				titularDetailUi.setBigDecimalValue((BigDecimal) value);
			} else if (ColDefType.DATE.equals(arquivoInputColsDefUi.getType())) {
				titularDetailUi.setDateValue((LocalDate) value);
			} else if (ColDefType.STRING.equals(arquivoInputColsDefUi.getType())) {
				titularDetailUi.setStringValue((String) value);
			} else {
				throw new ServiceException(
						"The column ArquivoInputColsDefUi[{}] must be [INT, LONG, DOUBLE, DATE OR STRING] types:");
			}

			titularUi.addTitularDetail(titularDetailUi);

			LOGGER.info("END");
			return titularDetailUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void storeDetails(CoParticipacaoContext coParticipacaoContext, TitularUi titularUi) throws ServiceException {
		List<ArquivoInputColsDefUi> arquivoInputColsDefUis;
		Object value;
		TitularDetailUi titularDetailUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputColsDefUis = coParticipacaoContext.getArquivoInputColsDefUis();

			for (ArquivoInputColsDefUi arquivoInputColsDefUi : arquivoInputColsDefUis) {
				value = coParticipacaoContext.getColumnValue(arquivoInputColsDefUi);

				LOGGER.debug("TitularDetail column[{}] has value [{}]:", arquivoInputColsDefUi.getNameColumn(), value);

				if (value != null) {
					titularDetailUi = createTitularDetail(titularUi, arquivoInputColsDefUi, value);
					titularDetailUi.setUserCreated(coParticipacaoContext.getUser());

					LOGGER.debug(
							"TitularDetail column[{}] stored with value [{}]:",
							titularDetailUi.getArquivoInputColsDef().getNameColumn(),
							getFieldValue(titularDetailUi));
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
