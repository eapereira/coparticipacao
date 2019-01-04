package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DependenteBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DesconhecidoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.LancamentoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.TitularBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.io.ProcessorListener;
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
		LancamentoUi lancamentoUi;
		TitularUi titularUi;
		DependenteUi dependenteUi;
		LancamentoDetailUi lancamentoDetailUi;

		try {
			LOGGER.info("BEGIN");

			// Aplicamdo regras do arquivo se existirem:
			regraService.applyRegras(coParticipacaoContext);

			lancamentoDetailUi = lancamentoDetailService.create(coParticipacaoContext);

			if (!coParticipacaoContext.isFirstLineProcecessed()) {
				LOGGER.info("Doing tasks to be performed just after we had read the first line:");
				coParticipacaoContext.setFirstLineProcecessed(true);

				beforeProcess(coParticipacaoContext);
			}

			if (beneficiarioService.validateBeneficiario(coParticipacaoContext, lancamentoDetailUi)) {
				lancamentoUi = createLancamento(coParticipacaoContext, lancamentoDetailUi);

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

				lancamentoUi.setUserAltered(coParticipacaoContext.getUser());
				lancamentoUi.setUserCreated(coParticipacaoContext.getUser());
				lancamentoUi.setAltered(LocalDateTime.now());
				lancamentoUi.setCreated(LocalDateTime.now());

				coParticipacaoContext.addLancamento(lancamentoUi);
			} else {
				desconhecidoService.createDesconhecido(coParticipacaoContext, lancamentoDetailUi);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private LancamentoUi createLancamento(
			CoParticipacaoContext coParticipacaoContext,
			LancamentoDetailUi lancamentoDetailUi) throws ServiceException {
		LancamentoUi lancamentoUi;
		try {
			LOGGER.info("BEGIN");

			lancamentoUi = new LancamentoUi();
			lancamentoUi.setMes(lancamentoDetailUi.getMes());
			lancamentoUi.setAno(lancamentoDetailUi.getAno());
			lancamentoUi.setContrato(coParticipacaoContext.getContratoUi());
			lancamentoUi.setDependente(lancamentoDetailUi.getDependenteUi());
			lancamentoUi.setTitular(lancamentoDetailUi.getTitularUi());

			if (lancamentoUi.getTitular().getContrato() == null) {
				lancamentoUi.getTitular().setContrato(coParticipacaoContext.getContratoUi());
			}

			lancamentoUi.setValorPrincipal(lancamentoDetailUi.getValorPrincipal());
			lancamentoUi.setValorRembolso(lancamentoDetailUi.getValorReembolso());
			lancamentoUi.setValorParticipacao(lancamentoDetailUi.getValorParticipacao());
			lancamentoUi.setDtUtilizacao(lancamentoDetailUi.getDtUtilizacao());
			lancamentoUi.setDescrUtilizacao(lancamentoDetailUi.getDescrUtilizacao());

			lancamentoUi.setUserCreated(coParticipacaoContext.getUser());

			LOGGER.info("END");
			return lancamentoUi;
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

	public void afterProcess(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Process ending and sending data to database:");

			if (coParticipacaoContext.getBunker().getLancamentoUis().isEmpty()) {
				LOGGER.info("There is no valid lancamentos in this process:");
			} else {
				LOGGER.info("Creating Beneficiarios that we dont have at database:");

				titularBatchService.saveBatch(coParticipacaoContext, coParticipacaoContext.getBunker().getTitularUis());
				dependenteBatchService
						.saveBatch(coParticipacaoContext, coParticipacaoContext.getBunker().getDependenteUis());

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
		int mes = NumberUtils.INTEGER_ZERO;
		int ano = NumberUtils.INTEGER_ZERO;
		LancamentoDetailUi lancamentoDetailUi;

		try {
			LOGGER.info("BEGIN");

			if (coParticipacaoContext.isFirstLineProcecessed()) {
				arquivoInputUi = coParticipacaoContext.getArquivoInputUi();

				lancamentoDetailUi = coParticipacaoContext.getLancamentoDetailUi();

				ano = lancamentoDetailUi.getAno();
				mes = lancamentoDetailUi.getMes();

				LOGGER.info(
						"Starting process [{}] to load benefiets from assets file:",
						arquivoInputUi.getUseType().getDescription());

				LOGGER.info("Cleaning all previous data from year[{}] and month[{}]:", ano, mes);

				lancamentoService.deleteByMesAndAno(coParticipacaoContext.getContratoUi(), mes, ano);

				desconhecidoService.deleteByContrato(coParticipacaoContext.getContratoUi());
			}

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

	public void generateOutputFileWithoutFatucopa(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			writeOutputFiles(coParticipacaoContext);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
