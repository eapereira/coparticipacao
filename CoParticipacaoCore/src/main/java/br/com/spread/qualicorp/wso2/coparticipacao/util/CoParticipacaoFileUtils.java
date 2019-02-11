package br.com.spread.qualicorp.wso2.coparticipacao.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CoParticipacaoFileUtils {
	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoFileUtils.class);

	public static String createFileName(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoOutputUi arquivoOutputUi,
			ContratoUi contratoUi) throws ServiceException {
		String fileNameFormat;
		LocalDate currentDate;

		try {
			LOGGER.info("BEGIN");

			currentDate = LocalDate.now();

			fileNameFormat = arquivoOutputUi.getNameArquivoFormat();

			fileNameFormat = StringUtils.replace(fileNameFormat, "{CC}", contratoUi.getCdContrato());
			fileNameFormat = StringUtils.replace(fileNameFormat, "{YYYY}", String.valueOf(currentDate.getYear()));
			fileNameFormat = StringUtils.replace(
					fileNameFormat,
					"{MM}",
					StringUtils.leftPad(String.valueOf(currentDate.getMonthValue()), 2, "0"));
			fileNameFormat = StringUtils.replace(
					fileNameFormat,
					"{DD}",
					StringUtils.leftPad(String.valueOf(currentDate.getDayOfMonth()), 2, "0"));

			LOGGER.info("Using ArquivoOutput's name [{}]:", fileNameFormat);
			LOGGER.info("END");
			return fileNameFormat;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public static void close(FileOutputStream fileOutputStream) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			fileOutputStream.close();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public static void close(FileInputStream fileInputStream) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			fileInputStream.close();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

}
