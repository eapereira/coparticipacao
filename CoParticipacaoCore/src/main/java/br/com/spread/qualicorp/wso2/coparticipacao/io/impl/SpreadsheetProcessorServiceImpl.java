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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.io.SpreadsheetProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Qualifier(value = "SpreadsheetProcessorService")
@Service
public class SpreadsheetProcessorServiceImpl extends AbstractFileProcessorImpl implements SpreadsheetProcessorService {

	private static final Logger LOGGER = LogManager.getLogger(SpreadsheetProcessorServiceImpl.class);

	private static final String EXCEL_97 = ".xls";

	private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

	private DataFormat dataFormat;

	private CellStyle cellStyleDate;

	public void readInputStream(CoParticipacaoContext coParticipacaoContext, ProcessorListener processorListener)
			throws ServiceException {
		Map<String, Object> mapLine;
		int currentLine = NumberUtils.INTEGER_ZERO;
		Workbook workbook;
		Sheet sheet;
		FormulaEvaluator formulaEvaluator;
		CreationHelper creationHelper;
		int sheetIndex = NumberUtils.INTEGER_ZERO;

		try {
			LOGGER.info("BEGIN");

			markProcessAsRunning(coParticipacaoContext);

			if (coParticipacaoContext.getFileName().toLowerCase().endsWith(EXCEL_97)) {
				workbook = new HSSFWorkbook(coParticipacaoContext.getFileInputStream());
			} else {
				workbook = new XSSFWorkbook(coParticipacaoContext.getFileInputStream());
			}

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

				coParticipacaoContext.setFormulaEvaluator(formulaEvaluator);

				for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
					sheet = workbook.getSheetAt(i);
					coParticipacaoContext.setCurrentSheet(i);

					if ((!workbook.isSheetHidden(i) || workbook.isSheetVeryHidden(i))) {
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

						coParticipacaoContext.setCurrentSheet(sheetIndex);

						for (Row row : sheet) {
							if (row.getRowNum() < coParticipacaoContext.getArquivoInputUi().getSkipLines()) {
								LOGGER.debug("Skipping line [{}]:", currentLine);
								continue;
							} else if (isEmptyLine(row)) {
								break;
							}

							LOGGER.info("Transforming line into Map:");
							mapLine = readLine(row, coParticipacaoContext);

							LOGGER.info("Sending line to be processed by ProcessorListener:");

							coParticipacaoContext.setMapLine(mapLine);
							coParticipacaoContext.setCurrentLine(currentLine);

							processorListener.processLine(coParticipacaoContext);

							currentLine++;
						}

						sheetIndex++;
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

	protected Map<String, Object> readLine(Row row, CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		Map<String, Object> mapLine;
		List<ArquivoInputColsDefUi> arquivoInputColsDefUis;
		//int cellId = NumberUtils.INTEGER_ZERO;
		String columnName = StringUtils.EMPTY;
		Object value;
		ArquivoInputColsDef arquivoInputColsDef;
		Cell cell;

		try {
			LOGGER.info("BEGIN");

			arquivoInputColsDefUis = coParticipacaoContext.getArquivoInputColsDefUis();

			mapLine = new HashMap<String, Object>();

			//cellId = NumberUtils.INTEGER_ZERO;

			for (ArquivoInputColsDef colsDef : arquivoInputColsDefUis) {
				arquivoInputColsDef = colsDef;

				cell = row.getCell(arquivoInputColsDef.getOrdem());

				if (cell != null) {
					columnName = arquivoInputColsDef.getNameColumn();

					LOGGER.info("Retrieving cell value for column [{}]:", columnName);
					value = getCellValue(cell, arquivoInputColsDef);

					LOGGER.info("Cell [{}] has value [{}]:", columnName, value);
					mapLine.put(columnName, value);

					//cellId++;
				} else {
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

	protected Object getCellValue(Cell cell, ArquivoInputColsDef arquivoInputColsDef) throws ServiceException {
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

			if (ColDefType.INT.equals(arquivoInputColsDef.getType())) {
				value = clearMask(value);

				if (NumberUtils.isNumber(value.toString())) {
					value = Integer.parseInt(value.toString());
				} else {
					return NumberUtils.INTEGER_ZERO;
				}
			} else if (ColDefType.LONG.equals(arquivoInputColsDef.getType())) {
				value = clearMask(value);

				if (NumberUtils.isNumber(value.toString())) {
					value = (long) Double.parseDouble(value.toString());
				} else {
					return NumberUtils.LONG_ZERO;
				}
			} else if (ColDefType.DOUBLE.equals(arquivoInputColsDef.getType())) {
				if (NumberUtils.isNumber(value.toString())) {
					value = BigDecimal.valueOf((Double) cell.getNumericCellValue());
				} else {
					return BigDecimal.ZERO;
				}
			} else if (ColDefType.DATE.equals(arquivoInputColsDef.getType())) {
				cell.setCellStyle(cellStyleDate);

				if (value instanceof String) {
					if (StringUtils.isNotBlank(arquivoInputColsDef.getLocalePattern())) {
						value = DateUtils.stringToDate(
								(String) value,
								arquivoInputColsDef.getFormat(),
								arquivoInputColsDef.getLocalePattern());
					} else {
						value = DateUtils.stringToDate((String) value, arquivoInputColsDef.getFormat());
					}
				} else if (value instanceof Date) {
					value = DateUtils.dateToLocalDate((Date) value);
				}
			} else if (ColDefType.STRING.equals(arquivoInputColsDef.getType())) {
				value = value.toString().trim().toUpperCase();
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private Long clearMask(Object value) {
		String strValue;

		if (value instanceof String) {
			strValue = ((String) value).trim();

			if (StringUtils.isNotBlank(strValue)) {
				strValue = StringUtils.replaceAll(strValue, "(\\.|\\-|\\')", StringUtils.EMPTY);
				return Long.valueOf(strValue);
			}

			return NumberUtils.LONG_ZERO;
		}

		return Double.valueOf(value.toString()).longValue();
	}

	@Override
	protected Map<String, Object> readLine(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
