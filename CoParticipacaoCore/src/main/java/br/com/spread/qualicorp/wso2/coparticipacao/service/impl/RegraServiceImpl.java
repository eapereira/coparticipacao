package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.OperationType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Regra;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraResult;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraValor;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraOperationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraResultUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraOperationService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraResultService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.util.RegraHelper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraServiceImpl extends AbstractServiceImpl<RegraUi, RegraEntity, Regra> implements RegraService {

	private static final Logger LOGGER = LogManager.getLogger(RegraServiceImpl.class);

	@Autowired
	private RegraDao regraDao;

	@Autowired
	private RegraUiMapper uiMapper;

	@Autowired
	private RegraEntityMapper entityMapper;

	@Autowired
	private RegraConditionalService regraConditionalService;

	@Autowired
	private RegraOperationService regraOperationService;

	@Autowired
	private RegraResultService regraResultService;

	@Autowired
	private IsentoService isentoService;

	@Override
	protected AbstractDao<RegraEntity> getDao() {
		return regraDao;
	}

	@Override
	protected RegraUi createUi() {
		return new RegraUi();
	}

	@Override
	protected RegraEntity createEntity() {
		return new RegraEntity();
	}

	@Override
	protected AbstractMapper<Regra, RegraUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Regra, RegraEntity> getEntityMapper() {
		return entityMapper;
	}

	private BigDecimal executeOperation(OperationType operationType, BigDecimal value, BigDecimal valueResult)
			throws ServiceException {

		try {
			LOGGER.info("BEGIN");

			if (OperationType.ADD.equals(operationType)) {
				LOGGER.info("Executing [{}] + [{}]", valueResult, value);
				valueResult = valueResult.add(value);
			} else if (OperationType.SUBSTRACT.equals(operationType)) {
				LOGGER.info("Executing [{}] - [{}]", valueResult, value);
				valueResult = valueResult.subtract(value);
			} else if (OperationType.DIVISION.equals(operationType)) {
				LOGGER.info("Executing [{}] / [{}]", valueResult, value);
				valueResult = valueResult.divide(value, 2, BigDecimal.ROUND_HALF_UP);
			} else if (OperationType.MULTIPLY.equals(operationType)) {
				LOGGER.info("Executing [{}] * [{}]", valueResult, value);
				valueResult = valueResult.multiply(value);
			}

			LOGGER.info("END");
			return valueResult;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public List<RegraUi> listByArquivoInputId(ArquivoInputUi arquivoInputUi) throws ServiceException {
		List<RegraUi> regraUis;
		List<RegraOperationUi> regraOperationUis;
		List<RegraResultUi> regraResultUis;

		try {
			LOGGER.info("BEGIN");

			regraUis = entityToUi(regraDao.listByArquivoInputId(arquivoInputUi.getId()));

			for (RegraUi regraUi : regraUis) {
				regraOperationUis = regraOperationService.listByRegraId(regraUi);
				regraResultUis = regraResultService.listByRegraId(regraUi);

				regraUi.getRegraOperations().addAll(regraOperationUis);
				regraUi.getRegraResults().addAll(regraResultUis);
			}

			LOGGER.info("END");
			return regraUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<RegraUi> listByArquivoInputSheet(ArquivoInputSheetUi arquivoInputSheetUi) throws ServiceException {
		List<RegraUi> regraUis;
		List<RegraOperationUi> regraOperationUis;
		List<RegraResultUi> regraResultUis;

		try {
			LOGGER.info("BEGIN");

			regraUis = entityToUi(regraDao.listByArquivoInputSheetId(arquivoInputSheetUi.getId()));

			for (RegraUi regraUi : regraUis) {
				regraOperationUis = regraOperationService.listByRegraId(regraUi);
				regraResultUis = regraResultService.listByRegraId(regraUi);

				regraUi.getRegraOperations().addAll(regraOperationUis);
				regraUi.getRegraResults().addAll(regraResultUis);
			}

			LOGGER.info("END");
			return regraUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void applyRegras(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			for (RegraUi regraUi : coParticipacaoContext.getRegraUis()) {
				LOGGER.debug("RegraUi[{}]:", regraUi.getNameRegra());

				if (RegraType.SIMPLES.equals(regraUi.getTpRegra())) {
					applyRegra(coParticipacaoContext, regraUi);
				}
			}

			regraConditionalService.applyRegras(coParticipacaoContext);
			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void applyRegra(CoParticipacaoContext coParticipacaoContext, RegraUi regraUi) throws ServiceException {
		List<RegraOperation> regraOperatios;
		Object value = null;
		BigDecimal result;
		List<ArquivoInputSheetColsDefUi> arquivoInputSheetColsDefUis;

		try {
			LOGGER.info("BEGIN");

			result = BigDecimal.ZERO;
			regraOperatios = regraUi.getRegraOperations();
			arquivoInputSheetColsDefUis = coParticipacaoContext.getArquivoInputSheetColsDefs();

			LOGGER.info("Using Regra [{}]:", regraUi.getNameRegra());

			for (RegraOperation regraOperation : regraOperatios) {

				for (RegraField regraField : regraOperation.getRegraFields()) {
					LOGGER.debug(
							"RegraUi[{}] with RegraField[{}]",
							regraUi.getNameRegra(),
							regraField.getArquivoInputSheetColsDef().getNameColumn());

					for (ArquivoInputSheetColsDefUi arquivoInputSheetColsDefUi : arquivoInputSheetColsDefUis) {
						LOGGER.debug("Mapped column[{}] to be used:", arquivoInputSheetColsDefUi.getNameColumn());

						if (regraField.getArquivoInputSheetColsDef().getId()
								.equals(arquivoInputSheetColsDefUi.getId())) {
							LOGGER.info(
									"Applying regra [{}] to field [{}]:",
									regraUi.getNameRegra(),
									regraField.getArquivoInputSheetColsDef().getNameColumn());

							value = RegraHelper
									.getFieldValue(coParticipacaoContext, arquivoInputSheetColsDefUi);

							LOGGER.info(
									"Field [{}] has value [{}]:",
									regraField.getArquivoInputSheetColsDef().getNameColumn(),
									value);

							if (!BigDecimal.ZERO.equals(value)) {
								if (BigDecimal.ZERO.equals(result)) {
									result = (BigDecimal) value;
								}

								LOGGER.info("Result value [{}]:", result);

								for (RegraValor regraValor : regraOperation.getRegraValors()) {
									value = regraValor.getValor();

									LOGGER.info("Field value for RegraValor has value [{}]:", value);

									result = executeOperation(regraOperation.getTpOperation(), (BigDecimal) value, result);
								}
							}

							break;
						}
					}
				}
			}

			LOGGER.info("Final result after all RegraOperations value is [{}]:", result);

			for (RegraResult regraResult : regraUi.getRegraResults()) {
				for (ArquivoInputSheetColsDefUi arquivoInputSheetColsDefUi : arquivoInputSheetColsDefUis) {
					if (regraResult.getArquivoInputSheetColsDef().getId().equals(arquivoInputSheetColsDefUi.getId())) {
						LOGGER.info(
								"Sending calculated value [{}] to lancamento field [{}]",
								result,
								regraResult.getArquivoInputSheetColsDef().getNameColumn());

						RegraHelper
								.setFieldValueAsBigDecimal(coParticipacaoContext, arquivoInputSheetColsDefUi, result);
						break;
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
