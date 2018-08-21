package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.webservice.coparticipacao.CoParticipacaoInfo;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.CoparticipacaoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ParameterUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.io.CsvProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.io.FixedLengthProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.io.IsentoSpreadsheetProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.io.SpreadsheetProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.io.impl.ProcessorListener;
import br.com.spread.qualicorp.wso2.coparticipacao.search.DependenteByCpfAndNameMapKeyBuilder;
import br.com.spread.qualicorp.wso2.coparticipacao.search.DependenteByMatriculaAndNameMapKeyBuilder;
import br.com.spread.qualicorp.wso2.coparticipacao.search.PartitionMap;
import br.com.spread.qualicorp.wso2.coparticipacao.search.TitularByCpfAndNameMapKeyBuilder;
import br.com.spread.qualicorp.wso2.coparticipacao.search.TitularByMatriculaAndNameMapKeyBuilder;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputColsDefService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.CoParticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.FatucopaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoInputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoInputColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.MecsasService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.NaoLocalizadoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ParameterService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.UserService;
import br.com.spread.qualicorp.wso2.coparticipacao.util.StopWatchAdapter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
@Transactional(transactionManager = AbstractService.JDBC_TX)
public class CoParticipacaoServiceImpl implements CoParticipacaoService {

	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoServiceImpl.class);

	@Autowired
	private ArquivoInputService arquivoInputService;

	@Autowired
	private ArquivoInputColsDefService arquivoInputColsDefService;

	@Autowired
	private FixedLengthProcessorService fixedLengthProcessorService;

	@Qualifier("MecsasService")
	@Autowired
	private MecsasService mecsasService;

	@Autowired
	private BeneficiarioColsService beneficiarioColsService;

	@Autowired
	private CsvProcessorService csvProcessorService;

	@Qualifier("SpreadsheetProcessorService")
	@Autowired
	private SpreadsheetProcessorService spreadsheetProcessorService;

	@Autowired
	private UserService userService;

	@Autowired
	private TitularService titularService;

	@Autowired
	private DependenteService dependenteService;

	@Autowired
	private LancamentoInputColsService lancamentoInputColsService;

	@Autowired
	private RegraService regraService;

	@Autowired
	private RegraConditionalService regraConditionalService;

	@Autowired
	private ParameterService parameterService;

	@Autowired
	private IsentoService isentoService;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private IsentoInputSheetService isentoInputSheetService;

	@Qualifier("IsentoSpreadsheetProcessorService")
	@Autowired
	private IsentoSpreadsheetProcessorService isentoSpreadsheetProcessorService;

	@Qualifier("NaoLocalizadoService")
	@Autowired
	private NaoLocalizadoService naoLocalizadoService;

	@Autowired
	private CoparticipacaoJdbcDao coparticipacaoJdbcDao;

	@Autowired
	private FatucopaService fatucopaService;

	private static final Long USER_ADMIN_ID = 1l;

	public CoParticipacaoInfo processFile(String fileName, String filePath) throws ServiceException {
		CoParticipacaoInfo coParticipacaoInfo;
		CoParticipacaoContext coParticipacaoContext;
		String tmp;
		StopWatchAdapter stopWatch;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Receiving file [{}] to process:", fileName);

			// PAra marcar o tempo que levou para fazer a tarefa:
			stopWatch = new StopWatchAdapter();
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

			LOGGER.info("CoParticipacao task completed with [{}] min:", stopWatch.getTotalTimeMinutes());
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

				arquivoInputColsDefUis = arquivoInputColsDefService
						.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi().getId());

				coParticipacaoContext.setArquivoInputColsDefUis(arquivoInputColsDefUis);
			}

			LOGGER.info("END");
			return coParticipacaoContext;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void loadFileInputData(String filePath, CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
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
				changeThreadName(coParticipacaoContext, "FatuCopaService");

				loadLancamentoDefinitions(coParticipacaoContext);

				if (ArquivoType.FIXED_LENGTH.equals(arquivoInputUi.getArquivoType())) {
					fixedLengthProcessorService
							.readInputStream(coParticipacaoContext, (ProcessorListener) fatucopaService);
				} else if (ArquivoType.CSV.equals(arquivoInputUi.getArquivoType())) {
					csvProcessorService.readInputStream(coParticipacaoContext, (ProcessorListener) fatucopaService);
				} else if (ArquivoType.SPREADSHEET.equals(arquivoInputUi.getArquivoType())) {
					spreadsheetProcessorService
							.readInputStream(coParticipacaoContext, (ProcessorListener) fatucopaService);
				}

			} else if (UseType.MECSAS.equals(arquivoInputUi.getUseType())
					|| UseType.MECSAS2.equals(arquivoInputUi.getUseType())) {
				changeThreadName(coParticipacaoContext, "MecsasService");

				loadMecsasDefinitions(coParticipacaoContext);

				if (ArquivoType.FIXED_LENGTH.equals(arquivoInputUi.getArquivoType())) {
					fixedLengthProcessorService
							.readInputStream(coParticipacaoContext, (ProcessorListener) mecsasService);
				} else if (ArquivoType.CSV.equals(arquivoInputUi.getArquivoType())) {
					csvProcessorService.readInputStream(coParticipacaoContext, (ProcessorListener) mecsasService);
				} else if (ArquivoType.SPREADSHEET.equals(arquivoInputUi.getArquivoType())) {
					spreadsheetProcessorService
							.readInputStream(coParticipacaoContext, (ProcessorListener) mecsasService);
				}
			} else if (UseType.ISENTO.equals(arquivoInputUi.getUseType())) {
				changeThreadName(coParticipacaoContext, "IsentoService");

				loadIsentoDefinitions(coParticipacaoContext);

				if (ArquivoType.FIXED_LENGTH.equals(arquivoInputUi.getArquivoType())) {
					fixedLengthProcessorService
							.readInputStream(coParticipacaoContext, (ProcessorListener) isentoService);
				} else if (ArquivoType.CSV.equals(arquivoInputUi.getArquivoType())) {
					csvProcessorService.readInputStream(coParticipacaoContext, (ProcessorListener) isentoService);
				} else if (ArquivoType.SPREADSHEET.equals(arquivoInputUi.getArquivoType())) {
					isentoSpreadsheetProcessorService
							.readInputStream(coParticipacaoContext, (ProcessorListener) isentoService);
				}
			} else if (UseType.NAO_LOCALIZADO.equals(arquivoInputUi.getUseType())) {
				changeThreadName(coParticipacaoContext, "NaoLocalizadoService");

				spreadsheetProcessorService
						.readInputStream(coParticipacaoContext, (ProcessorListener) naoLocalizadoService);
			}

			changeThreadName(coParticipacaoContext, null);

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
		List<BeneficiarioColsUi> beneficiarioColsUis;
		PartitionMap<TitularUi> mapTitularUi;
		PartitionMap<DependenteUi> mapDependenteUi;

		try {
			LOGGER.info("BEGIN");

			userUi = userService.findById(USER_ADMIN_ID);

			coParticipacaoContext.setUser(userUi);

			empresaUi = (EmpresaUi) coParticipacaoContext.getArquivoInputUi().getContrato().getEmpresa();
			empresaUi = empresaService.findById(empresaUi.getId());

			beneficiarioColsUis = beneficiarioColsService
					.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi());

			if (beneficiarioColsUis.isEmpty()) {
				LOGGER.info(
						"Didn't found mappings at BeneficiarioCols for ArquivoInput[{}]:",
						coParticipacaoContext.getArquivoInputUi().getNameArquivoRegexp());
			}

			// Carregando todos os beneficiários existentes da empresa:
			LOGGER.info("Loading Beneficiários data by CPF and Name:");
			titularUis = titularService.listByEmpresaIdOrderByCpfAndName(empresaUi);
			dependenteUis = dependenteService.listByEmpresaIdOrderByCpfAndName(empresaUi);

			if (!titularUis.isEmpty()) {
				mapTitularUi = new PartitionMap<TitularUi>(titularUis, new TitularByCpfAndNameMapKeyBuilder());
				coParticipacaoContext.setMapTitularUiByCpf(mapTitularUi);
			}

			if (!dependenteUis.isEmpty()) {
				mapDependenteUi = new PartitionMap<DependenteUi>(
						dependenteUis,
						new DependenteByCpfAndNameMapKeyBuilder());
				coParticipacaoContext.setMapDependenteUiByCpf(mapDependenteUi);
			}

			LOGGER.info("Loading Beneficiários data by Matricula and Name:");
			titularUis = titularService.listByEmpresaIdOrderByMatriculaAndName(empresaUi);
			dependenteUis = dependenteService.listByEmpresaIdOrderByMatriculaAndName(empresaUi);

			if (!titularUis.isEmpty()) {
				mapTitularUi = new PartitionMap<TitularUi>(titularUis, new TitularByMatriculaAndNameMapKeyBuilder());
				coParticipacaoContext.setMapTitularUiByMatricula(mapTitularUi);
			}

			if (!dependenteUis.isEmpty()) {
				mapDependenteUi = new PartitionMap<DependenteUi>(
						dependenteUis,
						new DependenteByMatriculaAndNameMapKeyBuilder());
				coParticipacaoContext.setMapDependenteUiByMatricula(mapDependenteUi);
			}

			coParticipacaoContext.setEmpresaUi(empresaUi);
			coParticipacaoContext.setTitularUis(titularUis);
			coParticipacaoContext.setDependenteUis(dependenteUis);
			coParticipacaoContext.setBeneficiarioColsUis(beneficiarioColsUis);

			parameterUis = parameterService.listByEmpresaId(empresaUi.getId());
			coParticipacaoContext.getParameterUis().addAll(parameterUis);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	private void loadMecsasDefinitions(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	private void loadIsentoDefinitions(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		List<IsentoInputSheetUi> isentoInputSheetUis;

		try {
			LOGGER.info("BEGIN");

			isentoInputSheetUis = isentoInputSheetService
					.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi());

			coParticipacaoContext.setIsentoInputSheetUis(isentoInputSheetUis);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	private void loadLancamentoDefinitions(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		List<LancamentoInputColsUi> lancamentoInputColsUis;
		List<RegraUi> regraUis;
		List<RegraConditionalUi> regraConditionalUis;

		try {
			LOGGER.info("BEGIN");

			lancamentoInputColsUis = lancamentoInputColsService
					.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi());
			coParticipacaoContext.getLancamentoInputColsUis().addAll(lancamentoInputColsUis);

			// Caregando as regras para o arquivo:
			regraUis = regraService.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi());
			regraConditionalUis = regraConditionalService
					.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi());

			LOGGER.info(
					"Loading [{}] Regras to use with ArquivoInput [{}]:",
					regraUis.size(),
					coParticipacaoContext.getArquivoInputUi().getDescrArquivo());
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

	private void changeThreadName(CoParticipacaoContext coParticipacaoContext, String threadName)
			throws ServiceException {
		Thread thread;

		try {
			LOGGER.info("BEGIN");

			thread = Thread.currentThread();

			if (threadName != null) {
				thread.setName(
						String.format(
								"%s::%s.%s",
								thread.getName(),
								threadName,
								coParticipacaoContext.getContratoUi().getCdContrato()));
			} else {
				thread.setName("main");
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void clearCoparticipacao(EmpresaUi empresaUi) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (empresaUi == null) {
				LOGGER.info(
						"<<< Danger >>> You are going to clean all Coparticipação data in the database be sure to use a test databse.");
				coparticipacaoJdbcDao.clearCoparticipacao(null);
			} else {
				coparticipacaoJdbcDao.clearCoparticipacao(empresaUi.getId());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

}
