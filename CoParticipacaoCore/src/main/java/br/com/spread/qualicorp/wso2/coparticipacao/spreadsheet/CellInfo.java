package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CellInfo {

	private Object value;

	private CellInfoStatus cellInfoStatus;

	public CellInfo() {

	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public CellInfoStatus getCellInfoStatus() {
		return cellInfoStatus;
	}

	public void setCellInfoStatus(CellInfoStatus cellInfoStatus) {
		this.cellInfoStatus = cellInfoStatus;
	}
}
