package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class SpreadsheetBuilder<UI extends AbstractDomain> {

	private static final Logger LOGGER = LogManager.getLogger(SpreadsheetBuilder.class);

	private List<SpreadsheetListener<UI>> spreadsheetListeners;

	private int currentRow;

	private Workbook workbook;

	private CellStyle cellStyleColumnTitle;

	private CellStyle cellStyleColumnNormal;

	private CellStyle cellStyleNumericDouble;

	private CellStyle cellStyleNumericInt;

	private CellStyle cellStyleDate;

	private int numColumns;

	private String spreadsheetFileName;

	private int numSheets;

	// pixels
	private static final int DEFAULT_FONT_WIDTH = 350;

	private static final String DEFAULT_FONT = "Calibri";

	private static final short DEFAULT_FONT_HEIGHT_11 = 11 * 20;

	public SpreadsheetBuilder() {
		spreadsheetListeners = new ArrayList<SpreadsheetListener<UI>>();
	}

	public SpreadsheetBuilder(String spreadsheetFileName) {
		this();

		this.spreadsheetFileName = spreadsheetFileName;
	}

	public void writeSpreadsheet(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		Sheet sheet;
		List<ColumnInfo> columnInfos;

		try {
			LOGGER.info("BEGIN");

			workbook = new XSSFWorkbook();

			numSheets = NumberUtils.INTEGER_ZERO;

			createCellStyles();

			for (SpreadsheetListener<UI> spreadsheetListener : getSpreadsheetListeners()) {
				sheet = workbook.createSheet(spreadsheetListener.getSheetName(numSheets));

				currentRow = NumberUtils.INTEGER_ZERO;

				columnInfos = spreadsheetListener.createColumnTitles();

				createColumnNames(spreadsheetListener, sheet, columnInfos);

				writeData(spreadsheetListener, sheet);

				// createColumnFilters(sheet);
				numSheets++;
			}

			writeWorkbook(coParticipacaoContext, workbook);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void writeData(SpreadsheetListener<UI> spreadsheetListener, Sheet sheet) throws ServiceException {
		List<UI> listData;
		Row row;
		Cell cell;
		CellInfo cellInfo;
		int col;

		try {
			LOGGER.info("BEGIN");

			listData = spreadsheetListener.createData();

			for (UI ui : listData) {
				row = sheet.createRow(currentRow);
				col = NumberUtils.INTEGER_ZERO;

				do {
					cell = row.createCell(col);
					cellInfo = spreadsheetListener.createCellContent(ui, col);

					writeCellValue(cell, cellInfo.getValue());
					col++;
				} while (CellInfoStatus.KEEP_LINE.equals(cellInfo.getCellInfoStatus()));

				currentRow++;
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void writeCellValue(Cell cell, Object value) throws ServiceException {
		if (value != null) {
			if (value instanceof Integer) {
				cell.setCellType(CellType.NUMERIC);
				cell.setCellValue((Integer) value);
				cell.setCellStyle(cellStyleNumericInt);
			} else if (value instanceof Long) {
				cell.setCellType(CellType.NUMERIC);
				cell.setCellValue((Long) value);
				cell.setCellStyle(cellStyleNumericInt);
			} else if (value instanceof BigDecimal) {
				cell.setCellType(CellType.NUMERIC);
				cell.setCellValue(((BigDecimal) value).doubleValue());
				cell.setCellStyle(cellStyleNumericDouble);
			} else if (value instanceof LocalDate) {
				cell.setCellValue(Date.valueOf((LocalDate) value));
				cell.setCellStyle(cellStyleDate);
			} else if (value instanceof String) {
				cell.setCellValue(value.toString());
			}
		}
	}

	private void createColumnNames(
			SpreadsheetListener<UI> spreadsheetListener,
			Sheet sheet,
			List<ColumnInfo> columnInfos) throws ServiceException {
		Row row;
		Cell cell;

		try {
			LOGGER.info("BEGIN");

			row = sheet.createRow(currentRow);

			numColumns = NumberUtils.INTEGER_ZERO;

			for (ColumnInfo columnInfo : columnInfos) {
				cell = row.createCell(numColumns);
				cell.setCellStyle(cellStyleColumnTitle);
				cell.setCellValue(columnInfo.getName());

				sheet.setColumnWidth(cell.getColumnIndex(), DEFAULT_FONT_WIDTH * columnInfo.getWidth());

				numColumns++;
			}

			currentRow++;

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void writeWorkbook(
			CoParticipacaoContext coParticipacaoContext,
			Workbook workbook) throws ServiceException {
		OutputStream outputStream;
		String reportPath;
		String spreadsheetFilePath;
		ArquivoInputUi arquivoInputUi;
		File file;

		try {
			LOGGER.info("BEGIN");

			arquivoInputUi = coParticipacaoContext.getArquivoInputUi();

			reportPath = coParticipacaoContext.getEmpresaUi().getOutputReportDir();

			spreadsheetFileName = StringUtils
					.replace(spreadsheetFileName, "{CC}", arquivoInputUi.getContrato().getCdContrato());
			spreadsheetFileName = StringUtils
					.replace(spreadsheetFileName, "{YYYY}", String.valueOf(DateUtils.now().getYear()));
			spreadsheetFileName = StringUtils.replace(
					spreadsheetFileName,
					"{MM}",
					StringUtils.leftPad(String.valueOf(DateUtils.now().getMonthValue()), 2, "0"));
			spreadsheetFileName = StringUtils.replace(
					spreadsheetFileName,
					"{DD}",
					StringUtils.leftPad(String.valueOf(DateUtils.now().getDayOfMonth()), 2, "0"));

			spreadsheetFilePath = reportPath.concat(spreadsheetFileName);
			
			//spreadsheetFilePath=arquivoOutputService.createFileName(coParticipacaoContext, arquivoOutputUi, arquivoInputUi.getContrato());

			LOGGER.info("Wrting spreadsheet file to [{}]:", spreadsheetFilePath);

			file = new File(reportPath);

			if (!file.exists()) {
				file.mkdirs();
			}

			file = new File(spreadsheetFilePath);

			if (file.exists()) {
				file.delete();
			}

			outputStream = new FileOutputStream(file);

			workbook.write(outputStream);

			coParticipacaoContext.getArquivoExecucaoUi().setNameArquivoOutput(spreadsheetFilePath);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<SpreadsheetListener<UI>> getSpreadsheetListeners() {
		return spreadsheetListeners;
	}

	public void setSpreadsheetListeners(List<SpreadsheetListener<UI>> spreadsheetListeners) {
		this.spreadsheetListeners = spreadsheetListeners;
	}

	public void addSpreadsheetListener(SpreadsheetListener<UI> spreadsheetListener) {
		getSpreadsheetListeners().add(spreadsheetListener);
	}

	private void createCellStyles() throws ServiceException {
		XSSFFont fontTitle;
		XSSFFont fontNormal;
		DataFormat dataFormat;

		try {
			LOGGER.info("BEGIN");

			fontTitle = (XSSFFont) workbook.createFont();
			fontTitle.setFontName(DEFAULT_FONT);
			fontTitle.setFontHeight(DEFAULT_FONT_HEIGHT_11);
			fontTitle.setBold(true);

			cellStyleColumnTitle = workbook.createCellStyle();
			cellStyleColumnTitle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
			cellStyleColumnTitle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			cellStyleColumnTitle.setFont(fontTitle);
			cellStyleColumnTitle.setAlignment(HorizontalAlignment.CENTER);
			cellStyleColumnTitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			fontNormal = (XSSFFont) workbook.createFont();
			fontNormal.setFontName(DEFAULT_FONT);
			fontNormal.setFontHeight(DEFAULT_FONT_HEIGHT_11);
			fontNormal.setBold(false);

			cellStyleColumnNormal = workbook.createCellStyle();
			cellStyleColumnNormal.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
			cellStyleColumnNormal.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			cellStyleColumnNormal.setFont(fontNormal);

			dataFormat = workbook.createDataFormat();

			cellStyleNumericDouble = workbook.createCellStyle();
			cellStyleNumericDouble.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
			cellStyleNumericDouble.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			cellStyleNumericDouble.setFont(fontNormal);
			cellStyleNumericDouble.setDataFormat(dataFormat.getFormat("0.00"));

			dataFormat = workbook.createDataFormat();

			cellStyleNumericInt = workbook.createCellStyle();
			cellStyleNumericInt.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
			cellStyleNumericInt.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			cellStyleNumericInt.setFont(fontNormal);
			cellStyleNumericInt.setDataFormat(dataFormat.getFormat("0"));

			dataFormat = workbook.createDataFormat();

			cellStyleDate = workbook.createCellStyle();
			cellStyleDate.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
			cellStyleDate.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			cellStyleDate.setFont(fontNormal);
			cellStyleDate.setDataFormat(dataFormat.getFormat("dd/MM/yyyy"));

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void createColumnFilters(Sheet sheet) throws ServiceException {
		Row firstRow;
		Row lastRow;
		Cell firstCellA;
		Cell lastCellB;
		int numRows;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Creating filters for sheet [{}]:", sheet.getSheetName());

			numRows = sheet.getLastRowNum();

			if (numRows > 0) {
				firstRow = sheet.getRow(0);

				if (firstRow != null) {
					lastRow = sheet.getRow(numRows - 1);

					firstCellA = firstRow.getCell(1);
					lastCellB = lastRow.getCell(numColumns - 1);

					sheet.setAutoFilter(
							new CellRangeAddress(
									firstCellA.getRowIndex(),
									lastCellB.getRowIndex(),
									firstCellA.getColumnIndex(),
									lastCellB.getColumnIndex()));
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public String getSpreadsheetFileName() {
		return spreadsheetFileName;
	}

	public void setSpreadsheetFileName(String spreadsheetFileName) {
		this.spreadsheetFileName = spreadsheetFileName;
	}
}
