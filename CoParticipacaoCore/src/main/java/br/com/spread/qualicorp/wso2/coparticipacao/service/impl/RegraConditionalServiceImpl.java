package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
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
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterColumnUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalOperationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalResultUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalValorUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalFieldService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalOperationService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalResultService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalValorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.util.RegraHelper;

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
	private RegraConditionalValorService regraConditionalValorService;

	@Autowired
	private RegraConditionalFieldService regraConditionalFieldService;

	@Autowired
	private RegraConditionalOperationService regraConditionalOperationService;

	@Autowired
	private RegraConditionalResultService regraConditionalResultService;

	@Autowired
	private RegraService regraService;

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

	public List<RegraConditionalUi> listByArquivoInputSheet(ArquivoInputSheetUi arquivoInputSheetUi)
			throws ServiceException {
		List<RegraConditionalUi> regraConditionalUis;
		List<RegraConditionalOperationUi> regraConditionalOperationUis;
		List<RegraConditionalResultUi> regraConditionalResultUis;

		try {
			LOGGER.info("BEGIN");

			regraConditionalUis = entityToUi(
					regraConditionalDao.listByArquivoInputSheetId(arquivoInputSheetUi.getId()));

			for (RegraConditionalUi regraConditionalUi : regraConditionalUis) {
				regraConditionalOperationUis = regraConditionalOperationService
						.listByRegraConditional(regraConditionalUi);
				regraConditionalResultUis = regraConditionalResultService.listByRegraConditional(regraConditionalUi);

				regraConditionalUi.getRegraConditionalOperations().addAll(regraConditionalOperationUis);
				regraConditionalUi.getRegraConditionalResults().addAll(regraConditionalResultUis);
			}

			LOGGER.info("END");
			return regraConditionalUis;
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

	public void applyRegras(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			for (RegraConditionalUi regraConditionalUi : coParticipacaoContext.getRegraConditionalUis()) {
				applyRegra(coParticipacaoContext, regraConditionalUi);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void applyRegra(CoParticipacaoContext coParticipacaoContext, RegraConditionalUi regraConditionalUi)
			throws ServiceException {
		List<RegraConditionalOperation> regraConditionalOperations;
		Object value;
		Object latestedValue;
		boolean result;
		OperationType operationType;
		ColDefType colDefType = null;
		boolean regraConditionalFieldFound;
		List<RegisterColumnUi> RegisterColumnUis;

		try {
			LOGGER.info("BEGIN");

			result = false;
			regraConditionalOperations = regraConditionalUi.getRegraConditionalOperations();
			latestedValue = null;
			RegisterColumnUis = coParticipacaoContext.getRegisterColumns();

			LOGGER.info("Using Regra [{}]:", regraConditionalUi.getNameRegra());

			for (RegraConditionalOperation regraConditionalOperation : regraConditionalOperations) {
				operationType = regraConditionalOperation.getTpOperation();

				LOGGER.info("RegraOperation to use with this Regra is [{}]:", operationType.getDescription());

				regraConditionalFieldFound = false;

				for (RegraConditionalField regraConditionalField : regraConditionalOperation
						.getRegraConditionalFields()) {

					for (RegisterColumnUi RegisterColumnUi : RegisterColumnUis) {
						colDefType = RegisterColumnUi.getType();

						if (regraConditionalField.getRegisterColumn().getId()
								.equals(RegisterColumnUi.getId())) {
							LOGGER.info(
									"Applying regra [{}] to field [{}]:",
									regraConditionalUi.getNameRegra(),
									regraConditionalField.getRegisterColumn().getNameColumn());

							regraConditionalFieldFound = true;

							value = RegraHelper
									.getFieldValue(coParticipacaoContext, RegisterColumnUi);

							LOGGER.info(
									"Field [{}] has value [{}]:",
									regraConditionalField.getRegisterColumn().getNameColumn(),
									value);

							if (latestedValue == null) {
								latestedValue = value;
							} else {
								result = executeOperation(operationType, colDefType, latestedValue, value);

								latestedValue = value;
							}

							break;
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
					for (RegraUi regraUi : coParticipacaoContext.getRegraUis()) {

						if (regraConditionalResult.getRegraExecution().getId().equals(regraUi.getId())) {
							LOGGER.info(
									"Executing Regra [{}]:",
									regraConditionalResult.getRegraExecution().getNameRegra());
							regraService.applyRegra(coParticipacaoContext, regraUi);
							break;
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

	private void executeRegraResult(CoParticipacaoContext coParticipacaoContext, RegraConditionalUi regraConditionalUi)
			throws ServiceException {
		RegisterColumnUi regraColumn;
		List<RegisterColumnUi> RegisterColumnUis;

		try {
			LOGGER.info("BEGIN");

			RegisterColumnUis = coParticipacaoContext.getRegisterColumns();

			for (RegraConditionalResult regraConditionalResult : regraConditionalUi.getRegraConditionalResults()) {

				LOGGER.info("Executing Regra [{}]:", regraConditionalResult.getRegraExecution().getNameRegra());

				for (RegraResult regraResult : regraConditionalResult.getRegraExecution().getRegraResults()) {
					regraColumn = (RegisterColumnUi) regraResult.getRegisterColumn();

					for (RegisterColumnUi RegisterColumnUi : RegisterColumnUis) {
						if (RegisterColumnUi.getId().equals(regraColumn.getId())) {
							regraService.applyRegra(
									coParticipacaoContext,
									(RegraUi) regraConditionalResult.getRegraExecution());
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
}
