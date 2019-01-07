package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegisterColumn;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterColumnUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.ArquivoInputException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.FixedLengthProcessorService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class FixedLengthProcessorServiceImpl extends AbstractFileProcessorImpl implements FixedLengthProcessorService {

	private static final Logger LOGGER = LogManager.getLogger(FixedLengthProcessorServiceImpl.class);

	@Override
	protected Map<String, Object> readLine(CoParticipacaoContext coParticipacaoContext) throws ArquivoInputException {
		Map<String, Object> mapLine;
		String columnValue;
		int pos = NumberUtils.INTEGER_ZERO;
		List<RegisterColumnUi> registerColumnUis;

		try {
			LOGGER.info("BEGIN");
			mapLine = new HashMap<String, Object>();

			registerColumnUis = coParticipacaoContext.getRegisterColumns();

			for (RegisterColumnUi registerColumnUi : registerColumnUis) {
				columnValue = coParticipacaoContext.getLine()
						.substring(pos, pos + registerColumnUi.getLength());

				LOGGER.debug("Column [{}] with value [{}]:", registerColumnUi.getNameColumn(), columnValue);

				mapLine.put(
						registerColumnUi.getNameColumn(),
						stringToColumnValue(
								(RegisterColumnUi) registerColumnUi,
								StringUtils.trim(columnValue)));

				pos += registerColumnUi.getLength();
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
		LOGGER.info("BEGIN");
		ArquivoInputUi arquivoInputUi = coParticipacaoContext.getArquivoInputUi();

		if (coParticipacaoContext.getLine().length() == arquivoInputUi.getDefaultLineLength()) {
			LOGGER.info("END");
			return true;
		}

		LOGGER.info("END");
		return false;
	}

}
