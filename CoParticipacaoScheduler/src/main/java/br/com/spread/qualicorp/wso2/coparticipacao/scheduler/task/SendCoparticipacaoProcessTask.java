package br.com.spread.qualicorp.wso2.coparticipacao.scheduler.task;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPort;
import br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPortServiceLocator;
import br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoRequest;
import br.com.spread.qualicorp.wso2.coparticipacao.xml.XmlUtils;
import br.com.spread.qualicorp.wso2.coparticipacao.xml.execucao.ExecucaoXml;
import br.com.spread.qualicorp.wso2.coparticipacao.xml.execucao.JobXml;

/**
 * 
 * @author eapereira
 *
 */
@Component
public class SendCoparticipacaoProcessTask {

	private static final Logger LOGGER = LogManager.getLogger(SendCoparticipacaoProcessTask.class);

	private static final String INPUT_DIR = "/home/eapereira/desenv/work/coparticipacao/input";

	private static final String OUTPUT_DIR = "/home/eapereira/desenv/work/coparticipacao/output";

	private static final String FAILURE_DIR = "/home/eapereira/desenv/work/coparticipacao/failure";

	@Scheduled(fixedRate = 3000)
	public void searchCoparticipacao() throws CoParticipacaoException {
		File inputDir;
		Collection<File> files;
		Long execucaoId;
		File processFile = null;

		try {
			LOGGER.info("BEGIN");

			inputDir = new File(INPUT_DIR);

			if (!inputDir.exists()) {
				inputDir.mkdirs();
			}

			LOGGER.info("Listing content of inputDir[{}]:", INPUT_DIR);
			files = FileUtils.listFiles(inputDir, FileFilterUtils.suffixFileFilter(".copa.xml"), null);

			for (File file : files) {
				processFile = file;
				LOGGER.info("Found coparticipacao process file[{}] to create request:", processFile.getName());

				execucaoId = loadExecucaoFromFile(processFile);

				sendProcessToExecute(execucaoId);

				LOGGER.info("Successfully sent ExecucaoId[{}] to WebService:", execucaoId);
				moveToOutput(processFile);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			moveToFailure(processFile);

			throw new CoParticipacaoException(e);
		}
	}

	private void moveToOutput(File processFile) throws CoParticipacaoException {
		File file;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();
			sb.append(OUTPUT_DIR);
			sb.append(File.separator);
			sb.append(processFile.getName());

			file = new File(sb.toString());

			if (file.exists()) {
				file.delete();
			}

			FileUtils.moveFileToDirectory(processFile, new File(OUTPUT_DIR), true);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	private void moveToFailure(File processFile) throws CoParticipacaoException {
		File file;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();
			sb.append(FAILURE_DIR);
			sb.append(File.separator);
			sb.append(processFile.getName());

			file = new File(sb.toString());

			if (file.exists()) {
				file.delete();
			}

			if (processFile != null) {
				FileUtils.moveFileToDirectory(processFile, new File(FAILURE_DIR), true);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	private void sendProcessToExecute(Long execucaoId) throws CoParticipacaoException {
		CoParticipacaoPortServiceLocator coParticipacaoPortServiceLocator;
		CoParticipacaoPort coParticipacaoPort;
		CoParticipacaoRequest coParticipacaoRequest;

		try {
			LOGGER.info("BEGIN");

			coParticipacaoPortServiceLocator = new CoParticipacaoPortServiceLocator();
			coParticipacaoPort = coParticipacaoPortServiceLocator.getCoParticipacaoPortSoap11();

			coParticipacaoRequest = new CoParticipacaoRequest();
			coParticipacaoRequest.setExecucaoId(execucaoId);

			coParticipacaoPort.coParticipacao(coParticipacaoRequest);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	private Long loadExecucaoFromFile(File processFile) throws CoParticipacaoException {
		Long execucaoId = null;
		JobXml jobXml;

		try {
			LOGGER.info("BEGIN");

			jobXml = XmlUtils.readFile(processFile, JobXml.class);

			if (jobXml != null) {
				for (ExecucaoXml execucaoXml : jobXml.getExecucaoXmls()) {
					execucaoId = execucaoXml.getExecucaoId();
					break;
				}

				LOGGER.info("User created execucaoId[{}]:", execucaoId);
			} else {
				LOGGER.info("Could not parse XML file with Coparticipacao's execucaoId:");
			}

			LOGGER.info("END");
			return execucaoId;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}
}
