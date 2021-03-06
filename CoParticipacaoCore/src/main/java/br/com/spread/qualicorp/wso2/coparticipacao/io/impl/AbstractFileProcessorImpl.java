package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterColumnUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.ArquivoInputException;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.ProcessorListener;
import br.com.spread.qualicorp.wso2.coparticipacao.io.ProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.NumberUtils2;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class AbstractFileProcessorImpl implements ProcessorService {
	private static final Logger LOGGER = LogManager.getLogger(FixedLengthProcessorServiceImpl.class);

	@Autowired
	private ArquivoExecucaoService arquivoExecucaoService;

	protected void markProcessAsRunning(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.RUNNING);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public void readInputStream(CoParticipacaoContext coParticipacaoContext, ProcessorListener processorListener)
			throws ServiceException {
		BufferedReader bufferedReader;
		String line;
		Map<String, Object> mapLine;
		int currentLine = NumberUtils.INTEGER_ZERO;

		try {
			LOGGER.info("BEGIN");

			markProcessAsRunning(coParticipacaoContext);

			bufferedReader = new BufferedReader(new InputStreamReader(coParticipacaoContext.getFileInputStream()));

			/*
			 * Permite o processo fazer algumas operações antes de carregar os
			 * registros:
			 */
			processorListener.beforeProcess(coParticipacaoContext);

			while ((line = bufferedReader.readLine()) != null) {
				currentLine++;

				if (currentLine <= coParticipacaoContext.getArquivoInputUi().getSkipLines()) {
					LOGGER.debug("Skipping line [{}]:", currentLine);
					continue;
				}

				LOGGER.debug(line);

				coParticipacaoContext.setLine(line);
				coParticipacaoContext.setCurrentSheet(NumberUtils.INTEGER_ZERO);

				if (processorListener.validateLine(line, coParticipacaoContext)) {
					if (isLineAccepted(coParticipacaoContext)) {
						LOGGER.info("Transforming line into Map:");
						mapLine = readLine(coParticipacaoContext);

						LOGGER.info("Sending line to be processed by ProcessorListener:");

						coParticipacaoContext.setMapLine(mapLine);
						coParticipacaoContext.setCurrentLine(currentLine);

						processorListener.processLine(coParticipacaoContext);
					}
				}
			}

			// Agora o processo pode finalizar com os registros carregados:
			processorListener.afterProcess(coParticipacaoContext);

			LOGGER.info("Total of lines processed [{}]:", currentLine);
			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected boolean isLineAccepted(CoParticipacaoContext coParticipacaoContext) {
		return true;
	}

	protected abstract Map<String, Object> readLine(CoParticipacaoContext coParticipacaoContext)
			throws ArquivoInputException;

	protected Object stringToColumnValue(RegisterColumnUi registerColumnUi, String columnValue)
			throws ServiceException {
		Object value = null;
		String tmp;

		LOGGER.info("BEGIN");

		tmp = StringUtils.replaceAll(columnValue, "'", "");
		tmp = tmp.trim();

		tmp = validateRegexpColumn(registerColumnUi, tmp);

		if (StringUtils.isNotBlank(tmp)) {
			if (ColDefType.INT.equals(registerColumnUi.getType())) {
				value = Integer.parseInt(tmp);
			} else if (ColDefType.LONG.equals(registerColumnUi.getType())) {
				value = stringToLong(tmp, registerColumnUi);
			} else if (ColDefType.DOUBLE.equals(registerColumnUi.getType())) {
				value = stringToBigDecimal(tmp, registerColumnUi);
			} else if (ColDefType.DATE.equals(registerColumnUi.getType())) {
				value = stringToDate(tmp, registerColumnUi);
			} else {
				value = tmp.trim();
			}
		}

		LOGGER.info("END");
		return value;
	}

	protected String validateRegexpColumn(RegisterColumnUi registerColumnUi, String columnValue)
			throws ServiceException {
		String value = columnValue;
		Matcher matcher;
		Pattern pattern;

		try {
			LOGGER.info("BEGIN");

			if (StringUtils.isNotBlank(columnValue)) {
				if (StringUtils.isNotBlank(registerColumnUi.getRegexpValue())) {
					pattern = Pattern.compile(registerColumnUi.getRegexpValue());
					matcher = pattern.matcher(columnValue);

					LOGGER.debug(
							"Validating value[{}] against REGEXP[{}]:",
							columnValue,
							registerColumnUi.getRegexpValue());

					if (matcher.find()) {
						value = matcher.group(registerColumnUi.getRegexpGroupValue());
						LOGGER.debug("Extracted value[{}] from column:", value);
					}
				}
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected Long stringToLong(String value, RegisterColumnUi registerColumnUi) throws ServiceException {
		Long longValue = null;
		DecimalFormat decimalFormat;

		try {
			LOGGER.info("BEGIN");

			if (StringUtils.isNotBlank(registerColumnUi.getFormat())) {
				decimalFormat = new DecimalFormat(registerColumnUi.getFormat());

				longValue = decimalFormat.parse(value).longValue();
			} else {
				longValue = Long.parseLong(value);
			}

			LOGGER.info("END");
			return longValue;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected BigDecimal stringToBigDecimal(String value, RegisterColumnUi registerColumnUi) throws ServiceException {
		BigDecimal bdValue = null;
		String tmp;

		try {
			LOGGER.info("BEGIN");

			tmp = StringUtils.replaceAll(value, ",", ".");

			if (StringUtils.isNotBlank(registerColumnUi.getFormat())) {
				bdValue = BigDecimal.valueOf(NumberUtils2.stringToDouble(value, registerColumnUi.getFormat()));
			} else {
				bdValue = new BigDecimal(tmp);
			}

			LOGGER.info("END");
			return bdValue;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected LocalDate stringToDate(String value, RegisterColumnUi registerColumnUi) throws ServiceException {
		DateTimeFormatter dateTimeFormatter;
		String formatPattern = "dd/MM/yyyy";
		LocalDate localDate;
		String tmp;

		try {
			LOGGER.info("BEGIN");

			if (StringUtils.isNotBlank(registerColumnUi.getFormat())) {
				formatPattern = registerColumnUi.getFormat();
			}

			/*
			 * Removendo caracteres estranhos que são deixados junto com a data
			 * em alguns clientes:
			 */
			tmp = StringUtils.replaceAll(value, "(\\|)|(I)", StringUtils.EMPTY);

			dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
			localDate = LocalDate.parse(tmp, dateTimeFormatter);

			LOGGER.info("END");
			return localDate;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}

	}

	protected String clearMask(Object value) {
		String strValue;

		if (value instanceof String) {
			strValue = ((String) value).trim();

			if (StringUtils.isNotBlank(strValue)) {
				strValue = StringUtils.replaceAll(strValue, "(\\.|\\-|\\'|/|\\W)", StringUtils.EMPTY);
				return strValue;
			} else {
				return NumberUtils.INTEGER_ZERO.toString();
			}
		}

		return value.toString();
	}

	protected Double clearDoubleMask(Object value) throws CoParticipacaoException {
		String strValue;

		LOGGER.debug("clearDoubleMask received value[{}]:", value);

		if (value instanceof String) {
			strValue = ((String) value).trim();

			if (StringUtils.isNotBlank(strValue)) {
				strValue = StringUtils.replaceAll(strValue, "(\\'|/)", StringUtils.EMPTY);

				return NumberUtils2.stringToDouble((String) value);
			}

			return NumberUtils.DOUBLE_ZERO;
		}

		return Double.valueOf(value.toString());
	}
}
