package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.io.IsentoSpreadsheetProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Qualifier(value = "IsentoSpreadsheetProcessorService")
@Service
public class IsentoSpreadsheetProcessorServiceImpl extends SpreadsheetProcessorServiceImpl
		implements IsentoSpreadsheetProcessorService {

	private static final Logger LOGGER = LogManager.getLogger(IsentoSpreadsheetProcessorServiceImpl.class);

	@Override
	protected Map<String, Object> readLine(Row row, CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		Map<String, Object> mapLine;
		String columnName = StringUtils.EMPTY;
		Object value;
		ArquivoInputColsDef arquivoInputColsDef;
		Cell cell;

		try {
			LOGGER.info("BEGIN");
			mapLine = new HashMap<String, Object>();

			for (IsentoInputSheetUi isentoInputSheetUi : coParticipacaoContext.getIsentoInputSheetUis()) {
				if (isAcceptSheet(coParticipacaoContext, isentoInputSheetUi.getSheetId())) {
					LOGGER.info(
							"Isento sheet [{}] is mapped and will be loaded:",
							coParticipacaoContext.getCurrentSheet());

					for (IsentoInputSheetCols isentoInputSheetCols : isentoInputSheetUi.getIsentoInputSheetCols()) {
						if (isentoInputSheetCols.getBeneficiarioIsentoColType() != null) {
							arquivoInputColsDef = isentoInputSheetCols.getArquivoInputColsDef();

							LOGGER.debug(
									"Loading Isento column [{}]",
									isentoInputSheetCols.getBeneficiarioIsentoColType().getDescription());

							cell = row.getCell(isentoInputSheetCols.getOrdem());

							if (cell != null) {
								columnName = arquivoInputColsDef.getNameColumn();

								LOGGER.info("Retrieving cell value for column [{}]:", columnName);
								value = getCellValue(cell, arquivoInputColsDef);

								LOGGER.info("Cell [{}] has value [{}]:", columnName, value);
								mapLine.put(columnName, value);
							} else {
								break;
							}
						}
					}

					break;
				}
			}

			LOGGER.info("END");
			return mapLine;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private boolean isAcceptSheet(CoParticipacaoContext coParticipacaoContext, int sheetId) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (coParticipacaoContext.getCurrentSheet().equals(sheetId)
					|| Boolean.TRUE.equals(coParticipacaoContext.getContratoUi().isSpreadsheetAllPages())) {
				return true;
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}

	}
}
