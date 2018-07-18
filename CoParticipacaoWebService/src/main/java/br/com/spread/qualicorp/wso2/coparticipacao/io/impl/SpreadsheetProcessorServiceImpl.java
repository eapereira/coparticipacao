package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.io.SpreadsheetProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputColsDefService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class SpreadsheetProcessorServiceImpl extends AbstractFileProcessorImpl
		implements SpreadsheetProcessorService {

	private static final Logger LOGGER = LogManager
			.getLogger(SpreadsheetProcessorServiceImpl.class);

	public void readInputStream(
			CoParticipacaoContext coParticipacaoContext,
			ProcessorListener processorListener) throws ServiceException {
		Map<String, Object> mapLine;
		int currentLine = NumberUtils.INTEGER_ZERO;
		Workbook workbook;
		Sheet sheet;

		try {
			LOGGER.info("BEGIN");

			workbook = new XSSFWorkbook(
					coParticipacaoContext.getFileInputStream());

			if (workbook.getNumberOfSheets() >= 1) {
				processorListener.beforeProcess(coParticipacaoContext);

				sheet = workbook.getSheetAt(NumberUtils.INTEGER_ZERO);

				for (Row row : sheet) {
					if (row.getRowNum() <= coParticipacaoContext
							.getArquivoInputUi().getSkipLines()) {
						LOGGER.debug("Skipping line [{}]:", currentLine);
						continue;
					}

					LOGGER.info("Transforming line into Map:");
					mapLine = readLine(row, coParticipacaoContext);

					LOGGER.info(
							"Sending line to be processed by ProcessorListener:");

					coParticipacaoContext.setMapLine(mapLine);
					coParticipacaoContext.setCurrentLine(currentLine);

					processorListener.processLine(coParticipacaoContext);

					currentLine++;
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

	protected Map<String, Object> readLine(
			Row row,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		Map<String, Object> mapLine;
		List<ArquivoInputColsDef> arquivoInputColsDefs;
		int cellId = NumberUtils.INTEGER_ZERO;
		String columnName = StringUtils.EMPTY;
		Object value;
		ArquivoInputColsDef arquivoInputColsDef;

		try {
			LOGGER.info("BEGIN");

			arquivoInputColsDefs = coParticipacaoContext.getArquivoInputUi()
					.getArquivoInputColsDefs();

			mapLine = new HashMap<String, Object>();

			for (Cell cell : row) {
				arquivoInputColsDef = null;
				cellId = NumberUtils.INTEGER_ZERO;

				for (ArquivoInputColsDef colsDef : arquivoInputColsDefs) {
					arquivoInputColsDef = colsDef;

					if (cellId == cell.getColumnIndex()) {
						columnName = arquivoInputColsDef.getNameColumn();
						break;
					}

					cellId++;
				}

				LOGGER.info(
						"Retrieving cell value for column [{}]:",
						columnName);
				value = getCellValue(cell, arquivoInputColsDef);

				LOGGER.info("Cell [{}] has value [{}]:", columnName, value);
				mapLine.put(columnName, value);
			}

			LOGGER.info("END");
			return mapLine;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private Object getCellValue(
			Cell cell,
			ArquivoInputColsDef arquivoInputColsDef) throws ServiceException {
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
				value = cell.getCellFormula();
				break;
			case BLANK:
				value = StringUtils.EMPTY;
				break;
			default:
				value = StringUtils.EMPTY;
				break;
			}

			if (ColDefType.INT.equals(arquivoInputColsDef.getType())) {
				if (NumberUtils.isNumber(value.toString())) {
					value = Integer.parseInt(value.toString());
				} else {
					return NumberUtils.INTEGER_ZERO;
				}
			} else if (ColDefType.LONG.equals(arquivoInputColsDef.getType())) {
				if (NumberUtils.isNumber(value.toString())) {
					value = (long) Double.parseDouble(value.toString());
				} else {
					return NumberUtils.LONG_ZERO;
				}
			} else if (ColDefType.DOUBLE
					.equals(arquivoInputColsDef.getType())) {
				if (NumberUtils.isNumber(value.toString())) {
					value = (Double) cell.getNumericCellValue();
				} else {
					return NumberUtils.DOUBLE_ZERO;
				}
			} else if (ColDefType.DATE.equals(arquivoInputColsDef.getType())) {
				value = DateUtils.dateToLocalDate((Date) value);
			} else if (ColDefType.STRING
					.equals(arquivoInputColsDef.getType())) {
				value = value.toString();
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected Map<String, Object> readLine(
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
