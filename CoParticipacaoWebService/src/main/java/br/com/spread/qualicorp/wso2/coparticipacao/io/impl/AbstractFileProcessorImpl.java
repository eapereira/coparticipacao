package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.icu.text.DecimalFormat;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.io.ProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class AbstractFileProcessorImpl implements ProcessorService {
	private static final Logger LOGGER = LogManager
			.getLogger(FixedLengthProcessorServiceImpl.class);

	public void readInputStream(
			CoParticipacaoContext coParticipacaoContext,
			ProcessorListener processorListener) throws ServiceException {
		BufferedReader bufferedReader;
		String line;
		Map<String, Object> mapLine;
		int currentLine = NumberUtils.INTEGER_ZERO;

		try {
			LOGGER.info("BEGIN");
			bufferedReader = new BufferedReader(
					new InputStreamReader(
							coParticipacaoContext.getFileInputStream()));

			/*
			 * Permite o processo fazer algumas operações antes de carregar os
			 * registros:
			 */
			processorListener.beforeProcess(coParticipacaoContext);

			while ((line = bufferedReader.readLine()) != null) {
				currentLine++;

				if (currentLine <= coParticipacaoContext.getArquivoInputUi()
						.getSkipLines()) {
					LOGGER.debug("Skipping line [{}]:", currentLine);
					continue;
				}

				LOGGER.debug(line);

				coParticipacaoContext.setLine(line);

				if (processorListener
						.validateLine(line, coParticipacaoContext)) {
					LOGGER.info("Transforming line into Map:");
					mapLine = readLine(coParticipacaoContext);

					LOGGER.info(
							"Sending line to be processed by ProcessorListener:");

					coParticipacaoContext.setMapLine(mapLine);
					coParticipacaoContext.setCurrentLine(currentLine);

					processorListener.processLine(coParticipacaoContext);
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

	protected abstract Map<String, Object> readLine(
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	protected Object stringToColumnValue(
			ArquivoInputColsDefUi arquivoInputColsDefUi,
			String columnValue) throws ServiceException {
		Object value = null;

		LOGGER.info("BEGIN");

		if (StringUtils.isNotBlank(columnValue)) {
			if (ColDefType.INT.equals(arquivoInputColsDefUi.getType())) {
				value = Integer.parseInt(columnValue);
			} else if (ColDefType.LONG
					.equals(arquivoInputColsDefUi.getType())) {
				value = stringToLong(columnValue, arquivoInputColsDefUi);
			} else if (ColDefType.DOUBLE
					.equals(arquivoInputColsDefUi.getType())) {
				value = stringToBigDecimal(columnValue, arquivoInputColsDefUi);
			} else if (ColDefType.DATE
					.equals(arquivoInputColsDefUi.getType())) {
				value = stringToDate(columnValue, arquivoInputColsDefUi);
			} else {
				value = columnValue;
			}
		}

		LOGGER.info("END");
		return value;
	}

	protected Long stringToLong(
			String value,
			ArquivoInputColsDefUi arquivoInputColsDefUi)
			throws ServiceException {
		Long longValue = null;
		DecimalFormat decimalFormat;

		try {
			LOGGER.info("BEGIN");

			if (StringUtils.isNotBlank(arquivoInputColsDefUi.getFormat())) {
				decimalFormat = new DecimalFormat(
						arquivoInputColsDefUi.getFormat());

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

	protected BigDecimal stringToBigDecimal(
			String value,
			ArquivoInputColsDefUi arquivoInputColsDefUi)
			throws ServiceException {
		Integer intValue = Integer.parseInt(value);
		BigDecimal bdValue = new BigDecimal(intValue / 100);
		DecimalFormat decimalFormat;

		try {
			LOGGER.info("BEGIN");

			if (StringUtils.isNotBlank(arquivoInputColsDefUi.getFormat())) {
				decimalFormat = new DecimalFormat(
						arquivoInputColsDefUi.getFormat());
				bdValue = BigDecimal
						.valueOf(decimalFormat.parse(value).doubleValue());
			}

			LOGGER.info("END");
			return bdValue;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected LocalDate stringToDate(
			String value,
			ArquivoInputColsDefUi arquivoInputColsDefUi)
			throws ServiceException {
		DateTimeFormatter dateTimeFormatter;
		String formatPattern = "dd/MM/yyyy";
		LocalDate localDate;

		if (StringUtils.isNotBlank(arquivoInputColsDefUi.getFormat())) {
			formatPattern = arquivoInputColsDefUi.getFormat();
		}

		dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
		localDate = LocalDate.parse(value, dateTimeFormatter);

		return localDate;
	}
}
