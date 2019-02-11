package br.com.spread.qualicorp.wso2.coparticipacao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CoParticipacaoProperties {

	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoProperties.class);

	private static final CoParticipacaoProperties CO_PARTICIPACAO_PROPERTIES = new CoParticipacaoProperties();

	public static final String BATCH_SIZE = "batch.size";

	private Properties properties;

	private CoParticipacaoProperties() {

	}

	private void init() throws CoParticipacaoException {
		File file;
		StringBuilder sb;

		LOGGER.info("BEGIN");

		try {
			if (properties == null) {
				sb = new StringBuilder();
				sb.append(System.getProperty("user.home"));
				sb.append(File.separator);
				sb.append(".coparticipacao");
				sb.append(File.separator);
				sb.append("coparticipacao.properties");

				file = new File(sb.toString());

				properties = new Properties();

				if (file.exists()) {
					properties.load(new FileInputStream(file));
				} else {
					properties.store(new FileOutputStream(file), "Arquivo para as propriedades de teste do projeto:");

					throw new CoParticipacaoException(
							"É necessário preencher as propriedades em coparticipacao.properties para realizar os testes.");
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	private String getPropertyAsString(String propertyName) throws CoParticipacaoException {
		if (properties == null) {
			init();
		}

		return properties.getProperty(propertyName);
	}

	public static String getProperty(String propertyName) throws CoParticipacaoException {
		return CO_PARTICIPACAO_PROPERTIES.getPropertyAsString(propertyName);
	}

	public static int getPropertyAsInteger(String propertyName) throws CoParticipacaoException {
		String value = getProperty(propertyName);

		if (StringUtils.isNotBlank(value)) {
			return Integer.valueOf(value);
		}

		return NumberUtils.INTEGER_ZERO;
	}
}
