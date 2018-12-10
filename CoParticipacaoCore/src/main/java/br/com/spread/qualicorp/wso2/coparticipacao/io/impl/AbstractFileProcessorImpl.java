package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.ArquivoInputException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.ProcessorListener;
import br.com.spread.qualicorp.wso2.coparticipacao.io.ProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

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
			throws ArquivoInputException {
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
			throw new ArquivoInputException(e);
		}
	}

	protected boolean isLineAccepted(CoParticipacaoContext coParticipacaoContext) {
		return true;
	}

	protected abstract Map<String, Object> readLine(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	protected Object stringToColumnValue(ArquivoInputColsDefUi arquivoInputColsDefUi, String columnValue)
			throws ServiceException {
		Object value = null;
		String tmp;

		LOGGER.info("BEGIN");

		tmp = StringUtils.replaceAll(columnValue, "'", "");
		tmp = tmp.trim();

		if (StringUtils.isNotBlank(tmp)) {
			if (ColDefType.INT.equals(arquivoInputColsDefUi.getType())) {
				value = Integer.parseInt(tmp);
			} else if (ColDefType.LONG.equals(arquivoInputColsDefUi.getType())) {
				value = stringToLong(tmp, arquivoInputColsDefUi);
			} else if (ColDefType.DOUBLE.equals(arquivoInputColsDefUi.getType())) {
				value = stringToBigDecimal(tmp, arquivoInputColsDefUi);
			} else if (ColDefType.DATE.equals(arquivoInputColsDefUi.getType())) {
				value = stringToDate(tmp, arquivoInputColsDefUi);
			} else {
				value = tmp.trim();
			}
		}

		LOGGER.info("END");
		return value;
	}

	protected Long stringToLong(String value, ArquivoInputColsDefUi arquivoInputColsDefUi) throws ServiceException {
		Long longValue = null;
		DecimalFormat decimalFormat;

		try {
			LOGGER.info("BEGIN");

			if (StringUtils.isNotBlank(arquivoInputColsDefUi.getFormat())) {
				decimalFormat = new DecimalFormat(arquivoInputColsDefUi.getFormat());

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

	protected BigDecimal stringToBigDecimal(String value, ArquivoInputColsDefUi arquivoInputColsDefUi)
			throws ServiceException {
		BigDecimal bdValue = null;
		DecimalFormat decimalFormat;
		String tmp;

		try {
			LOGGER.info("BEGIN");

			tmp = StringUtils.replaceAll(value, ",", ".");

			if (StringUtils.isNotBlank(arquivoInputColsDefUi.getFormat())) {
				decimalFormat = new DecimalFormat(arquivoInputColsDefUi.getFormat());
				bdValue = BigDecimal.valueOf(decimalFormat.parse(tmp).doubleValue());
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

	protected LocalDate stringToDate(String value, ArquivoInputColsDefUi arquivoInputColsDefUi)
			throws ServiceException {
		DateTimeFormatter dateTimeFormatter;
		String formatPattern = "dd/MM/yyyy";
		LocalDate localDate;
		String tmp;

		try {
			LOGGER.info("BEGIN");

			if (StringUtils.isNotBlank(arquivoInputColsDefUi.getFormat())) {
				formatPattern = arquivoInputColsDefUi.getFormat();
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
}
