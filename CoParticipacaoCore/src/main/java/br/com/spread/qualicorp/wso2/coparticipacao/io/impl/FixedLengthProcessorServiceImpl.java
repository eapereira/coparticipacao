package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.io.FixedLengthProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class FixedLengthProcessorServiceImpl extends AbstractFileProcessorImpl implements FixedLengthProcessorService {

	private static final Logger LOGGER = LogManager.getLogger(FixedLengthProcessorServiceImpl.class);

	@Override
	protected Map<String, Object> readLine(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		Map<String, Object> mapLine;
		String columnValue;
		int pos = NumberUtils.INTEGER_ZERO;

		try {
			LOGGER.info("BEGIN");
			mapLine = new HashMap<String, Object>();

			for (ArquivoInputColsDefUi arquivoInputColsDefUi : coParticipacaoContext.getArquivoInputColsDefUis()) {
				columnValue = coParticipacaoContext.getLine().substring(pos, pos + arquivoInputColsDefUi.getLength());

				LOGGER.debug("Column [{}] with value [{}]:", arquivoInputColsDefUi.getNameColumn(), columnValue);

				mapLine.put(
						arquivoInputColsDefUi.getNameColumn(),
						stringToColumnValue(arquivoInputColsDefUi, StringUtils.trim(columnValue)));

				pos += arquivoInputColsDefUi.getLength();
			}

			LOGGER.info("END");
			return mapLine;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
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
