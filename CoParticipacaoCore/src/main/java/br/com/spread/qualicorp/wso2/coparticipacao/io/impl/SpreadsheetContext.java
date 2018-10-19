package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class SpreadsheetContext {

	private DataFormat dataFormat;

	private CellStyle cellStyleDate;
	
	private String sheetName;
	
	public SpreadsheetContext() {
		
	}

	public DataFormat getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(DataFormat dataFormat) {
		this.dataFormat = dataFormat;
	}

	public CellStyle getCellStyleDate() {
		return cellStyleDate;
	}

	public void setCellStyleDate(CellStyle cellStyleDate) {
		this.cellStyleDate = cellStyleDate;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}
