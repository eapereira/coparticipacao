package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.webservice.coparticipacao.CoParticipacaoInfo;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.CoparticipacaoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoExecucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.io.CsvProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.io.FixedLengthProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.io.IsentoSpreadsheetProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.io.ProcessorListener;
import br.com.spread.qualicorp.wso2.coparticipacao.io.SpreadsheetMultiSheetProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.io.SpreadsheetProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.search.DependenteByCpfAndNameMapKeyBuilder;
import br.com.spread.qualicorp.wso2.coparticipacao.search.DependenteByMatriculaAndNameMapKeyBuilder;
import br.com.spread.qualicorp.wso2.coparticipacao.search.PartitionMap;
import br.com.spread.qualicorp.wso2.coparticipacao.search.TitularByCpfAndNameMapKeyBuilder;
import br.com.spread.qualicorp.wso2.coparticipacao.search.TitularByMatriculaAndNameMapKeyBuilder;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputColsDefService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.CoParticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ContratoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.FatucopaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoInputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoInputColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoInputSheetColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoInputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.MecsasService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.NaoLocalizadoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.UserService;
import br.com.spread.qualicorp.wso2.coparticipacao.util.FriendlyErrorHelper;
import br.com.spread.qualicorp.wso2.coparticipacao.util.StopWatchAdapter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
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

	@Qualifier("SpreadsheetMultiSheetProcessorService")
	@Autowired
	private SpreadsheetMultiSheetProcessorService spreadsheetMultiSheetProcessorService;

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
	private IsentoService isentoService;

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

	@Autowired
	private ArquivoExecucaoService arquivoExecucaoService;

	@Autowired
	private ExecucaoService execucaoService;

	@Autowired
	private ArquivoInputSheetService arquivoInputSheetService;

	@Autowired
	private DesconhecidoService desconhecidoService;

	@Autowired
	private LancamentoInputSheetService lancamentoInputSheetService;

	@Autowired
	private LancamentoInputSheetColsService lancamentoInputSheetColsService;

	@Autowired
	private ArquivoOutputService arquivoOutputService;

	@Autowired
	private ContratoService contratoService;

	private static final Long USER_ADMIN_ID = 1l;

	public CoParticipacaoInfo processExecucaoId(Long execucaoId) throws ServiceException {
		CoParticipacaoInfo coParticipacaoInfo;
		ExecucaoUi execucaoUi = null;
		String fileName;
		File file;
		StopWatchAdapter stopWatch;

		try {
			LOGGER.info("BEGIN");

			// Para marcar o tempo que levou para fazer a tarefa:
			stopWatch = new StopWatchAdapter();
			stopWatch.start();

			coParticipacaoInfo = new CoParticipacaoInfo();

			if (execucaoId != null) {
				execucaoUi = execucaoService.findById(execucaoId);
			}

			if (execucaoUi != null && ExecucaoType.OPEN.equals(execucaoUi.getExecucaoType())) {
				for (ArquivoExecucao arquivoExecucao : execucaoUi.getArquivoExecucaos()) {
					fileName = arquivoExecucao.getNameArquivoInput();
					file = new File(fileName);

					if (file.exists()) {
						LOGGER.info("Receiving file [{}] to process:", fileName);

						coParticipacaoInfo = processFile((ArquivoExecucaoUi) arquivoExecucao);
					} else {
						throw new ServiceException("File [%s] not found:", fileName);
					}
				}
			} else {
				throw new ServiceException("Invalid Execucao.ID[%s] received:", execucaoId);
			}

			execucaoUi.setExecucaoType(ExecucaoType.CLOSED);
			execucaoService.save(execucaoUi);

			stopWatch.stop();

			LOGGER.info(
					"CoParticipacao task ExecucaoUi[{}] completed with [{}] min:",
					execucaoUi.getId(),
					stopWatch.getTotalTimeMinutes());
			LOGGER.info("END");
			return coParticipacaoInfo;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void moveExecucaoToOutput(CoParticipacaoContext coParticipacaoContext, ArquivoExecucaoUi arquivoExecucaoUi)
			throws ServiceException {
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			empresaUi = coParticipacaoContext.getEmpresaUi();

			moveExecucaoToDirectory(coParticipacaoContext, arquivoExecucaoUi, empresaUi.getOutputDir());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void moveExecucaoToFailure(CoParticipacaoContext coParticipacaoContext, ArquivoExecucaoUi arquivoExecucaoUi)
			throws ServiceException {
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			if (coParticipacaoContext != null) {
				empresaUi = coParticipacaoContext.getEmpresaUi();

				moveExecucaoToDirectory(coParticipacaoContext, arquivoExecucaoUi, empresaUi.getFailureDir());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	protected void moveExecucaoToDirectory(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoExecucaoUi arquivoExecucaoUi,
			String destPathDir) throws ServiceException {
		File file;
		File destFile;
		EmpresaUi empresaUi;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			empresaUi = coParticipacaoContext.getEmpresaUi();

			LOGGER.info(
					"Moving file[{}] to [{}]:",
					arquivoExecucaoUi.getSimpleNameArquivoInput(),
					empresaUi.getOutputDir());

			sb = new StringBuilder();
			sb.append(destPathDir);
			sb.append(File.separator);
			sb.append(arquivoExecucaoUi.getSimpleNameArquivoInput());

			file = new File(arquivoExecucaoUi.getNameArquivoInput());
			destFile = new File(sb.toString());

			if (destFile.exists()) {
				destFile.delete();
			}

			FileUtils.moveFile(file, destFile);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private CoParticipacaoInfo processFile(ArquivoExecucaoUi arquivoExecucaoUi) throws ServiceException {
		CoParticipacaoInfo coParticipacaoInfo;
		CoParticipacaoContext coParticipacaoContext = null;
		StopWatchAdapter stopWatch;

		try {
			LOGGER.info("BEGIN");

			// Para marcar o tempo que levou para fazer a tarefa:
			stopWatch = new StopWatchAdapter();
			stopWatch.start();

			coParticipacaoInfo = new CoParticipacaoInfo();

			coParticipacaoContext = createCoParticipacaoContext(arquivoExecucaoUi.getSimpleNameArquivoInput());
			coParticipacaoContext.setExecucaoUi((ExecucaoUi) arquivoExecucaoUi.getExecucao());
			coParticipacaoContext.setArquivoExecucaoUi(arquivoExecucaoUi);

			if (!coParticipacaoContext.getArquivoInputColsDefUis().isEmpty()
					|| !coParticipacaoContext.getMapArquivoInputSheetUi().isEmpty()) {
				loadFileInputData(arquivoExecucaoUi, coParticipacaoContext);
			} else {
				throw new ServiceException(
						"O arquivo [%s] não possui colunas definidas.",
						arquivoExecucaoUi.getSimpleNameArquivoInput());
			}

			moveExecucaoToOutput(coParticipacaoContext, arquivoExecucaoUi);

			generateOutputFileWithoutFatucopa(coParticipacaoContext);

			stopWatch.stop();

			LOGGER.info("CoParticipacao task completed with [{}] min:", stopWatch.getTotalTimeMinutes());
			LOGGER.info("END");
			return coParticipacaoInfo;
		} catch (Exception e) {
			moveExecucaoToFailure(coParticipacaoContext, arquivoExecucaoUi);

			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void generateOutputFileWithoutFatucopa(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		EmpresaUi empresaUi;
		List<ContratoUi> children;

		try {
			LOGGER.info("BEGIN");

			empresaUi = coParticipacaoContext.getEmpresaUi();

			if (empresaUi.isGenerateOutputFileWithoutFatucopa()) {
				for (Contrato contrato : empresaUi.getContratos()) {
					if (UseType.FATUCOPA.equals(contrato.getUseType())) {
						LOGGER.info("Preparing to create report for ContratoUi[{}]:", contrato.getCdContrato());
						children = contratoService.listByParent((ContratoUi) contrato);

						if (!children.isEmpty()) {
							LOGGER.info("Main ContratoUi[{}] has children contratos:", contrato.getCdContrato());

							for (Contrato contratoChild : children) {
								if (UseType.FATUCOPA.equals(contratoChild.getUseType())) {
									LOGGER.info(
											"Creating Output files for ContratoUi[{}]:",
											contratoChild.getCdContrato());
									generateOutputFileWithoutFatucopa(
											coParticipacaoContext,
											(ContratoUi) contratoChild);
								}
							}
						} else {
							generateOutputFileWithoutFatucopa(coParticipacaoContext, (ContratoUi) contrato);
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

	private void generateOutputFileWithoutFatucopa(CoParticipacaoContext coParticipacaoContext, ContratoUi contratoUi)
			throws ServiceException {
		ArquivoExecucaoUi arquivoExecucaoUi;
		ArquivoExecucaoUi arquivoExecucaoUiTmp;
		List<ArquivoOutputUi> arquivoOutputUis;
		ContratoUi parent;

		try {
			LOGGER.info("BEGIN");

			if (contratoUi.getParent() != null) {
				parent = (ContratoUi) contratoUi.getParent();
			} else {
				parent = contratoUi;
			}

			LOGGER.info("Using ContratoUi[{}] as FATUCOPA:", parent.getCdContrato());

			LOGGER.info("Loading necessary data to create the report:");
			arquivoExecucaoUi = arquivoExecucaoService
					.createArquivoExecucao(coParticipacaoContext, (ContratoUi) contratoUi);
			arquivoExecucaoUiTmp = coParticipacaoContext.getArquivoExecucaoUi();
			coParticipacaoContext.setArquivoExecucaoUi(arquivoExecucaoUi);

			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.STARTED);
			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.RUNNING);

			arquivoOutputUis = arquivoOutputService.listByContrato((ContratoUi) parent);
			coParticipacaoContext.setArquivoOutputUis(arquivoOutputUis);

			fatucopaService.generateOutputFileWithoutFatucopa(coParticipacaoContext);

			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.SUCCESS);

			coParticipacaoContext.setArquivoExecucaoUi(arquivoExecucaoUiTmp);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private CoParticipacaoContext createCoParticipacaoContext(String fileName) throws ServiceException {
		CoParticipacaoContext coParticipacaoContext = null;
		List<ArquivoInputColsDefUi> arquivoInputColsDefUis;
		List<ArquivoInputSheetUi> arquivoInputSheetUis;
		List<ArquivoOutputUi> arquivoOutputUis;
		ContratoUi contratoUi;

		try {
			LOGGER.info("BEGIN");

			coParticipacaoContext = arquivoInputService.findByName(fileName);

			if (coParticipacaoContext == null) {
				throw new ServiceException("O arquivo [%s] não é cadastrado em ArquivoInput no sistema.", fileName);
			} else {
				coParticipacaoContext.setFileName(fileName);

				arquivoInputColsDefUis = arquivoInputColsDefService
						.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi().getId());

				arquivoInputSheetUis = arquivoInputSheetService
						.listByArquivoInput(coParticipacaoContext.getArquivoInputUi());

				contratoUi = contratoService.findById(coParticipacaoContext.getArquivoInputUi().getContrato().getId());

				for (ArquivoInputSheetUi arquivoInputSheetUi : arquivoInputSheetUis) {
					coParticipacaoContext.getMapArquivoInputSheetUi()
							.put(arquivoInputSheetUi.getSheetId(), arquivoInputSheetUi);
				}

				coParticipacaoContext.setArquivoInputColsDefUis(arquivoInputColsDefUis);

				coParticipacaoContext.setContratoUi(contratoUi);
				arquivoOutputUis = arquivoOutputService.listByContrato(coParticipacaoContext.getContratoUi());
				coParticipacaoContext.setArquivoOutputUis(arquivoOutputUis);
			}

			LOGGER.info("END");
			return coParticipacaoContext;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void loadFileInputData(ArquivoExecucaoUi arquivoExecucaoUi, CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		FileInputStream fileInputStream = null;
		ArquivoInputUi arquivoInputUi;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Loading file [{}]:", arquivoExecucaoUi.getSimpleNameArquivoInput());

			fileInputStream = new FileInputStream(arquivoExecucaoUi.getNameArquivoInput());

			coParticipacaoContext.setFileInputStream(fileInputStream);

			loadDefaultDefinitions(coParticipacaoContext);

			arquivoInputUi = coParticipacaoContext.getArquivoInputUi();

			arquivoExecucaoUi.getExecucao().setUserCreated(coParticipacaoContext.getUser());
			arquivoExecucaoUi.getExecucao().setUserAltered(coParticipacaoContext.getUser());

			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.RUNNING);

			if (UseType.FATUCOPA.equals(arquivoInputUi.getUseType())) {
				changeThreadName(coParticipacaoContext, "FatuCopaService");

				loadFatucopaDefinitions(coParticipacaoContext);

				if (ArquivoType.FIXED_LENGTH.equals(arquivoInputUi.getArquivoType())) {
					fixedLengthProcessorService
							.readInputStream(coParticipacaoContext, (ProcessorListener) fatucopaService);
				} else if (ArquivoType.CSV.equals(arquivoInputUi.getArquivoType())) {
					csvProcessorService.readInputStream(coParticipacaoContext, (ProcessorListener) fatucopaService);
				} else if (ArquivoType.SPREADSHEET.equals(arquivoInputUi.getArquivoType())) {
					if (!coParticipacaoContext.getMapArquivoInputSheetUi().isEmpty()) {
						spreadsheetMultiSheetProcessorService
								.readInputStream(coParticipacaoContext, (ProcessorListener) fatucopaService);
					} else {
						spreadsheetProcessorService
								.readInputStream(coParticipacaoContext, (ProcessorListener) fatucopaService);
					}
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
					if (!coParticipacaoContext.getMapArquivoInputSheetUi().isEmpty()) {
						spreadsheetMultiSheetProcessorService
								.readInputStream(coParticipacaoContext, (ProcessorListener) mecsasService);
					} else {
						spreadsheetProcessorService
								.readInputStream(coParticipacaoContext, (ProcessorListener) mecsasService);
					}
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

			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.SUCCESS);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			FriendlyErrorHelper.createFriendlyErrorMessage(
					coParticipacaoContext,
					coParticipacaoContext.getArquivoExecucaoUi(),
					e.getMessage());

			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.ERROR);

			throw new ServiceException(e.getMessage(), e);
		} finally {
			close(fileInputStream);
		}
	}

	private void close(FileInputStream fileInputStream) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (fileInputStream != null) {
				fileInputStream.close();
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
		PartitionMap<TitularUi> mapTitularUi;
		PartitionMap<DependenteUi> mapDependenteUi;
		List<DesconhecidoUi> desconhecidoUis;

		try {
			LOGGER.info("BEGIN");

			userUi = userService.findById(USER_ADMIN_ID);

			coParticipacaoContext.setUser(userUi);

			empresaUi = coParticipacaoContext.getEmpresaUi();

			loadBeneficiarioCols(coParticipacaoContext);

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

			desconhecidoUis = desconhecidoService.listByEmpresaId(empresaUi);

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

			coParticipacaoContext.setTitularUis(titularUis);
			coParticipacaoContext.setDependenteUis(dependenteUis);
			coParticipacaoContext.setDesconhecidoUis(desconhecidoUis);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	private void loadBeneficiarioCols(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		List<BeneficiarioColsUi> beneficiarioColsUis;

		try {
			LOGGER.info("BEGIN");

			beneficiarioColsUis = beneficiarioColsService
					.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi());

			if (beneficiarioColsUis.isEmpty()) {
				for (ArquivoInputSheet arquivoInputSheet : coParticipacaoContext.getArquivoInputUi()
						.getArquivoInputSheets()) {
					beneficiarioColsUis = beneficiarioColsService
							.listByArquivoInputSheetId((ArquivoInputSheetUi) arquivoInputSheet);

					if (!beneficiarioColsUis.isEmpty()) {
						coParticipacaoContext.getMapBeneficiarioCols()
								.put(arquivoInputSheet.getSheetId(), beneficiarioColsUis);
					}
				}

				if (coParticipacaoContext.getMapBeneficiarioCols().isEmpty()) {
					LOGGER.info(
							"Didn't found mappings at BeneficiarioCols for ArquivoInput[{}]:",
							coParticipacaoContext.getArquivoInputUi().getNameArquivoRegexp());
				}
			} else {
				coParticipacaoContext.setBeneficiarioColsUis(beneficiarioColsUis);
			}

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

	private void loadFatucopaDefinitions(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		List<LancamentoInputColsUi> lancamentoInputColsUis;
		List<LancamentoInputSheetColsUi> lancamentoInputSheetColsUis;
		List<RegraUi> regraUis;
		List<RegraConditionalUi> regraConditionalUis;
		List<LancamentoInputSheetUi> lancamentoInputSheetUis;
		Set<Integer> keys;
		ArquivoInputSheetUi arquivoInputSheetUi;

		try {
			LOGGER.info("BEGIN");

			lancamentoInputColsUis = lancamentoInputColsService
					.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi());

			if (!lancamentoInputColsUis.isEmpty()) {
				coParticipacaoContext.getLancamentoInputColsUis().addAll(lancamentoInputColsUis);
			} else {
				keys = coParticipacaoContext.getMapArquivoInputSheetUi().keySet();

				for (Integer key : keys) {
					arquivoInputSheetUi = coParticipacaoContext.getMapArquivoInputSheetUi().get(key);

					lancamentoInputSheetColsUis = lancamentoInputSheetColsService
							.listByArquivoInputSheet(arquivoInputSheetUi);

					LOGGER.info(
							"Loading LancamentoInputSheetColsUi for sheetId[{}]:",
							arquivoInputSheetUi.getSheetId());

					if (!lancamentoInputSheetColsUis.isEmpty()) {
						coParticipacaoContext.getMapLancamentoInputSheetColsUis()
								.put(arquivoInputSheetUi.getSheetId(), lancamentoInputSheetColsUis);
					}
				}
			}

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

			lancamentoInputSheetUis = lancamentoInputSheetService
					.listByArquivoInput(coParticipacaoContext.getArquivoInputUi());

			coParticipacaoContext.getRegraUis().addAll(regraUis);
			coParticipacaoContext.getRegraConditionalUis().addAll(regraConditionalUis);
			coParticipacaoContext.setLancamentoInputSheetUis(lancamentoInputSheetUis);

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
