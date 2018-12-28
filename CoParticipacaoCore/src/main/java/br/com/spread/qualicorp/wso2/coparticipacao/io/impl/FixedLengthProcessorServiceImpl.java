package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheetColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetColsDefUi;
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
		List<ArquivoInputSheetColsDef> arquivoInputSheetColsDefs;

		try {
			LOGGER.info("BEGIN");
			mapLine = new HashMap<String, Object>();

			arquivoInputSheetColsDefs = coParticipacaoContext
					.listArquivoInputSheetColsBySheetId(coParticipacaoContext.getCurrentSheet());

			for (ArquivoInputSheetColsDef arquivoInputSheetColsDef : arquivoInputSheetColsDefs) {
				columnValue = coParticipacaoContext.getLine()
						.substring(pos, pos + arquivoInputSheetColsDef.getLength());

				LOGGER.debug("Column [{}] with value [{}]:", arquivoInputSheetColsDef.getNameColumn(), columnValue);

				mapLine.put(
						arquivoInputSheetColsDef.getNameColumn(),
						stringToColumnValue(
								(ArquivoInputSheetColsDefUi) arquivoInputSheetColsDef,
								StringUtils.trim(columnValue)));

				pos += arquivoInputSheetColsDef.getLength();
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
