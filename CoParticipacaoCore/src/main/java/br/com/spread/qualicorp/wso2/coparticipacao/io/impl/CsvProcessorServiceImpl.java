package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterColumnUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.ArquivoInputException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.CsvProcessorService;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class CsvProcessorServiceImpl extends AbstractFileProcessorImpl implements CsvProcessorService {

	private static final Logger LOGGER = LogManager.getLogger(CsvProcessorServiceImpl.class);

	@Override
	protected Map<String, Object> readLine(CoParticipacaoContext coParticipacaoContext) throws ArquivoInputException {
		Map<String, Object> mapLine;
		String columnValue;
		String[] lineColumns;
		int currentCol = NumberUtils.INTEGER_ZERO;
		List<RegisterColumnUi> registerColumnUis;

		try {
			LOGGER.info("BEGIN");

			lineColumns = coParticipacaoContext.getLine().split(";");

			mapLine = new HashMap<String, Object>();

			LOGGER.debug("Reading line [{}]:", coParticipacaoContext.getLine());

			registerColumnUis = coParticipacaoContext.getRegisterColumns();

			for (RegisterColumnUi registerColumnUi : registerColumnUis) {
				if (currentCol < lineColumns.length) {
					columnValue = lineColumns[currentCol];

					if (StringUtils.isNotBlank(columnValue)) {
						LOGGER.info("Column [{}] with value [{}]:", registerColumnUi.getNameColumn(), columnValue);

						mapLine.put(
								registerColumnUi.getNameColumn(),
								stringToColumnValue((RegisterColumnUi) registerColumnUi, columnValue));
					}
				}

				currentCol++;
			}

			LOGGER.info("END");
			return mapLine;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ArquivoInputException(e);
		}
	}

	@Override
	protected boolean isLineAccepted(CoParticipacaoContext coParticipacaoContext) {
		String tmp = coParticipacaoContext.getLine().replaceAll(";", StringUtils.EMPTY);

		if (StringUtils.isNotBlank(tmp)) {
			return true;
		}

		return false;
	}
}
