package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioIsentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;
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
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraOperationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraResultUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraOperationService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraResultService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

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
	private LancamentoDetailService lancamentoDetailService;

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

	public void applyRegras(CoParticipacaoContext coParticipacaoContext, LancamentoUi lancamentoUi)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			for (LancamentoDetail lancamentoDetail : lancamentoUi.getLancamentoDetails()) {

				for (RegraUi regraUi : coParticipacaoContext.getRegraUis()) {

					LOGGER.info(
							"Checking if we have a Regra for column [{}]:",
							lancamentoDetail.getArquivoInputColsDef().getNameColumn());

					if (RegraType.SIMPLES.equals(regraUi.getTpRegra())) {
						applyRegra(coParticipacaoContext, regraUi, lancamentoDetail);
					}
				}
			}

			regraConditionalService.applyRegras(coParticipacaoContext, lancamentoUi);
			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean isLancamentoDetailAcceptable(RegraUi regraUi, LancamentoDetailUi lancamentoDetailUi)
			throws ServiceException {
		ArquivoInputColsDefUi arquivoInputColsDefUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputColsDefUi = (ArquivoInputColsDefUi) lancamentoDetailUi.getArquivoInputColsDef();

			for (RegraOperation regraOperation : regraUi.getRegraOperations()) {
				for (RegraField regraField : regraOperation.getRegraFields()) {
					if (regraField.getArquivoInputColsDef().getId().equals(arquivoInputColsDefUi.getId())) {
						LOGGER.info("END");
						LOGGER.info(
								"This Regra can process that LancamentoDetail with field[{}]",
								arquivoInputColsDefUi.getNameColumn());
						return true;
					}
				}
			}

			LOGGER.info("Using Regra refuised by LancamentoDetail:");
			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void applyRegra(
			CoParticipacaoContext coParticipacaoContext,
			RegraUi regraUi,
			LancamentoDetail lancamentoDetail) throws ServiceException {
		List<RegraOperation> regraOperatios;
		BigDecimal value;
		BigDecimal result;

		try {
			LOGGER.info("BEGIN");

			if (isLancamentoDetailAcceptable(regraUi, (LancamentoDetailUi) lancamentoDetail)) {

				result = BigDecimal.ZERO;
				regraOperatios = regraUi.getRegraOperations();

				LOGGER.info("Using Regra [{}]:", regraUi.getNameRegra());

				for (RegraOperation regraOperation : regraOperatios) {

					for (RegraField regraField : regraOperation.getRegraFields()) {

						LOGGER.info(
								"Applying regra [{}] to field [{}]:",
								regraUi.getNameRegra(),
								regraField.getArquivoInputColsDef().getNameColumn());

						value = lancamentoDetailService
								.getFieldValueAsBigDecimal(regraField.getArquivoInputColsDef(), lancamentoDetail);

						LOGGER.info(
								"Field [{}] has value [{}]:",
								regraField.getArquivoInputColsDef().getNameColumn(),
								value);

						if (!BigDecimal.ZERO.equals(value)) {
							if (BigDecimal.ZERO.equals(result)) {
								result = value;
							}

							LOGGER.info("Result value [{}]:", result);

							for (RegraValor regraValor : regraOperation.getRegraValors()) {
								value = regraValor.getValor();

								LOGGER.info("Field value for RegraValor has value [{}]:", value);

								result = executeOperation(regraOperation.getTpOperation(), value, result);
							}
						}
					}
				}

				LOGGER.info("Final result after all RegraOperations value is [{}]:", result);

				for (RegraResult regraResult : regraUi.getRegraResults()) {

					LOGGER.info(
							"Sending calculated value [{}] to lancamento field [{}]",
							result,
							regraResult.getArquivoInputColsDef().getNameColumn());

					if (ColDefType.DOUBLE.equals(regraResult.getArquivoInputColsDef().getType())) {
						lancamentoDetailService.updateLancamentoDetail(
								(LancamentoDetailUi) lancamentoDetail,
								(ArquivoInputColsDefUi) regraResult.getArquivoInputColsDef(),
								result);
					} else if (ColDefType.LONG.equals(regraResult.getArquivoInputColsDef().getType())) {
						lancamentoDetailService.updateLancamentoDetail(
								(LancamentoDetailUi) lancamentoDetail,
								(ArquivoInputColsDefUi) regraResult.getArquivoInputColsDef(),
								result.longValue());
					} else if (ColDefType.INT.equals(regraResult.getArquivoInputColsDef().getType())) {
						lancamentoDetailService.updateLancamentoDetail(
								(LancamentoDetailUi) lancamentoDetail,
								(ArquivoInputColsDefUi) regraResult.getArquivoInputColsDef(),
								result.intValue());
					}
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
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

	public void applyRegras(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			List<IsentoInputSheetCols> isentoInputSheetCols) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			for (RegraUi regraUi : coParticipacaoContext.getRegraUis()) {
				if (RegraType.SIMPLES.equals(regraUi.getTpRegra())) {
					applyRegra(coParticipacaoContext, regraUi, beneficiarioIsentoUi, isentoInputSheetCols);
				}
			}

			regraConditionalService.applyRegras(coParticipacaoContext, beneficiarioIsentoUi, isentoInputSheetCols);
			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void applyRegra(
			CoParticipacaoContext coParticipacaoContext,
			RegraUi regraUi,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			List<IsentoInputSheetCols> isentoInputSheetColss) throws ServiceException {
		List<RegraOperation> regraOperatios;
		BigDecimal value = null;
		BigDecimal result;
		ArquivoInputColsDefUi arquivoInputColsDefUi;

		try {
			LOGGER.info("BEGIN");

			result = BigDecimal.ZERO;
			regraOperatios = regraUi.getRegraOperations();

			LOGGER.info("Using Regra [{}]:", regraUi.getNameRegra());

			for (RegraOperation regraOperation : regraOperatios) {

				for (RegraField regraField : regraOperation.getRegraFields()) {
					for (IsentoInputSheetCols isentoInputSheetCols : isentoInputSheetColss) {
						arquivoInputColsDefUi = (ArquivoInputColsDefUi) isentoInputSheetCols.getArquivoInputColsDef();

						if (regraField.getArquivoInputColsDef().getId().equals(arquivoInputColsDefUi.getId())) {
							LOGGER.info(
									"Applying regra [{}] to field [{}]:",
									regraUi.getNameRegra(),
									regraField.getArquivoInputColsDef().getNameColumn());

							value = isentoService.getFieldValueAsBigDecimal(
									isentoInputSheetCols.getBeneficiarioIsentoColType(),
									beneficiarioIsentoUi);

							LOGGER.info(
									"Field [{}] has value [{}]:",
									regraField.getArquivoInputColsDef().getNameColumn(),
									value);

							if (!BigDecimal.ZERO.equals(value)) {
								if (BigDecimal.ZERO.equals(result)) {
									result = value;
								}

								LOGGER.info("Result value [{}]:", result);

								for (RegraValor regraValor : regraOperation.getRegraValors()) {
									value = regraValor.getValor();

									LOGGER.info("Field value for RegraValor has value [{}]:", value);

									result = executeOperation(regraOperation.getTpOperation(), value, result);
								}
							}
							
							break;
						}
					}
				}
			}

			LOGGER.info("Final result after all RegraOperations value is [{}]:", result);

			for (RegraResult regraResult : regraUi.getRegraResults()) {
				for (IsentoInputSheetCols isentoInputSheetCols : isentoInputSheetColss) {
					arquivoInputColsDefUi = (ArquivoInputColsDefUi) isentoInputSheetCols.getArquivoInputColsDef();

					if (regraResult.getArquivoInputColsDef().getId().equals(arquivoInputColsDefUi.getId())) {

						LOGGER.info(
								"Sending calculated value [{}] to lancamento field [{}]",
								result,
								regraResult.getArquivoInputColsDef().getNameColumn());

						isentoService.setFieldValueAsBigDecimal(
								isentoInputSheetCols.getBeneficiarioIsentoColType(),
								beneficiarioIsentoUi,
								result);
						
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

	public void applyRegra(
			CoParticipacaoContext coParticipacaoContext,
			RegraUi regraUi,
			IsentoInputSheetColsUi isentoInputSheetColsUi,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException {
		List<RegraOperation> regraOperatios;
		BigDecimal value = null;
		BigDecimal result;
		ArquivoInputColsDefUi arquivoInputColsDefUi;

		try {
			LOGGER.info("BEGIN");

			result = BigDecimal.ZERO;
			regraOperatios = regraUi.getRegraOperations();

			LOGGER.info("Using Regra [{}]:", regraUi.getNameRegra());

			for (RegraOperation regraOperation : regraOperatios) {

				for (RegraField regraField : regraOperation.getRegraFields()) {
					arquivoInputColsDefUi = (ArquivoInputColsDefUi) isentoInputSheetColsUi.getArquivoInputColsDef();

					if (regraField.getArquivoInputColsDef().getId().equals(arquivoInputColsDefUi.getId())) {
						LOGGER.info(
								"Applying regra [{}] to field [{}]:",
								regraUi.getNameRegra(),
								regraField.getArquivoInputColsDef().getNameColumn());

						value = isentoService.getFieldValueAsBigDecimal(
								isentoInputSheetColsUi.getBeneficiarioIsentoColType(),
								beneficiarioIsentoUi);

						LOGGER.info(
								"Field [{}] has value [{}]:",
								regraField.getArquivoInputColsDef().getNameColumn(),
								value);

						if (!BigDecimal.ZERO.equals(value)) {
							if (BigDecimal.ZERO.equals(result)) {
								result = value;
							}

							LOGGER.info("Result value [{}]:", result);

							for (RegraValor regraValor : regraOperation.getRegraValors()) {
								value = regraValor.getValor();

								LOGGER.info("Field value for RegraValor has value [{}]:", value);

								result = executeOperation(regraOperation.getTpOperation(), value, result);
							}
						}
					}
				}
			}

			LOGGER.info("Final result after all RegraOperations value is [{}]:", result);

			for (RegraResult regraResult : regraUi.getRegraResults()) {
				arquivoInputColsDefUi = (ArquivoInputColsDefUi) isentoInputSheetColsUi.getArquivoInputColsDef();

				if (regraResult.getArquivoInputColsDef().getId().equals(arquivoInputColsDefUi.getId())) {

					LOGGER.info(
							"Sending calculated value [{}] to lancamento field [{}]",
							result,
							regraResult.getArquivoInputColsDef().getNameColumn());

					isentoService.setFieldValueAsBigDecimal(
							isentoInputSheetColsUi.getBeneficiarioIsentoColType(),
							beneficiarioIsentoUi,
							value);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
