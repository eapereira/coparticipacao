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
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DesconhecidoDetailEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DesconhecidoDetailUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoDetailService;
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
		DesconhecidoDetailUi desconhecidoDetail;

		try {
			LOGGER.info("BEGIN");

			for (ArquivoInputColsDefUi arquivoInputColsDefUi : coParticipacaoContext
					.getArquivoInputColsDefUis()) {
				desconhecidoDetail = new DesconhecidoDetailUi();

				value = coParticipacaoContext
						.getColumnValue(arquivoInputColsDefUi);

				defineDesconhecidoDetailValue(
						desconhecidoDetail,
						value,
						arquivoInputColsDefUi);

				desconhecidoDetail
						.setArquivoInputColsDef(arquivoInputColsDefUi);
				desconhecidoDetail.setCreated(LocalDateTime.now());
				desconhecidoDetail.setAltered(LocalDateTime.now());
				desconhecidoDetail
						.setUserCreated(coParticipacaoContext.getUser());
				desconhecidoDetail
						.setUserAltered(coParticipacaoContext.getUser());

				desconhecidoUi.addDesconhecidoDetail(desconhecidoDetail);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
