package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import br.com.spread.qualicorp.webservice.coparticipacao.CoParticipacaoInfo;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestination;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestinationColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputLancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ParameterUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.io.CsvProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.io.FixedLengthProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.io.SpreadsheetProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.io.impl.ProcessorListener;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputColsDefService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.CoParticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputDependenteIsentoColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputLancamentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputTitularIsentoColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputTitularService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.MecsasService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ParameterService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.UserService;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
@Transactional(value = "jpaTransactionManager")
public class CoParticipacaoServiceImpl implements CoParticipacaoService {

	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoServiceImpl.class);

	@Autowired
	private ArquivoInputService arquivoInputService;

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private ArquivoInputColsDefService arquivoInputColsDefService;

	@Autowired
	private FixedLengthProcessorService fixedLengthProcessorService;

	@Autowired
	private MecsasService mecsasService;

	@Autowired
	private BeneficiarioColsService beneficiarioColsService;

	@Autowired
	private CsvProcessorService csvProcessorService;

	@Autowired
	private SpreadsheetProcessorService spreadsheetProcessorService;

	@Autowired
	private InputTitularService inputTitularService;

	@Autowired
	private UserService userService;

	@Autowired
	private TitularService titularService;

	@Autowired
	private DependenteService dependenteService;

	@Autowired
	private InputLancamentoService inputLancamentoService;

	@Autowired
	private RegraService regraService;

	@Autowired
	private RegraConditionalService regraConditionalService;

	@Autowired
	private ParameterService parameterService;

	@Autowired
	private InputTitularIsentoColsService inputTitularIsentoColsService;

	@Autowired
	private InputDependenteIsentoColsService inputDependenteIsentoColsService;

	@Autowired
	private IsentoService isentoService;

	@Autowired
	private EmpresaService empresaService;

	private static final Long USER_ADMIN_ID = 1l;

	@Transactional(propagation = Propagation.REQUIRED)
	public CoParticipacaoInfo processFile(String fileName, String filePath) throws ServiceException {
		CoParticipacaoInfo coParticipacaoInfo;
		CoParticipacaoContext coParticipacaoContext;
		String tmp;
		StopWatch stopWatch;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Receiving file [{}] to process:", fileName);

			// PAra marcar o tempo que levou para fazer a tarefa:
			stopWatch = new StopWatch();
			stopWatch.start();

			coParticipacaoInfo = new CoParticipacaoInfo();
			coParticipacaoInfo.setFileName(fileName);
			coParticipacaoInfo.setFilePath(filePath);

			coParticipacaoContext = createCoParticipacaoContext(fileName);

			if (!coParticipacaoContext.getArquivoInputColsDefUis().isEmpty()) {
				tmp = StringUtils.replaceAll(filePath, "input", "output");

				loadFileInputData(tmp, coParticipacaoContext);
			} else {
				throw new ServiceException("O arquivo [%s] não possui colunas definidas.", fileName);
			}

			stopWatch.stop();

			LOGGER.info("CoParticipacao task complete with [{}] min:", stopWatch.getTotalTimeSeconds() / 60.0);
			LOGGER.info("END");
			return coParticipacaoInfo;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private CoParticipacaoContext createCoParticipacaoContext(String fileName) throws ServiceException {
		CoParticipacaoContext coParticipacaoContext = null;
		List<ArquivoInputColsDefUi> arquivoInputColsDefUis;

		try {
			LOGGER.info("BEGIN");

			coParticipacaoContext = arquivoInputService.findByName(fileName);

			if (coParticipacaoContext == null) {
				throw new ServiceException("O arquivo [%s] não é cadastrado em ArquivoInput no sistema.", fileName);
			} else {
				coParticipacaoContext.setFileName(fileName);

				arquivoInputColsDefUis = arquivoInputColsDefService.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi().getId());

				coParticipacaoContext.setArquivoInputColsDefUis(arquivoInputColsDefUis);
			}

			LOGGER.info("END");
			return coParticipacaoContext;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void loadFileInputData(String filePath, CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		FileInputStream fileInputStream;
		ArquivoInputUi arquivoInputUi;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Loading file [{}]:", filePath);

			fileInputStream = new FileInputStream(filePath);

			coParticipacaoContext.setFileInputStream(fileInputStream);

			loadDefaultDefinitions(coParticipacaoContext);

			arquivoInputUi = coParticipacaoContext.getArquivoInputUi();

			if (UseType.FATUCOPA.equals(arquivoInputUi.getUseType())) {
				loadLancamentoDefinitions(coParticipacaoContext);

				if (ArquivoType.FIXED_LENGTH.equals(arquivoInputUi.getArquivoType())) {
					fixedLengthProcessorService.readInputStream(coParticipacaoContext, (ProcessorListener) lancamentoService);
				} else if (ArquivoType.CSV.equals(arquivoInputUi.getArquivoType())) {
					csvProcessorService.readInputStream(coParticipacaoContext, (ProcessorListener) lancamentoService);
				} else if (ArquivoType.SPREADSHEET.equals(arquivoInputUi.getArquivoType())) {
					spreadsheetProcessorService.readInputStream(coParticipacaoContext, (ProcessorListener) lancamentoService);
				}

			} else if (UseType.MECSAS.equals(arquivoInputUi.getUseType())) {
				loadMecsasDefinitions(coParticipacaoContext);

				if (ArquivoType.FIXED_LENGTH.equals(arquivoInputUi.getArquivoType())) {
					fixedLengthProcessorService.readInputStream(coParticipacaoContext, (ProcessorListener) mecsasService);
				} else if (ArquivoType.CSV.equals(arquivoInputUi.getArquivoType())) {
					csvProcessorService.readInputStream(coParticipacaoContext, (ProcessorListener) mecsasService);
				} else if (ArquivoType.SPREADSHEET.equals(arquivoInputUi.getArquivoType())) {
					spreadsheetProcessorService.readInputStream(coParticipacaoContext, (ProcessorListener) mecsasService);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void loadDefaultDefinitions(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		UserUi userUi;
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		EmpresaUi empresaUi;
		List<ParameterUi> parameterUis;

		try {
			LOGGER.info("BEGIN");

			userUi = userService.findById(USER_ADMIN_ID);

			coParticipacaoContext.setUser(userUi);

			empresaUi = (EmpresaUi) coParticipacaoContext.getArquivoInputUi().getContrato().getEmpresa();
			empresaUi = empresaService.findById(empresaUi.getId());

			// Carregando todos os beneficiários existentes da empresa:
			titularUis = titularService.listByEmpresaId(empresaUi.getId());
			dependenteUis = dependenteService.listByEmpresaId(empresaUi.getId());

			coParticipacaoContext.setEmpresaUi(empresaUi);
			coParticipacaoContext.setTitularUis(titularUis);
			coParticipacaoContext.setDependenteUis(dependenteUis);

			parameterUis = parameterService.listByEmpresaId(empresaUi.getId());
			coParticipacaoContext.getParameterUis().addAll(parameterUis);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	private void loadMecsasDefinitions(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		List<InputTitularUi> inputTitularUis;
		List<InputTitularIsentoColsUi> inputTitularIsentoColsUis;
		List<InputDependenteIsentoColsUi> inputDependenteIsentoColsUis;
		List<BeneficiarioColsUi> beneficiarioColsUis;

		try {
			LOGGER.info("BEGIN");

			beneficiarioColsUis = beneficiarioColsService.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi());

			inputTitularUis = inputTitularService.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi().getId());

			if (beneficiarioColsUis.isEmpty()) {
				LOGGER.info(
						"Didn't found mappings at BeneficiarioCols for ArquivoInput[{}]:",
						coParticipacaoContext.getArquivoInputUi().getNameArquivoRegexp());

				throw new ServiceException(
						"Didn't found mappings at BeneficiarioCols for ArquivoInput[{}]:",
						coParticipacaoContext.getArquivoInputUi().getNameArquivoRegexp());
			}

			if (inputTitularUis.isEmpty()) {
				LOGGER.info("Didn't found mappings at InputTitular for ArquivoInput[{}]:", coParticipacaoContext.getArquivoInputUi().getNameArquivoRegexp());
			}

			coParticipacaoContext.setBeneficiarioColsUis(beneficiarioColsUis);

			inputTitularIsentoColsUis = inputTitularIsentoColsService.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi());
			inputDependenteIsentoColsUis = inputDependenteIsentoColsService.listByArquivoInput(coParticipacaoContext.getArquivoInputUi());

			coParticipacaoContext.setInputTitularIsentoColsUis(inputTitularIsentoColsUis);
			coParticipacaoContext.setInputDependenteIsentoColsUis(inputDependenteIsentoColsUis);

			coParticipacaoContext.setInputTitularUis(inputTitularUis);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	private void loadLancamentoDefinitions(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		List<InputLancamentoUi> inputLancamentoUis;
		List<RegraUi> regraUis;
		List<RegraConditionalUi> regraConditionalUis; 

		try {
			LOGGER.info("BEGIN");

			inputLancamentoUis = inputLancamentoService.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi().getId());

			coParticipacaoContext.getInputLancamentoUis().addAll(inputLancamentoUis);

			// Caregando as regras para o arquivo:
			regraUis = regraService.listRegrasByArquivoInputId(coParticipacaoContext.getArquivoInputUi().getId());
			regraConditionalUis = regraConditionalService.listRegrasByArquivoInputId(coParticipacaoContext.getArquivoInputUi().getId());

			LOGGER.info("Loading [{}] Regras to use with ArquivoInput [{}]:", regraUis.size(), coParticipacaoContext.getArquivoInputUi().getDescrArquivo());
			LOGGER.info(
					"Loading [{}] RegraCondicionais to use with ArquivoInput [{}]:",
					regraConditionalUis.size(),
					coParticipacaoContext.getArquivoInputUi().getDescrArquivo());

			coParticipacaoContext.getRegraUis().addAll(regraUis);
			coParticipacaoContext.getRegraConditionalUis().addAll(regraConditionalUis);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
