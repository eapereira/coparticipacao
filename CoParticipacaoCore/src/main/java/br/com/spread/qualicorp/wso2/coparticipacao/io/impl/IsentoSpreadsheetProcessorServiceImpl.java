package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheetColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheetCols;
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
	protected Map<String, Object> readLine(
			SpreadsheetContext spreadsheetContext,
			Row row,
			CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		Map<String, Object> mapLine;
		String columnName = StringUtils.EMPTY;
		Object value;
		ArquivoInputSheetColsDef arquivoInputSheetColsDef;
		List<IsentoInputSheetCols> isentoInputSheetColss;
		Cell cell;

		try {
			LOGGER.info("BEGIN");
			mapLine = new HashMap<String, Object>();

			isentoInputSheetColss = coParticipacaoContext
					.listIsentoInputSheetColsBySheetId(coParticipacaoContext.getCurrentSheet());

			if (!isentoInputSheetColss.isEmpty()) {
				LOGGER.info("Isento sheet [{}] is mapped and will be loaded:", coParticipacaoContext.getCurrentSheet());

				for (IsentoInputSheetCols isentoInputSheetCols : isentoInputSheetColss) {
					LOGGER.debug(
							"Checking column[{}] with BeneficiarioIsentoColType[{}]:",
							isentoInputSheetCols.getArquivoInputSheetColsDef().getNameColumn(),
							isentoInputSheetCols.getBeneficiarioIsentoColType());

					if (isentoInputSheetCols.getBeneficiarioIsentoColType() != null) {
						arquivoInputSheetColsDef = isentoInputSheetCols.getArquivoInputSheetColsDef();

						LOGGER.debug("Loading Isento column [{}]", arquivoInputSheetColsDef.getNameColumn());

						cell = row.getCell(arquivoInputSheetColsDef.getOrdem());

						if (cell != null) {
							columnName = arquivoInputSheetColsDef.getNameColumn();

							LOGGER.info("Retrieving cell value for column [{}]:", columnName);
							value = getCellValue(spreadsheetContext, cell, arquivoInputSheetColsDef);

							LOGGER.info("Cell [{}] has value [{}]:", columnName, value);
							mapLine.put(columnName, value);
						} else {
							break;
						}
					}
				}
			}

			LOGGER.info("END");
			return mapLine;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
