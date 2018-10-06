package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DependenteDetailDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DependenteDetailEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DependenteDetailUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DependenteDetailServiceImpl
		extends AbstractServiceImpl<DependenteDetailUi, DependenteDetailEntity, DependenteDetail>
		implements DependenteDetailService {

	private static final Logger LOGGER = LogManager.getLogger(DependenteDetailServiceImpl.class);

	@Autowired
	private DependenteDetailDao titularDetailDao;

	@Autowired
	private DependenteDetailUiMapper uiMapper;

	@Autowired
	private DependenteDetailEntityMapper entityMapper;

	@Override
	protected DependenteDetailUi createUi() {
		return new DependenteDetailUi();
	}

	@Override
	protected DependenteDetailEntity createEntity() {
		return new DependenteDetailEntity();
	}

	@Override
	protected AbstractDao<DependenteDetailEntity> getDao() {
		return titularDetailDao;
	}

	@Override
	protected AbstractMapper<DependenteDetail, DependenteDetailUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<DependenteDetail, DependenteDetailEntity> getEntityMapper() {
		return entityMapper;
	}

	public Object getFieldValue(DependenteDetailUi dependenteDetailUi) throws ServiceException {
		Object value = null;
		ArquivoInputColsDefUi arquivoInputColsDefUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputColsDefUi = (ArquivoInputColsDefUi) dependenteDetailUi.getArquivoInputColsDef();

			if (ColDefType.INT.equals(arquivoInputColsDefUi.getType())) {
				value = dependenteDetailUi.getIntValue();
			} else if (ColDefType.LONG.equals(arquivoInputColsDefUi.getType())) {
				value = dependenteDetailUi.getLongValue();
			} else if (ColDefType.DOUBLE.equals(arquivoInputColsDefUi.getType())) {
				value = dependenteDetailUi.getBigDecimalValue();
			} else if (ColDefType.DATE.equals(arquivoInputColsDefUi.getType())) {
				value = dependenteDetailUi.getDateValue();
			} else if (ColDefType.STRING.equals(arquivoInputColsDefUi.getType())) {
				value = dependenteDetailUi.getStringValue();
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

	public DependenteDetailUi createDependenteDetail(
			DependenteUi dependenteUi,
			ArquivoInputColsDefUi arquivoInputColsDefUi,
			Object value) throws ServiceException {
		DependenteDetailUi dependenteDetailUi;

		try {
			LOGGER.info("BEGIN");

			dependenteDetailUi = new DependenteDetailUi();
			dependenteDetailUi.setArquivoInputColsDef(arquivoInputColsDefUi);

			if (ColDefType.INT.equals(arquivoInputColsDefUi.getType())) {
				dependenteDetailUi.setIntValue((Integer) value);
			} else if (ColDefType.LONG.equals(arquivoInputColsDefUi.getType())) {
				dependenteDetailUi.setLongValue((Long) value);
			} else if (ColDefType.DOUBLE.equals(arquivoInputColsDefUi.getType())) {
				dependenteDetailUi.setBigDecimalValue((BigDecimal) value);
			} else if (ColDefType.DATE.equals(arquivoInputColsDefUi.getType())) {
				dependenteDetailUi.setDateValue((LocalDate) value);
			} else if (ColDefType.STRING.equals(arquivoInputColsDefUi.getType())) {
				dependenteDetailUi.setStringValue((String) value);
			} else {
				throw new ServiceException(
						"The column ArquivoInputColsDefUi[{}] must be [INT, LONG, DOUBLE, DATE OR STRING] types:");
			}

			dependenteUi.addDependenteDetail(dependenteDetailUi);

			LOGGER.info("END");
			return dependenteDetailUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public void storeDetails(CoParticipacaoContext coParticipacaoContext, DependenteUi dependenteUi)
			throws ServiceException {
		List<ArquivoInputColsDefUi> arquivoInputColsDefUis;
		Object value;
		DependenteDetailUi dependenteDetailUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputColsDefUis = coParticipacaoContext.getArquivoInputColsDefUis();

			for (ArquivoInputColsDefUi arquivoInputColsDefUi : arquivoInputColsDefUis) {
				value = coParticipacaoContext.getColumnValue(arquivoInputColsDefUi);

				LOGGER.debug(
						"DependenteDetail column[{}] has value [{}]:",
						arquivoInputColsDefUi.getNameColumn(),
						value);

				if (value != null) {
					dependenteDetailUi = createDependenteDetail(dependenteUi, arquivoInputColsDefUi, value);
					dependenteDetailUi.setUserCreated(coParticipacaoContext.getUser());

					LOGGER.debug(
							"DependenteDetail column[{}] stored with value [{}]:",
							dependenteDetailUi.getArquivoInputColsDef().getNameColumn(),
							getFieldValue(dependenteDetailUi));
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
