package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DependenteBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DesconhecidoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.LancamentoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.TitularBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.BeneficiarioNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.impl.ProcessorListener;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.FatucopaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
@Transactional(value = AbstractService.JDBC_TX)
public class FatucopaServiceImpl implements FatucopaService, ProcessorListener {

	private static final Logger LOGGER = LogManager.getLogger(FatucopaServiceImpl.class);

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private LancamentoDetailService lancamentoDetailService;

	@Autowired
	private DesconhecidoService desconhecidoService;

	@Autowired
	private RegraService regraService;

	@Autowired
	private ArquivoOutputService arquivoOutputService;

	@Autowired
	private BeneficiarioService beneficiarioService;

	@Autowired
	private TitularBatchService titularBatchService;

	@Autowired
	private DependenteBatchService dependenteBatchService;

	@Autowired
	private LancamentoBatchService lancamentoBatchService;

	@Autowired
	private DesconhecidoBatchService desconhecidoBatchService;

	public void processLine(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		Object value;
		List<LancamentoInputColsUi> lancamentoInputColsUis;
		List<ArquivoInputColsDefUi> arquivoInputColsDefUis;
		LancamentoUi lancamentoUi;
		TitularUi titularUi;
		DependenteUi dependenteUi;

		try {
			LOGGER.info("BEGIN");

			lancamentoUi = new LancamentoUi();

			lancamentoInputColsUis = coParticipacaoContext.getLancamentoInputColsUis();
			arquivoInputColsDefUis = coParticipacaoContext.getArquivoInputColsDefUis();

			if (!lancamentoInputColsUis.isEmpty()) {

				// Processando uma linha do arquivo:
				for (ArquivoInputColsDefUi arquivoInputColsDefUi : arquivoInputColsDefUis) {
					value = coParticipacaoContext.getMapLine().get(arquivoInputColsDefUi.getNameColumn());

					LOGGER.debug("Column [{}] with value [{}]:", arquivoInputColsDefUi.getNameColumn(), value);

					for (LancamentoInputColsUi lancamentoInputColsUi : lancamentoInputColsUis) {
						if (lancamentoInputColsUi.getArquivoInputColsDef().getId()
								.equals(arquivoInputColsDefUi.getId())) {
							storeInputValue(
									coParticipacaoContext,
									lancamentoUi,
									lancamentoInputColsUi.getLancamentoColType(),
									value);

							break;
						}
					}

					lancamentoDetailService.storeLancamentoDetail(
							lancamentoUi,
							arquivoInputColsDefUi,
							value,
							coParticipacaoContext.getUser());
				}
			} else {
				LOGGER.info(
						"There's no registers in LancamentoInputCols mapping to ArquivoInput, so we can read and store Lancamentos:");

				throw new ServiceException(
						"There's no registers in LancamentoInputCols mapping to ArquivoInput, so we can read and store Lancamentos:");
			}

			// Aplicamdo regras do arquivo se existirem:
			regraService.applyRegras(coParticipacaoContext, lancamentoUi);

			if (beneficiarioService.validateBeneficiario(coParticipacaoContext, lancamentoUi)) {
				if (lancamentoUi.getTitular() != null) {
					titularUi = (TitularUi) lancamentoUi.getTitular();
					LOGGER.info(
							"Lancamento using Titular [{}] with Matricula [{}] and CPF [{}]:",
							titularUi.getNameTitular(),
							titularUi.getMatricula(),
							titularUi.getCpf());
				}

				if (lancamentoUi.getDependente() != null) {
					dependenteUi = (DependenteUi) lancamentoUi.getDependente();

					LOGGER.info(
							"Lancamento using Dependente [{}] with Matricula [{}] and CPF [{}]:",
							dependenteUi.getNameDependente(),
							dependenteUi.getMatricula(),
							dependenteUi.getCpf());
				}

				if (lancamentoUi.getMes() == null) {
					lancamentoUi.setMes(coParticipacaoContext.getMes());
				}

				if (lancamentoUi.getAno() == null) {
					lancamentoUi.setAno(coParticipacaoContext.getAno());
				}

				lancamentoUi.setUserAltered(coParticipacaoContext.getUser());
				lancamentoUi.setUserCreated(coParticipacaoContext.getUser());
				lancamentoUi.setAltered(LocalDateTime.now());
				lancamentoUi.setCreated(LocalDateTime.now());

				lancamentoDetailService.showLancamentoDetailInfo(lancamentoUi);

				coParticipacaoContext.addLancamento(lancamentoUi);
			} else {
				desconhecidoService.createDesconhecido(coParticipacaoContext, lancamentoUi);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void storeInputValue(
			CoParticipacaoContext coParticipacaoContext,
			LancamentoUi lancamentoUi,
			LancamentoColType lancamentoColType,
			Object value) throws ServiceException, BeneficiarioNotFoundException {
		try {
			LOGGER.info("BEGIN");

			if (lancamentoColType != null) {
				LOGGER.debug(
						"Reading Lancamento field [{}] with value [{}]:",
						lancamentoColType.getDescription(),
						value);

				if (LancamentoColType.ID_DEPENDENTE.equals(lancamentoColType)) {
					// findBeneficiario(lancamentoUi, (Long) value,
					// coParticipacaoContext);
				} else if (LancamentoColType.ID_CONTRATO.equals(lancamentoColType)) {
					lancamentoUi.setContrato(coParticipacaoContext.getContratoUi());
				} else if (LancamentoColType.CD_MES.equals(lancamentoColType)) {
					lancamentoUi.setMes((Integer) value);
				} else if (LancamentoColType.CD_ANO.equals(lancamentoColType)) {
					lancamentoUi.setAno((Integer) value);
				} else if (LancamentoColType.VL_PRINCIPAL.equals(lancamentoColType)) {
					lancamentoUi.setValorPrincipal((BigDecimal) value);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public boolean validateLine(String line, CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		if (coParticipacaoContext.getArquivoInputUi().getDefaultLineLength() != null) {
			LOGGER.debug(
					"Validating Lancamento line length [{}] expected to be [{}]:",
					line.length(),
					coParticipacaoContext.getArquivoInputUi().getDefaultLineLength());

			if (line.length() == coParticipacaoContext.getArquivoInputUi().getDefaultLineLength()) {
				return true;
			}
		} else {
			return true;
		}

		return false;
	}

	@Transactional(value = AbstractService.JDBC_TX, propagation = Propagation.REQUIRED)
	public void afterProcess(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Process ending and sending data to database:");

			if (coParticipacaoContext.getBunker().getLancamentoUis().isEmpty()) {
				LOGGER.info("There is no valid lancamentos in this process:");
			} else {
				LOGGER.info("Creating Beneficiarios that we dont have at database:");

				titularBatchService.saveBatch(coParticipacaoContext.getBunker().getTitularUis());
				dependenteBatchService.saveBatch(coParticipacaoContext.getBunker().getDependenteUis());

				LOGGER.info("Storing lancamentos data:");
				lancamentoBatchService.saveBatch(coParticipacaoContext.getBunker().getLancamentoUis());
			}

			LOGGER.info("Storing desconhecidos data:");
			desconhecidoBatchService.saveBatch(coParticipacaoContext.getBunker().getDesconhecidoUis());

			writeOutputFiles(coParticipacaoContext);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void beforeProcess(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		ArquivoInputUi arquivoInputUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputUi = coParticipacaoContext.getArquivoInputUi();

			LOGGER.info(
					"Starting process [{}] to load benefiets from assets file:",
					arquivoInputUi.getUseType().getDescription());

			LOGGER.info(
					"Cleaning all previous data from year[{}] and month[{}]:",
					coParticipacaoContext.getAno(),
					coParticipacaoContext.getMes());

			lancamentoService.deleteByMesAndAno(
					coParticipacaoContext.getContratoUi(),
					coParticipacaoContext.getMes(),
					coParticipacaoContext.getAno());

			desconhecidoService.deleteByMesAndAno(
					coParticipacaoContext.getContratoUi(),
					coParticipacaoContext.getMes(),
					coParticipacaoContext.getAno());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void writeOutputFiles(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			desconhecidoService.writeDesconhecidosFile(coParticipacaoContext);

			arquivoOutputService.writeOutputFile(coParticipacaoContext);

			LOGGER.info("Process finished with success, all output files created:");
			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
