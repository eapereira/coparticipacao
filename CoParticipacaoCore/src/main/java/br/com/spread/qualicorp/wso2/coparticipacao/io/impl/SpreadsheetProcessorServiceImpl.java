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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegisterColumn;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterColumnUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.ArquivoInputException;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.RestrictedValueException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.ProcessorListener;
import br.com.spread.qualicorp.wso2.coparticipacao.io.SpreadsheetProcessorListener;
import br.com.spread.qualicorp.wso2.coparticipacao.io.SpreadsheetProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.NumberUtils2;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class SpreadsheetProcessorServiceImpl extends AbstractFileProcessorImpl implements SpreadsheetProcessorService {

	private static final Logger LOGGER = LogManager.getLogger(SpreadsheetProcessorServiceImpl.class);

	private static final String EXCEL_97 = ".xls";

	private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

	public void readInputStream(CoParticipacaoContext coParticipacaoContext, ProcessorListener processorListener)
			throws ServiceException {
		Map<String, Object> mapLine;
		int currentLine = NumberUtils.INTEGER_ZERO;
		Workbook workbook;
		Sheet sheet;
		FormulaEvaluator formulaEvaluator;
		CreationHelper creationHelper;
		DataFormat dataFormat;
		CellStyle cellStyleDate;
		SpreadsheetContext spreadsheetContext;
		int sheetId = NumberUtils.INTEGER_MINUS_ONE;

		try {
			LOGGER.info("BEGIN");

			spreadsheetContext = new SpreadsheetContext();
			coParticipacaoContext.setSpreadsheetContext(spreadsheetContext);
			markProcessAsRunning(coParticipacaoContext);

			LOGGER.info(
					"Reading spreadsheet file[{}]:",
					coParticipacaoContext.getArquivoExecucaoUi().getNameArquivoInput());

			if (coParticipacaoContext.getFileName().toLowerCase().endsWith(EXCEL_97)) {
				workbook = new HSSFWorkbook(coParticipacaoContext.getFileInputStream());
			} else {
				workbook = new XSSFWorkbook(coParticipacaoContext.getFileInputStream());
			}

			LOGGER.info("Spreadsheet is ready to be processed:");

			if (workbook.getNumberOfSheets() >= 1) {
				processorListener.beforeProcess(coParticipacaoContext);

				formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

				/*
				 * Se aparecer uma célula com data, vamos alterar o seu formato
				 * para um que seja fácil para convertermos:
				 */
				creationHelper = workbook.getCreationHelper();
				dataFormat = creationHelper.createDataFormat();
				cellStyleDate = workbook.createCellStyle();
				cellStyleDate.setDataFormat(dataFormat.getFormat(DEFAULT_DATE_FORMAT));

				spreadsheetContext.setCellStyleDate(cellStyleDate);
				spreadsheetContext.setDataFormat(dataFormat);

				coParticipacaoContext.setFormulaEvaluator(formulaEvaluator);

				LOGGER.info("Reading spreadsheet lines:");

				for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
					sheet = workbook.getSheetAt(i);

					spreadsheetContext.setSheetName(sheet.getSheetName());

					LOGGER.info("Verifying if sheet[{}] can be loaded:", sheet.getSheetName());

					if ((!workbook.isSheetHidden(i) || workbook.isSheetVeryHidden(i))) {
						sheetId++;

						/*
						 * As vezes o usuário deixa pastas invisíveis na
						 * planilha, então vamos contas apenas as que são
						 * habilitadas:
						 */
						coParticipacaoContext.setCurrentSheet(sheetId);

						if (coParticipacaoContext.getCurrentSheet() > NumberUtils.INTEGER_ZERO
								&& !coParticipacaoContext.getContratoUi().isSpreadsheetAllPages()) {
							LOGGER.info(
									"ContratoUi[{}] only process the first sheet:",
									coParticipacaoContext.getContratoUi().getCdContrato());
							break;
						}

						if (processorListener instanceof SpreadsheetProcessorListener) {
							if (!((SpreadsheetProcessorListener) processorListener)
									.validateSheet(coParticipacaoContext)) {
								LOGGER.info("Ignoring sheet [{}]:", sheet.getSheetName());
								continue;
							}
						}

						LOGGER.info("Processing sheet [{}]:", sheet.getSheetName());
						LOGGER.info(
								"Sheet [{}] has a total number of Rows[{}]:",
								sheet.getSheetName(),
								sheet.getPhysicalNumberOfRows());

						for (Row row : sheet) {
							if (row.getRowNum() < coParticipacaoContext.getArquivoInputUi().getSkipLines()) {
								LOGGER.debug("Skipping line [{}]:", currentLine);
								continue;
							} else if (isEmptyLine(row)) {
								break;
							}

							LOGGER.info("Transforming line into Map:");
							validateRegisters(coParticipacaoContext, row);

							mapLine = readLine(coParticipacaoContext, row);

							if (!mapLine.isEmpty()) {
								LOGGER.info("Sending line to be processed by ProcessorListener:");

								coParticipacaoContext.setMapLine(mapLine);
								coParticipacaoContext.setCurrentLine(currentLine);

								processorListener.processLine(coParticipacaoContext);
							}

							currentLine++;
						}
					} else {
						LOGGER.debug("Hidden sheet found [{}]:", sheet.getSheetName());
					}
				}

				processorListener.afterProcess(coParticipacaoContext);
			} else {
				LOGGER.info(
						"Is required at least one sheet to be loaded is the file [{}]:",
						coParticipacaoContext.getFileName());
			}

			workbook.close();

			LOGGER.info("Total of lines processed [{}]:", currentLine);
			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private void validateRegisters(CoParticipacaoContext coParticipacaoContext, Row row) throws ServiceException {
		Cell cell;
		Integer cdRegister;
		ArquivoInputSheetUi arquivoInputSheetUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputSheetUi = coParticipacaoContext.getArquivoInputSheet();

			/**
			 * Se a pasta atual possuir mais de uma registro, devemos verificar
			 * se a linha que estamos lendo é a que representa este registro,
			 * para isto devemos utilizar a primeira coluna da pasta da
			 * planilha, se a coluna tiver o valor do CD-REGISTER do registro,
			 * vamos utiliza-lo.
			 */
			if (arquivoInputSheetUi.getRegisters().size() > NumberUtils.INTEGER_ONE) {
				LOGGER.debug("Spreadsheet has multiple RegisterUi:");
				cell = row.getCell(NumberUtils.INTEGER_ZERO);

				if (CellType.NUMERIC.equals(cell.getCellTypeEnum())) {
					cdRegister = (int) cell.getNumericCellValue();
					coParticipacaoContext.getSpreadsheetContext().setCdRegister(cdRegister);

					LOGGER.debug("Identifying RegisterUi with CD_REGISTER[{}]", cdRegister);
				}
			} else {
				LOGGER.debug("Spreadsheet with single RegisterUi");
				coParticipacaoContext.getSpreadsheetContext().setCdRegister(null);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private boolean isEmptyLine(Row row) throws ServiceException {
		Cell cell;

		try {
			LOGGER.info("BEGIN");

			for (int i = 0; i < row.getLastCellNum(); i++) {
				cell = row.getCell(i);

				if (cell != null) {
					if (!CellType.BLANK.equals(cell.getCellTypeEnum())) {
						return false;
					}
				}
			}

			LOGGER.info("END");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	protected Object getCellValue(SpreadsheetContext spreadsheetContext, Cell cell, RegisterColumn RegisterColumn)
			throws ServiceException {
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
				if (DateUtil.isCellDateFormatted(cell)) {
					value = DateUtils.dateToLocalDate(cell.getDateCellValue());
				} else {
					value = cell.getNumericCellValue();
				}
				break;
			case BLANK:
				value = StringUtils.EMPTY;
				break;
			default:
				value = StringUtils.EMPTY;
				break;
			}

			LOGGER.debug("Reading cell for field[{}] with value[{}]:", RegisterColumn.getNameColumn(), value);

			if (ColDefType.INT.equals(RegisterColumn.getType())) {
				value = clearMask(value);

				if (NumberUtils.isNumber(value.toString())) {
					value = Integer.parseInt(value.toString());
				} else {
					return NumberUtils.INTEGER_ZERO;
				}
			} else if (ColDefType.LONG.equals(RegisterColumn.getType())) {
				value = clearMask(value);

				if (NumberUtils.isNumber(value.toString())) {
					value = (long) Double.parseDouble(value.toString());
				} else {
					return NumberUtils.LONG_ZERO;
				}
			} else if (ColDefType.DOUBLE.equals(RegisterColumn.getType())) {
				value = clearDoubleMask(value);

				if (NumberUtils.isNumber(value.toString())) {
					value = BigDecimal.valueOf((Double) cell.getNumericCellValue());
				} else {
					return BigDecimal.ZERO;
				}
			} else if (ColDefType.DATE.equals(RegisterColumn.getType())) {
				cell.setCellStyle(spreadsheetContext.getCellStyleDate());

				if (value instanceof String) {
					if (StringUtils.isNotBlank(RegisterColumn.getLocalePattern())) {
						value = DateUtils.stringToDate(
								(String) value,
								RegisterColumn.getFormat(),
								RegisterColumn.getLocalePattern());
					} else {
						value = DateUtils.stringToDate((String) value, RegisterColumn.getFormat());
					}
				} else if (value instanceof Date) {
					value = DateUtils.dateToLocalDate((Date) value);
				}
			} else if (ColDefType.STRING.equals(RegisterColumn.getType())) {
				value = value.toString().trim().toUpperCase();
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
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

	@Override
	protected Map<String, Object> readLine(CoParticipacaoContext coParticipacaoContext) throws ArquivoInputException {
		// TODO Auto-generated method stub
		return null;
	}

	protected Map<String, Object> readLine(CoParticipacaoContext coParticipacaoContext, Row row)
			throws ServiceException {
		Map<String, Object> mapLine;
		// int cellId = NumberUtils.INTEGER_ZERO;
		String columnName = StringUtils.EMPTY;
		Object value;
		Cell cell;
		ContratoUi contratoUi;
		ArquivoInputSheetUi arquivoInputSheetUi;
		List<RegisterColumnUi> registerColumnUis;
		SpreadsheetContext spreadsheetContext;

		try {
			LOGGER.info("BEGIN");

			arquivoInputSheetUi = coParticipacaoContext.getArquivoInputSheet();
			spreadsheetContext = coParticipacaoContext.getSpreadsheetContext();

			mapLine = new HashMap<String, Object>();

			// cellId = NumberUtils.INTEGER_ZERO;
			registerColumnUis = coParticipacaoContext.getRegisterColumns();

			if (arquivoInputSheetUi != null) {
				LOGGER.info("Processing sheet[{}] columns:", spreadsheetContext.getSheetName());

				for (RegisterColumnUi registerColumnUi : registerColumnUis) {
					cell = row.getCell(registerColumnUi.getOrdem());

					if (cell != null) {
						columnName = registerColumnUi.getNameColumn();

						LOGGER.info("Retrieving cell value for column [{}]:", columnName);
						value = getExtendedCellValue(spreadsheetContext, cell, registerColumnUi);

						LOGGER.info("Cell [{}] has value [{}]:", columnName, value);
						mapLine.put(columnName, value);

						// cellId++;
					}
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
			RegisterColumnUi registerColumnUi) throws ServiceException {
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
				if (DateUtil.isCellDateFormatted(cell)) {
					value = DateUtils.dateToLocalDate(cell.getDateCellValue());
				} else {
					value = cell.getNumericCellValue();
				}
				break;
			case BLANK:
				value = StringUtils.EMPTY;
				break;
			default:
				value = StringUtils.EMPTY;
				break;
			}

			if (!validateRestrictedValue(registerColumnUi, value)) {
				throw new RestrictedValueException(
						"Ignoring line for restricted acceptable value[%s] at column[%s]:",
						value,
						registerColumnUi.getNameColumn());
			}

			LOGGER.debug("Converting value[{}]:", value);

			if (ColDefType.INT.equals(registerColumnUi.getType())) {
				value = clearMask(value);

				if (NumberUtils2.isNumber(value.toString())) {
					value = (int) Double.parseDouble(value.toString());
				} else {
					return NumberUtils.INTEGER_ZERO;
				}
			} else if (ColDefType.LONG.equals(registerColumnUi.getType())) {
				value = clearMask(value);

				if (NumberUtils2.isNumber(value.toString())) {
					value = (long) Double.parseDouble(value.toString());
				} else {
					return NumberUtils.LONG_ZERO;
				}
			} else if (ColDefType.DOUBLE.equals(registerColumnUi.getType())) {
				if (StringUtils.isNotBlank(registerColumnUi.getFormat())) {
					value = NumberUtils2.stringToDouble(value.toString(), registerColumnUi.getFormat());
				} else {
					value = clearDoubleMask(value);
				}

				value = BigDecimal.valueOf((Double) value);
			} else if (ColDefType.DATE.equals(registerColumnUi.getType())) {
				cell.setCellStyle(spreadsheetContext.getCellStyleDate());

				if (value instanceof String) {
					if (DateUtils.isNotZeroDate((String) value)) {
						if (StringUtils.isNotBlank(registerColumnUi.getLocalePattern())) {
							value = DateUtils.stringToDate(
									(String) value,
									registerColumnUi.getFormat(),
									registerColumnUi.getLocalePattern());
						} else {
							value = DateUtils.stringToDate((String) value, registerColumnUi.getFormat());
						}
					} else {
						value = null;
					}
				} else if (value instanceof Date) {
					value = DateUtils.dateToLocalDate((Date) value);
				}
			} else if (ColDefType.STRING.equals(registerColumnUi.getType())) {
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

	protected boolean validateRestrictedValue(RegisterColumnUi registerColumnUi, Object value) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (StringUtils.isNotBlank(registerColumnUi.getRestrictedValue())) {
				if (!registerColumnUi.getRestrictedValue().equals(value.toString())) {
					LOGGER.info(
							"Ignoring line for restricted acceptable value[{}] at column[{}]:",
							value,
							registerColumnUi.getNameColumn());
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
