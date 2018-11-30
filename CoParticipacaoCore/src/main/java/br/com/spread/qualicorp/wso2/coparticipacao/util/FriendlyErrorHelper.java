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

	private static final CharSequence ERROR_MATRICULA_TITULAR = "UN_TITULAR_01";

	private static final String ERROR_COMMUNICATION_LINK = "Communications link failure";
	
	private static final String ERROR_CONNECT_DATABASE="Unable to acquire JDBC Connection";

	private static final Pattern REGEXP_MATRICULA_TITULAR = Pattern
			.compile("Duplicate entry \\'([0-9]{1,10})\\-([0-9]{1,10})\\' for key \\'UN_TITULAR_02\\'");

	private static final int GROUP_MATRICULA_TITULAR = 2;

	private static final int GROUP_CONTRATO_TITULAR = 1;

	public static void createFriendlyErrorMessage(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoExecucaoUi arquivoExecucaoUi,
			String errorMessage) throws ServiceException {
		String friendlyMessage = null;
		ContratoUi contratoUi;
		Long matricula;
		Matcher matcher;

		try {
			LOGGER.info("BEGIN");

			if (StringUtils.contains(errorMessage, ERROR_MATRICULA_TITULAR)) {
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
			} else if (StringUtils.contains(errorMessage, ERROR_COMMUNICATION_LINK)) {
				friendlyMessage = "Consulta ao banco de dados excedeu o tempo limite";
			} else if (StringUtils.contains(errorMessage, ERROR_CONNECT_DATABASE)) {
				friendlyMessage = "Não foi possível conectar ao Banco de Dados";				
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

}
