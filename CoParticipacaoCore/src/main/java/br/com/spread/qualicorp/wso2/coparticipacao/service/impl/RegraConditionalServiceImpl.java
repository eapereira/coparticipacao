package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioIsentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.OperationType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditional;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalResult;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalValor;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraResult;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraConditionalEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraConditionalUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalValorUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalValorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraConditionalServiceImpl
		extends AbstractServiceImpl<RegraConditionalUi, RegraConditionalEntity, RegraConditional>
		implements RegraConditionalService {

	private static final Logger LOGGER = LogManager.getLogger(RegraConditionalServiceImpl.class);

	@Autowired
	private RegraConditionalDao regraConditionalDao;

	@Autowired
	private RegraConditionalUiMapper uiMapper;

	@Autowired
	private RegraConditionalEntityMapper entityMapper;

	@Autowired
	private LancamentoDetailService lancamentoDetailService;

	@Autowired
	private RegraConditionalValorService regraConditionalValorService;

	@Autowired
	private RegraService regraService;

	@Autowired
	private IsentoService isentoService;

	public RegraConditionalServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected RegraConditionalUi createUi() {
		return new RegraConditionalUi();
	}

	@Override
	protected RegraConditionalEntity createEntity() {
		return new RegraConditionalEntity();
	}

	@Override
	protected AbstractDao<RegraConditionalEntity> getDao() {
		return regraConditionalDao;
	}

	@Override
	protected AbstractMapper<RegraConditional, RegraConditionalUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraConditional, RegraConditionalEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<RegraConditionalUi> listByArquivoInputId(ArquivoInputUi arquivoInputUi) throws ServiceException {
		List<RegraConditionalUi> regraConditionalUis;

		try {
			LOGGER.info("BEGIN");

			regraConditionalUis = entityToUi(regraConditionalDao.listByArquivoInput(arquivoInputUi.getId()));

			LOGGER.info("END");
			return regraConditionalUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void applyRegras(CoParticipacaoContext coParticipacaoContext, LancamentoDetailUi lancamentoDetailUi)
			throws ServiceException {
		List<LancamentoInputSheetCols> lancamentoInputSheetColss;

		try {
			LOGGER.info("BEGIN");

			lancamentoInputSheetColss = coParticipacaoContext
					.listLancamentoInputSheetColsBySheetId(coParticipacaoContext.getCurrentSheet());

			for (LancamentoInputSheetCols lancamentoInputSheetCols : lancamentoInputSheetColss) {
				for (RegraConditionalUi regraConditionalUi : coParticipacaoContext.getRegraConditionalUis()) {
					LOGGER.info(
							"Verifying RegraConditions for column [{}]:",
							lancamentoInputSheetCols.getArquivoInputSheetColsDef().getNameColumn());

					applyRegra(
							coParticipacaoContext,
							regraConditionalUi,
							lancamentoDetailUi,
							(LancamentoInputSheetColsUi) lancamentoInputSheetCols);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void applyRegra(
			CoParticipacaoContext coParticipacaoContext,
			RegraConditionalUi regraConditionalUi,
			LancamentoDetailUi lancamentoDetailUi,
			LancamentoInputSheetColsUi lancamentoInputSheetColsUi) throws ServiceException {
		List<RegraConditionalOperation> regraConditionalOperations;
		Object value;
		Object latestedValue;
		boolean result;
		OperationType operationType;
		ColDefType colDefType;
		boolean regraConditionalFieldFound;

		try {
			LOGGER.info("BEGIN");

			result = false;
			regraConditionalOperations = regraConditionalUi.getRegraConditionalOperations();
			latestedValue = null;
			colDefType = lancamentoInputSheetColsUi.getArquivoInputSheetColsDef().getType();

			LOGGER.info("Using Regra [{}]:", regraConditionalUi.getNameRegra());

			for (RegraConditionalOperation regraConditionalOperation : regraConditionalOperations) {

				operationType = regraConditionalOperation.getTpOperation();

				LOGGER.info("RegraOperation to use with this Regra is [{}]:", operationType.getDescription());

				regraConditionalFieldFound = false;

				for (RegraConditionalField regraConditionalField : regraConditionalOperation
						.getRegraConditionalFields()) {

					if (regraConditionalField.getArquivoInputSheetColsDef().getId()
							.equals(lancamentoInputSheetColsUi.getArquivoInputSheetColsDef().getId())) {
						LOGGER.info(
								"Applying regra [{}] to field [{}]:",
								regraConditionalUi.getNameRegra(),
								regraConditionalField.getArquivoInputSheetColsDef().getNameColumn());

						regraConditionalFieldFound = true;

						value = lancamentoDetailService
								.getFieldValue(lancamentoDetailUi, lancamentoInputSheetColsUi.getLancamentoColType());

						LOGGER.info(
								"Field [{}] has value [{}]:",
								regraConditionalField.getArquivoInputSheetColsDef().getNameColumn(),
								value);

						if (latestedValue == null) {
							latestedValue = value;
						} else {
							result = executeOperation(operationType, colDefType, latestedValue, value);

							latestedValue = value;
						}
					}
				}

				if (regraConditionalFieldFound) {
					LOGGER.info("Result value [{}]:", result);

					for (RegraConditionalValor regraConditionalValor : regraConditionalOperation
							.getRegraConditionalValors()) {
						value = regraConditionalValorService
								.getValor((RegraConditionalValorUi) regraConditionalValor, colDefType);

						LOGGER.info("Field value for RegraConditionalValor has value [{}]:", value);

						if (latestedValue == null) {
							latestedValue = value;
						} else {
							result = executeOperation(operationType, colDefType, latestedValue, value);

							latestedValue = value;
						}
					}
				}
			}

			LOGGER.info("Final result after all RegraOperations value is [{}]:", result);

			if (result) {
				for (RegraConditionalResult regraConditionalResult : regraConditionalUi.getRegraConditionalResults()) {

					LOGGER.info("Executing Regra [{}]:", regraConditionalResult.getRegraExecution().getNameRegra());

					executeRegraResult(coParticipacaoContext, regraConditionalUi, lancamentoDetailUi);

				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void executeRegraResult(
			CoParticipacaoContext coParticipacaoContext,
			RegraConditionalUi regraConditionalUi,
			LancamentoDetailUi lancamentoDetailUi) throws ServiceException {
		LancamentoInputSheetColsUi lancamentoInputSheetColsUi;

		try {
			LOGGER.info("BEGIN");

			for (RegraConditionalResult regraConditionalResult : regraConditionalUi.getRegraConditionalResults()) {

				LOGGER.info("Executing Regra [{}]:", regraConditionalResult.getRegraExecution().getNameRegra());

				for (RegraResult regraResult : regraConditionalResult.getRegraExecution().getRegraResults()) {
					lancamentoInputSheetColsUi = lancamentoDetailService.findByArquivoInputSheetColsDefId(
							coParticipacaoContext,
							(ArquivoInputSheetColsDefUi) regraResult.getArquivoInputSheetColsDef());

					regraService.applyRegra(
							coParticipacaoContext,
							(RegraUi) regraConditionalResult.getRegraExecution(),
							lancamentoDetailUi,
							lancamentoInputSheetColsUi);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean executeOperation(OperationType operationType, ColDefType colDefType, Object valueA, Object valueB)
			throws ServiceException {
		boolean valueResult = false;

		try {
			LOGGER.info("BEGIN");

			if (OperationType.EQUALS.equals(operationType)) {
				LOGGER.info("Executing LancamentoDetail[{}] equals RegraConditionalValue[{}]", valueA, valueB);

				if (Objects.equals(valueA, valueB)) {
					valueResult = true;
				}
			} else if (OperationType.NOT_EQUALS.equals(operationType)) {
				LOGGER.info("Executing [{}] not equals [{}]", valueA, valueB);

				if (!Objects.equals(valueA, valueB)) {
					valueResult = true;
				}
			}

			LOGGER.info("END");
			return valueResult;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void applyRegras(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			List<IsentoInputSheetCols> isentoInputSheetColss) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			for (RegraConditionalUi regraConditionalUi : coParticipacaoContext.getRegraConditionalUis()) {
				applyRegra(coParticipacaoContext, regraConditionalUi, isentoInputSheetColss, beneficiarioIsentoUi);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void applyRegra(
			CoParticipacaoContext coParticipacaoContext,
			RegraConditionalUi regraConditionalUi,
			List<IsentoInputSheetCols> isentoInputSheetColss,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException {
		List<RegraConditionalOperation> regraConditionalOperations;
		Object value;
		Object latestedValue;
		boolean result;
		OperationType operationType;
		ColDefType colDefType;
		boolean regraConditionalFieldFound;
		BeneficiarioIsentoColType beneficiarioIsentoColType;
		ArquivoInputSheetColsDefUi arquivoInputColsDefUi;

		try {
			LOGGER.info("BEGIN");

			result = false;
			regraConditionalOperations = regraConditionalUi.getRegraConditionalOperations();
			latestedValue = null;

			for (IsentoInputSheetCols isentoInputSheetCols : isentoInputSheetColss) {
				beneficiarioIsentoColType = isentoInputSheetCols.getBeneficiarioIsentoColType();
				arquivoInputColsDefUi = (ArquivoInputSheetColsDefUi) isentoInputSheetCols.getArquivoInputSheetColsDef();
				colDefType = arquivoInputColsDefUi.getType();

				LOGGER.info("Using Regra [{}]:", regraConditionalUi.getNameRegra());

				for (RegraConditionalOperation regraConditionalOperation : regraConditionalOperations) {

					operationType = regraConditionalOperation.getTpOperation();

					LOGGER.info("RegraOperation to use with this Regra is [{}]:", operationType.getDescription());

					regraConditionalFieldFound = false;

					for (RegraConditionalField regraConditionalField : regraConditionalOperation
							.getRegraConditionalFields()) {

						if (regraConditionalField.getArquivoInputSheetColsDef().getId()
								.equals(arquivoInputColsDefUi.getId())) {
							LOGGER.info(
									"Applying regra [{}] to field [{}]:",
									regraConditionalUi.getNameRegra(),
									regraConditionalField.getArquivoInputSheetColsDef().getNameColumn());

							regraConditionalFieldFound = true;

							value = isentoService
									.getFieldValueAsBigDecimal(beneficiarioIsentoColType, beneficiarioIsentoUi);

							LOGGER.info(
									"Field [{}] has value [{}]:",
									regraConditionalField.getArquivoInputSheetColsDef().getNameColumn(),
									value);

							if (latestedValue == null) {
								latestedValue = value;
							} else {
								result = executeOperation(operationType, colDefType, latestedValue, value);

								latestedValue = value;
							}
						}
					}

					if (regraConditionalFieldFound) {
						LOGGER.info("Result value [{}]:", result);

						for (RegraConditionalValor regraConditionalValor : regraConditionalOperation
								.getRegraConditionalValors()) {
							value = regraConditionalValorService
									.getValor((RegraConditionalValorUi) regraConditionalValor, colDefType);

							LOGGER.info("Field value for RegraConditionalValor has value [{}]:", value);

							if (latestedValue == null) {
								latestedValue = value;
							} else {
								result = executeOperation(operationType, colDefType, latestedValue, value);

								latestedValue = value;
							}
						}
					}
				}
			}

			LOGGER.info("Final result after all RegraOperations value is [{}]:", result);

			if (result) {
				for (RegraConditionalResult regraConditionalResult : regraConditionalUi.getRegraConditionalResults()) {

					LOGGER.info("Executing Regra [{}]:", regraConditionalResult.getRegraExecution().getNameRegra());

					executeRegraResult(
							coParticipacaoContext,
							regraConditionalUi,
							isentoInputSheetColss,
							beneficiarioIsentoUi);

				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void executeRegraResult(
			CoParticipacaoContext coParticipacaoContext,
			RegraConditionalUi regraConditionalUi,
			List<IsentoInputSheetCols> isentoInputSheetColss,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException {
		ArquivoInputSheetColsDefUi arquivoInputColsDefUi;

		try {
			LOGGER.info("BEGIN");

			for (RegraConditionalResult regraConditionalResult : regraConditionalUi.getRegraConditionalResults()) {

				LOGGER.info("Executing Regra [{}]:", regraConditionalResult.getRegraExecution().getNameRegra());

				for (RegraResult regraResult : regraConditionalResult.getRegraExecution().getRegraResults()) {
					arquivoInputColsDefUi = (ArquivoInputSheetColsDefUi) regraResult.getArquivoInputSheetColsDef();

					for (IsentoInputSheetCols isentoInputSheetCols : isentoInputSheetColss) {
						if (isentoInputSheetCols.getArquivoInputSheetColsDef().getId()
								.equals(arquivoInputColsDefUi.getId())) {
							regraService.applyRegra(
									coParticipacaoContext,
									(RegraUi) regraConditionalResult.getRegraExecution(),
									(IsentoInputSheetColsUi) isentoInputSheetCols,
									beneficiarioIsentoUi);
						}
					}
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<RegraConditionalUi> listByArquivoInputSheet(ArquivoInputSheetUi arquivoInputSheetUi)
			throws ServiceException {
		List<RegraConditionalUi> regraConditionalUis;

		try {
			LOGGER.info("BEGIN");

			regraConditionalUis = entityToUi(
					regraConditionalDao.listByArquivoInputSheetId(arquivoInputSheetUi.getId()));

			LOGGER.info("END");
			return regraConditionalUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
