package br.com.spread.qualicorp.wso2.coparticipacao.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class FriendlyErrorHelper {

	private static final Logger LOGGER = LogManager.getLogger(FriendlyErrorHelper.class);

	private static final String ERROR_MATRICULA_TITULAR = "UN_TITULAR_01";

	private static final String ERROR_DUPLICATED_DEPENDENTE_02 = "UN_DEPENDENTE_02";

	private static final String ERROR_COMMUNICATION_LINK = "Communications link failure";

	private static final String ERROR_CONNECT_DATABASE = "Unable to acquire JDBC Connection";

	private static final String ERROR_INVALID_UPLOADED_FILE = "No valid entries or contents found, this is not a valid OOXML (Office Open XML) file";

	private static final String ERROR_INVALID_SPREADSHEET_FILE = "You are calling the part of POI that deals with OOXML (Office Open XML) Documents";

	private static final String ERROR_ARQUIVO_INPUT_PARSER = "ArquivoInputException";

	private static final Pattern REGEXP_MATRICULA_TITULAR = Pattern
			.compile("Duplicate entry \\'([0-9]{1,10})\\-([0-9]{1,10})\\' for key \\'UN_TITULAR_02\\'");

	private static final Pattern REGEXP_DUPLICATED_DEPENDENTE_02 = Pattern
			.compile("Duplicate\\Wentry\\W'(.+)(\\-)([0-9]{1,})(' for key 'UN_DEPENDENTE_02')");

	private static final int GROUP_MATRICULA_TITULAR = 2;

	private static final int GROUP_CONTRATO_TITULAR = 1;

	public static void createFriendlyErrorMessage(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoExecucaoUi arquivoExecucaoUi,
			String errorMessage) throws ServiceException {
		String friendlyMessage = null;

		try {
			LOGGER.info("BEGIN");

			if (StringUtils.contains(errorMessage, ERROR_MATRICULA_TITULAR)) {
				friendlyMessage = createErrorMatriculaTitular(coParticipacaoContext, errorMessage);
			} else if (StringUtils.contains(errorMessage, ERROR_COMMUNICATION_LINK)) {
				friendlyMessage = "Consulta ao banco de dados excedeu o tempo limite";
			} else if (StringUtils.contains(errorMessage, ERROR_CONNECT_DATABASE)) {
				friendlyMessage = "Não foi possível conectar ao Banco de Dados";
			} else if (StringUtils.contains(errorMessage, ERROR_INVALID_UPLOADED_FILE)
					|| StringUtils.contains(errorMessage, ERROR_INVALID_SPREADSHEET_FILE)) {
				friendlyMessage = "O arquivo carregado não é uma planilha Excel XLSX válida.";
			} else if (StringUtils.contains(errorMessage, ERROR_DUPLICATED_DEPENDENTE_02)) {
				friendlyMessage = createErrorDuplicatedDependente(errorMessage);
			} else if (StringUtils.contains(errorMessage, ERROR_ARQUIVO_INPUT_PARSER)) {
				friendlyMessage = "O arquivo de entrada não se encontra no formato esperado";
			} else {
				/*
				 * Se não conhecemos o erro é melhor mostra-lo de forma completa
				 * ao usuário:
				 */
				friendlyMessage = errorMessage;
			}

			LOGGER.error(friendlyMessage);
			arquivoExecucaoUi.setErrorMessage(friendlyMessage);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private static String createErrorMatriculaTitular(CoParticipacaoContext coParticipacaoContext, String errorMessage)
			throws ServiceException {
		Matcher matcher;
		String friendlyMessage = null;
		ContratoUi contratoUi;
		Long matricula;

		try {
			LOGGER.info("BEGIN");

			friendlyMessage = "Existem Títulares com o mesmo número de Matricula para o mesmo ContratoUi";
			matcher = REGEXP_MATRICULA_TITULAR.matcher(errorMessage);

			if (matcher.find()) {
				matricula = Long.parseLong(matcher.group(GROUP_MATRICULA_TITULAR));
				contratoUi = coParticipacaoContext
						.findContratoById(Long.parseLong(matcher.group(GROUP_CONTRATO_TITULAR)));

				friendlyMessage = String.format(
						"Existem Títulares com o mesmo número de Matricula[%d] para o mesmo ContratoUi[%s]",
						matricula,
						contratoUi.getCdContrato());
			}

			LOGGER.info("END");
			return friendlyMessage;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private static String createErrorDuplicatedDependente(String errorMessage) throws ServiceException {
		Matcher matcher;
		String friendlyMessage = null;
		String nameDependente;
		String cpf;

		try {
			LOGGER.info("BEGIN");

			matcher = REGEXP_DUPLICATED_DEPENDENTE_02.matcher(errorMessage);
			friendlyMessage = "Existem registros repetidos para o Dependente[%s] usando o CPF[%s]:";

			if (matcher.find()) {
				nameDependente = matcher.group(1);
				cpf = matcher.group(3);

				friendlyMessage = String.format(friendlyMessage, nameDependente, cpf);
			}

			LOGGER.info("END");
			return friendlyMessage;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

}
