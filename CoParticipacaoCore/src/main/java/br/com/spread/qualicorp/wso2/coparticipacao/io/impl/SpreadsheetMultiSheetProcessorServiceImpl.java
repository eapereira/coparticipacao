package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheetColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.RestrictedValueException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.SpreadsheetMultiSheetProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Qualifier(value = "SpreadsheetMultiSheetProcessorService")
@Service
public class SpreadsheetMultiSheetProcessorServiceImpl extends SpreadsheetProcessorServiceImpl
		implements SpreadsheetMultiSheetProcessorService {

	private static final Logger LOGGER = LogManager.getLogger(SpreadsheetMultiSheetProcessorServiceImpl.class);

	protected Map<String, Object> readLine(
			SpreadsheetContext spreadsheetContext,
			Row row,
			CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		Map<String, Object> mapLine;
		List<ArquivoInputSheetUi> arquivoInputSheetUis;
		// int cellId = NumberUtils.INTEGER_ZERO;
		String columnName = StringUtils.EMPTY;
		Object value;
		ArquivoInputSheetColsDefUi arquivoInputSheetColsDefUi;
		Cell cell;
		ContratoUi contratoUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputSheetUis = coParticipacaoContext.getArquivoInputSheetUis();

			mapLine = new HashMap<String, Object>();

			// cellId = NumberUtils.INTEGER_ZERO;

			for (ArquivoInputSheetUi arquivoInputSheetUi : arquivoInputSheetUis) {
				if (arquivoInputSheetUi.getSheetId().equals(coParticipacaoContext.getCurrentSheet())) {

					LOGGER.info("Processing sheet[{}] columns:", spreadsheetContext.getSheetName());

					contratoUi = (ContratoUi) arquivoInputSheetUi.getContrato();

					if (contratoUi != null) {
						LOGGER.info(
								"Using default ContratoUi[{}] for all registers in this ArquivoInputSheet:",
								contratoUi.getCdContrato());
						coParticipacaoContext.setContratoSheetRegisters(contratoUi);
					}

					for (ArquivoInputSheetColsDef arquivoInputSheetColsDef : arquivoInputSheetUi
							.getArquivoInputSheetColsDefs()) {
						arquivoInputSheetColsDefUi = (ArquivoInputSheetColsDefUi) arquivoInputSheetColsDef;

						cell = row.getCell(arquivoInputSheetColsDefUi.getOrdem());

						if (cell != null) {
							columnName = arquivoInputSheetColsDefUi.getNameColumn();

							LOGGER.info("Retrieving cell value for column [{}]:", columnName);
							value = getExtendedCellValue(spreadsheetContext, cell, arquivoInputSheetColsDefUi);

							LOGGER.info("Cell [{}] has value [{}]:", columnName, value);
							mapLine.put(columnName, value);

							// cellId++;
						} else {
							break;
						}
					}

					break;
				}
			}

			LOGGER.info("END");
			return mapLine;
		} catch (RestrictedValueException e) {
			LOGGER.info(e.getMessage());
			return new HashMap<String, Object>();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected Object getExtendedCellValue(
			SpreadsheetContext spreadsheetContext,
			Cell cell,
			ArquivoInputSheetColsDefUi arquivoInputSheetColsDefUi) throws ServiceException {
		Object value;

		try {
			LOGGER.info("BEGIN");

			value = null;

			switch (cell.getCellTypeEnum()) {
			case BOOLEAN:
				value = cell.getBooleanCellValue();
				break;
			case STRING:
				value = cell.getRichStringCellValue().getString();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					value = DateUtils.dateToLocalDate(cell.getDateCellValue());
				} else {
					value = cell.getNumericCellValue();
				}
				break;
			case FORMULA:
				value = cell.getNumericCellValue();
				break;
			case BLANK:
				value = StringUtils.EMPTY;
				break;
			default:
				value = StringUtils.EMPTY;
				break;
			}

			if (validateRestrictedValue(arquivoInputSheetColsDefUi, value)) {
				throw new RestrictedValueException(
						"Ignoring line for restricted acceptable value[%s] at column[%s]:",
						value,
						arquivoInputSheetColsDefUi.getNameColumn());
			}

			if (ColDefType.INT.equals(arquivoInputSheetColsDefUi.getType())) {
				value = clearMask(value);

				if (NumberUtils.isDigits(value.toString())) {
					value = Integer.parseInt(value.toString());
				} else {
					return NumberUtils.INTEGER_ZERO;
				}
			} else if (ColDefType.LONG.equals(arquivoInputSheetColsDefUi.getType())) {
				value = clearMask(value);

				if (NumberUtils.isDigits(value.toString())) {
					value = (long) Double.parseDouble(value.toString());
				} else {
					return NumberUtils.LONG_ZERO;
				}
			} else if (ColDefType.DOUBLE.equals(arquivoInputSheetColsDefUi.getType())) {
				if (NumberUtils.isDigits(value.toString())) {
					value = BigDecimal.valueOf((Double) cell.getNumericCellValue());
				} else {
					return BigDecimal.ZERO;
				}
			} else if (ColDefType.DATE.equals(arquivoInputSheetColsDefUi.getType())) {
				cell.setCellStyle(spreadsheetContext.getCellStyleDate());

				if (value instanceof String) {
					if (StringUtils.isNotBlank(arquivoInputSheetColsDefUi.getLocalePattern())) {
						value = DateUtils.stringToDate(
								(String) value,
								arquivoInputSheetColsDefUi.getFormat(),
								arquivoInputSheetColsDefUi.getLocalePattern());
					} else {
						value = DateUtils.stringToDate((String) value, arquivoInputSheetColsDefUi.getFormat());
					}
				} else if (value instanceof Date) {
					value = DateUtils.dateToLocalDate((Date) value);
				}
			} else if (ColDefType.STRING.equals(arquivoInputSheetColsDefUi.getType())) {
				value = value.toString().trim().toUpperCase();
			}

			LOGGER.info("END");
			return value;
		} catch (RestrictedValueException e) {
			LOGGER.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected boolean validateRestrictedValue(ArquivoInputSheetColsDefUi arquivoInputSheetColsDefUi, Object value)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (StringUtils.isNotBlank(arquivoInputSheetColsDefUi.getRestrictedValue())) {
				if (arquivoInputSheetColsDefUi.getRestrictedValue().equals(value.toString())) {
					LOGGER.info(
							"Ignoring line for restricted acceptable value[{}] at column[{}]:",
							value,
							arquivoInputSheetColsDefUi.getNameColumn());
					return false;
				}
			}

			LOGGER.info("END");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
