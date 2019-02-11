package br.com.spread.qualicorp.wso2.coparticipacao.test.spreadsheet.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.test.spreadsheet.SpreadsheetReader;
import br.com.spread.qualicorp.wso2.coparticipacao.test.spreadsheet.SpreadsheetReadertListener;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class SpreadsheetReaderImpl implements SpreadsheetReader {

	private static final Logger LOGGER = LogManager.getLogger(SpreadsheetReaderImpl.class);

	public <T> Map<String, List<T>> loadData(
			InputStream inputStream,
			SpreadsheetReadertListener<T> spreadsheeReadertListener) throws ServiceException {
		Map<String, List<T>> map;
		List<T> list;
		Workbook workbook = null;
		Sheet sheet;
		T data;

		try {
			LOGGER.info("BEGIN");

			map = new HashMap<String, List<T>>();

			if (inputStream != null) {				
				workbook = new XSSFWorkbook(inputStream);

				for (String sheetName : spreadsheeReadertListener.getSheetNames()) {
					sheet = workbook.getSheet(sheetName);
					list = new ArrayList<T>();

					for (Row row : sheet) {
						if (row.getRowNum() > NumberUtils.INTEGER_ZERO) {
							data = spreadsheeReadertListener.createData(row);

							list.add(data);
						}
					}

					map.put(sheetName, list);
				}
			}

			LOGGER.info("END");
			return map;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		} finally {
			close(workbook);
		}
	}

	private void close(Workbook workbook) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (workbook != null) {
				workbook.close();
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

}
