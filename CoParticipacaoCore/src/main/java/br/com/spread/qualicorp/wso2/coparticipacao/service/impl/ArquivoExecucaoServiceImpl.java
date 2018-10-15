package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.ArquivoExecucaoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoExecucaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoExecucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoExecucaoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoExecucaoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoExecucaoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ExternalProcessService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ArquivoExecucaoServiceImpl
		extends AbstractServiceImpl<ArquivoExecucaoUi, ArquivoExecucaoEntity, ArquivoExecucao>
		implements ArquivoExecucaoService {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoExecucaoServiceImpl.class);

	@Autowired
	private ArquivoExecucaoDao arquivoExecucaoDao;

	@Autowired
	private ArquivoExecucaoUiMapper uiMapper;

	@Autowired
	private ArquivoExecucaoEntityMapper entityMapper;

	@Autowired
	private ExternalProcessService externalProcessService;

	private static final String UPLOAD_DIR = ".coparticipacao/upload";

	@Autowired
	private ArquivoExecucaoBatchService arquivoExecucaoBatchService;

	public List<ArquivoExecucaoUi> listByEmpresaIdAndMesAndAno(EmpresaUi empresaUi, Integer mes, Integer ano)
			throws ServiceException {
		List<ArquivoExecucaoUi> arquivoExecucaoUis;

		try {
			LOGGER.info("BEGIN");

			arquivoExecucaoUis = entityToUi(
					arquivoExecucaoDao.listByEmpresaIdAndMesAndAno(empresaUi.getId(), mes, ano));

			LOGGER.info("END");
			return arquivoExecucaoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected ArquivoExecucaoUi createUi() {
		return new ArquivoExecucaoUi();
	}

	@Override
	protected ArquivoExecucaoEntity createEntity() {
		return new ArquivoExecucaoEntity();
	}

	@Override
	protected AbstractDao<ArquivoExecucaoEntity> getDao() {
		return arquivoExecucaoDao;
	}

	@Override
	protected AbstractMapper<ArquivoExecucao, ArquivoExecucaoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ArquivoExecucao, ArquivoExecucaoEntity> getEntityMapper() {
		return entityMapper;
	}

	public void updateStatus(CoParticipacaoContext coParticipacaoContext, StatusExecucaoType statusExecucaoType)
			throws ServiceException {
		ArquivoExecucaoUi arquivoExecucaoUi;
		ContratoUi contratoUi;
		LocalDate currentDate;

		try {
			LOGGER.info("BEGIN");

			currentDate = LocalDate.now();
			contratoUi = coParticipacaoContext.getContratoUi();

			if (coParticipacaoContext.getArquivoExecucaoUi() == null) {
				arquivoExecucaoUi = findByContratoIdAndMesAndAno(
						contratoUi,
						currentDate.getMonthValue(),
						currentDate.getYear());

				if (arquivoExecucaoUi == null) {
					arquivoExecucaoUi = new ArquivoExecucaoUi();
					arquivoExecucaoUi.setExecucao(coParticipacaoContext.getExecucaoUi());
					arquivoExecucaoUi.setUserCreated(coParticipacaoContext.getUser());
					arquivoExecucaoUi.setContrato(contratoUi);
					arquivoExecucaoUi.setMes(currentDate.getMonthValue());
					arquivoExecucaoUi.setAno(currentDate.getYear());
					arquivoExecucaoUi.setNameArquivoInput(coParticipacaoContext.getFileName());
				}
			} else {
				arquivoExecucaoUi = coParticipacaoContext.getArquivoExecucaoUi();
			}

			if (StatusExecucaoType.STARTED.equals(statusExecucaoType)) {
				arquivoExecucaoUi.setStarted(LocalDateTime.now());
			} else if (StatusExecucaoType.SUCCESS.equals(statusExecucaoType)) {
				arquivoExecucaoUi.setFinnished(LocalDateTime.now());
			}

			arquivoExecucaoUi.setStatusExecucaoType(statusExecucaoType);
			arquivoExecucaoUi.setUserAltered(coParticipacaoContext.getUser());

			// arquivoExecucaoBatchService.saveBatch(arquivoExecucaoUi);
			arquivoExecucaoUi = save(arquivoExecucaoUi);

			coParticipacaoContext.setArquivoExecucaoUi(arquivoExecucaoUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public ArquivoExecucaoUi findByArquivoInputIdAndMesAndAno(ArquivoInputUi arquivoInputUi, Integer mes, Integer ano)
			throws ServiceException {
		ArquivoExecucaoUi arquivoExecucaoUi;

		try {
			LOGGER.info("BEGIN");

			arquivoExecucaoUi = entityToUi(
					arquivoExecucaoDao.findByArquivoInputIdAndMesAndAno(arquivoInputUi.getId(), mes, ano));

			LOGGER.info("END");
			return arquivoExecucaoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public ArquivoExecucaoUi findByContratoIdAndMesAndAno(ContratoUi contratoUi, Integer mes, Integer ano)
			throws ServiceException {
		ArquivoExecucaoUi arquivoExecucaoUi;

		try {
			LOGGER.info("BEGIN");

			arquivoExecucaoUi = entityToUi(
					arquivoExecucaoDao.findByContratoIdAndMesAndAno(contratoUi.getId(), mes, ano));

			LOGGER.info("END");
			return arquivoExecucaoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public ArquivoExecucaoUi saveFile(UserUi userUi, InputStream inputstream, String fileName) throws ServiceException {
		StringBuilder sb;
		File coparticipacaoFile;
		ArquivoExecucaoUi arquivoExecucaoUi;

		try {
			LOGGER.info("BEGIN");

			arquivoExecucaoUi = new ArquivoExecucaoUi();

			sb = new StringBuilder();
			sb.append(System.getProperty("user.home"));
			sb.append(File.separator);
			sb.append(UPLOAD_DIR);
			sb.append(File.separator);
			sb.append(userUi.getNameLogin());

			coparticipacaoFile = new File(sb.toString());

			if (!coparticipacaoFile.exists()) {
				LOGGER.info("Creating user's upload dir [{}]:", sb.toString());
				coparticipacaoFile.mkdirs();
			}

			sb.append(File.separator);
			sb.append(fileName);

			coparticipacaoFile = new File(sb.toString());

			LOGGER.info("Moving file [{}] to [{}]:", fileName, sb.toString());
			FileUtils.copyInputStreamToFile(inputstream, coparticipacaoFile);

			arquivoExecucaoUi.setNameArquivoInput(sb.toString());
			arquivoExecucaoUi.setStatusExecucaoType(StatusExecucaoType.PENDING);
			arquivoExecucaoUi.setUserCreated(userUi);
			arquivoExecucaoUi.setUserAltered(userUi);

			LOGGER.info("END");
			return arquivoExecucaoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public void sendToProcess(List<ArquivoExecucao> arquivoExecucaos) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			for (ArquivoExecucao arquivoExecucao : arquivoExecucaos) {
				sendToProcess((ArquivoExecucaoUi) arquivoExecucao);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public ArquivoExecucaoUi sendToProcess(ArquivoExecucaoUi arquivoExecucaoUi) throws ServiceException {
		File coparticipacaoFile;
		LocalDate currentDate;
		ContratoUi contratoUi;
		String fileName;
		File inputFile;

		try {
			LOGGER.info("BEGIN");

			contratoUi = (ContratoUi) arquivoExecucaoUi.getContrato();
			fileName = arquivoExecucaoUi.getNameArquivoInput();
			inputFile = new File(fileName);

			if (inputFile.exists()) {
				currentDate = LocalDate.now();

				renameToProcess(arquivoExecucaoUi);

				coparticipacaoFile = new File(arquivoExecucaoUi.getNameArquivoInput());

				if (coparticipacaoFile.exists()) {
					coparticipacaoFile.delete();
				}

				LOGGER.info("Moving file [{}] to [{}]:", fileName, arquivoExecucaoUi.getNameArquivoInput());
				FileUtils.moveFile(inputFile, coparticipacaoFile);

				arquivoExecucaoUi.setNameArquivoInput(coparticipacaoFile.getAbsolutePath());
				arquivoExecucaoUi.setMes(currentDate.getMonthValue());
				arquivoExecucaoUi.setAno(currentDate.getYear());

				if (contratoUi.getEmpresa().isEnabledExternalProcess()) {
					externalProcessService.createNameArquivoOutput(arquivoExecucaoUi);
				}

				arquivoExecucaoUi.setStatusExecucaoType(StatusExecucaoType.STARTED);
				arquivoExecucaoUi.setStarted(LocalDateTime.now());

				// arquivoExecucaoBatchService.saveBatch(arquivoExecucaoUi);
				save(arquivoExecucaoUi);
			} else {
				throw new ServiceException("File [%s] does not exists.", inputFile.getAbsolutePath());
			}

			LOGGER.info("END");
			return arquivoExecucaoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public void renameToProcess(ArquivoExecucaoUi arquivoExecucaoUi) throws ServiceException {
		StringBuilder sb;
		ContratoUi contratoUi;
		EmpresaUi empresaUi;
		int pos;
		String fileName;
		LocalDate currentDate;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();
			contratoUi = (ContratoUi) arquivoExecucaoUi.getContrato();
			empresaUi = (EmpresaUi) contratoUi.getEmpresa();
			fileName = arquivoExecucaoUi.getNameArquivoInput();
			currentDate = LocalDate.now();

			LOGGER.info("Moving the file to process directory:");
			sb.append(contratoUi.getEmpresa().getInputDir());

			if (!empresaUi.getInputDir().endsWith(File.separator)) {
				sb.append(File.separator);
			}

			if (contratoUi.getEmpresa().isEnabledExternalProcess()) {
				pos = fileName.lastIndexOf(File.separator);

				sb.append(fileName.substring(pos + 1));
			} else {
				sb.append(contratoUi.getEmpresa().getCdEmpresa());
				sb.append(".");
				sb.append(contratoUi.getCdContrato());
				sb.append(".");
				sb.append(currentDate.getYear());
				sb.append(StringUtils.leftPad(String.valueOf(currentDate.getMonthValue()), 2, "0"));
				sb.append(".");
				sb.append(StringUtils.leftPad(String.valueOf(arquivoExecucaoUi.getOrdem()), 3, "0"));
				sb.append(".");

				// Finding file extension:
				pos = fileName.lastIndexOf(".");

				sb.append(fileName.substring(pos + 1).toLowerCase());
			}

			arquivoExecucaoUi.setNameArquivoInput(sb.toString());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public void deleteByEmpresaIdAndMesAndAno(EmpresaUi empresaUi, Integer mes, Integer ano) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			LOGGER.info(
					"Cleaning all ArquivoExecucaoUis for EmpresaUi.NM_EMPRESA[{}] at Mes[{}] and Ano[{}]:",
					empresaUi.getNameEmpresa(),
					mes,
					ano);

			arquivoExecucaoDao.deleteByEmpresaIdAndMesAndAno(empresaUi.getId(), mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public void deleteByContratoIdAndMesAndAno(ContratoUi contratoUi, Integer mes, Integer ano)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			arquivoExecucaoDao.deleteByContratoIdAndMesAndAno(contratoUi.getId(), mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected void logBatchInfo(ArquivoExecucaoUi arquivoExecucaoUi) throws ServiceException {
		LOGGER.info("CD_MES: ..................... [{}]", arquivoExecucaoUi.getMes());
		LOGGER.info("CD_ANO: ..................... [{}]", arquivoExecucaoUi.getAno());
		LOGGER.info("CD_CONTRATO: ................ [{}]", arquivoExecucaoUi.getContrato().getCdContrato());
		LOGGER.info("NM_CONTRATO: ................ [{}]", arquivoExecucaoUi.getContrato().getNameContrato());
		LOGGER.info("NM_ARQUIVO_INPUT: ........... [{}]", arquivoExecucaoUi.getNameArquivoInput());
		LOGGER.info("NM_ARQUIVO_OUTPUT: .......... [{}]", arquivoExecucaoUi.getNameArquivoOutput());
		LOGGER.info("STATUS: ..................... [{}]", arquivoExecucaoUi.getStatusExecucaoType().getDescription());
		LOGGER.info("DT_STARTED: ................. [{}]", arquivoExecucaoUi.getStarted());
		LOGGER.info("DT_FINNISHED: ............... [{}]", arquivoExecucaoUi.getFinnished());
		LOGGER.info("ERROR_MESSAGE: .............. [{}]", arquivoExecucaoUi.getErrorMessage());
		LOGGER.info("USER_CREATED: ............... [{}]", arquivoExecucaoUi.getUserCreated().getNameLogin());
	}

	public ArquivoExecucaoUi createArquivoExecucao(CoParticipacaoContext coParticipacaoContext, UseType useType)
			throws ServiceException {
		ArquivoExecucaoUi arquivoExecucaoUi = null;
		ContratoUi contratoUi = null;

		try {
			LOGGER.info("BEGIN");

			for (Contrato contrato : coParticipacaoContext.getEmpresaUi().getContratos()) {
				if (contrato.getUseType().equals(useType)) {
					contratoUi = (ContratoUi) contrato;
					break;
				}
			}

			if (contratoUi != null) {
				arquivoExecucaoUi = createArquivoExecucao(coParticipacaoContext, contratoUi);
			} else {
				throw new ServiceException("There is no ContratoUi for UseType.[%s]:", useType.getDescription());
			}

			LOGGER.info("END");
			return arquivoExecucaoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public ArquivoExecucaoUi createArquivoExecucao(CoParticipacaoContext coParticipacaoContext, ContratoUi contratoUi)
			throws ServiceException {
		ArquivoExecucaoUi arquivoExecucaoUi = null;
		LocalDate currentDate;

		try {
			LOGGER.info("BEGIN");

			currentDate = LocalDate.now();

			arquivoExecucaoUi = findByContratoIdAndMesAndAno(
					contratoUi,
					currentDate.getMonthValue(),
					currentDate.getYear());

			if (arquivoExecucaoUi == null) {
				arquivoExecucaoUi = new ArquivoExecucaoUi();
				arquivoExecucaoUi.setExecucao(coParticipacaoContext.getExecucaoUi());
			}

			arquivoExecucaoUi.setContrato(contratoUi);
			arquivoExecucaoUi.setMes(currentDate.getMonthValue());
			arquivoExecucaoUi.setAno(currentDate.getYear());
			arquivoExecucaoUi.setStatusExecucaoType(StatusExecucaoType.PENDING);
			arquivoExecucaoUi.setNameArquivoInput(coParticipacaoContext.getFileName());
			arquivoExecucaoUi.setUserCreated(coParticipacaoContext.getUser());

			LOGGER.info("END");
			return arquivoExecucaoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public boolean isJobDone(ArquivoExecucaoUi arquivoExecucaoUi, UserUi userUi) throws ServiceException {
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			if (arquivoExecucaoUi != null) {
				empresaUi = (EmpresaUi) arquivoExecucaoUi.getContrato().getEmpresa();

				if (empresaUi.isEnabledExternalProcess()) {
					LOGGER.info("END");

					if (externalProcessService.isJobDone(arquivoExecucaoUi)) {
						if (!StatusExecucaoType.SUCCESS.equals(arquivoExecucaoUi.getStatusExecucaoType())) {
							arquivoExecucaoUi.setStatusExecucaoType(StatusExecucaoType.SUCCESS);
							arquivoExecucaoUi.setFinnished(LocalDateTime.now());
							arquivoExecucaoUi.setUserAltered(userUi);

							// arquivoExecucaoBatchService.saveBatch(arquivoExecucaoUi);
							save(arquivoExecucaoUi);
						}

						LOGGER.info("END");
						return true;
					}
				} else {
					if (UseType.FATUCOPA.equals(arquivoExecucaoUi.getContrato().getUseType())
							|| UseType.EXTRA_FILE.equals(arquivoExecucaoUi.getContrato().getUseType())
							|| UseType.NAO_LOCALIZADO.equals(arquivoExecucaoUi.getContrato().getUseType())) {
						if (StatusExecucaoType.SUCCESS.equals(arquivoExecucaoUi.getStatusExecucaoType())) {
							LOGGER.info(
									"File with ContratoUi [{}] is ready to download:",
									arquivoExecucaoUi.getContrato().getCdContrato());
							LOGGER.info("END");
							return true;
						}
					}
				}
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public void deleteByEmpresaAndUseTypeAndMesAndAno(EmpresaUi empresaUi, UseType useType, Integer mes, Integer ano)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			arquivoExecucaoDao.deleteByEmpresaAndUseTypeAndMesAndAno(empresaUi.getId(), useType, mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
