package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class IsentoInputSheet extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6768042503118149953L;

	private List<IsentoInputSheetCols> isentoInputSheetCols;

	private ArquivoInput arquivoInput;

	private IsentoType isentoType;
	
	private Integer sheetId;
	

	public IsentoInputSheet() {
		isentoInputSheetCols = new ArrayList<>();
	}

	public List<IsentoInputSheetCols> getIsentoInputSheetCols() {
		return isentoInputSheetCols;
	}

	public void setIsentoInputSheetCols(List<IsentoInputSheetCols> isentoInputSheetCols) {
		this.isentoInputSheetCols = isentoInputSheetCols;
	}

	public void addIsentoInputSheetCols(IsentoInputSheetCols isentoInputSheetCols) {
		getIsentoInputSheetCols().add(isentoInputSheetCols);
		isentoInputSheetCols.setIsentoInputSheet(this);
	}

	public void removeIsentoInputSheetCols(IsentoInputSheetCols isentoInputSheetCols) {
		getIsentoInputSheetCols().remove(isentoInputSheetCols);
		isentoInputSheetCols.setIsentoInputSheet(null);
	}

	public IsentoType getIsentoType() {
		return isentoType;
	}

	public void setIsentoType(IsentoType isentoType) {
		this.isentoType = isentoType;
	}

	public ArquivoInput getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public Integer getSheetId() {
		return sheetId;
	}

	public void setSheetId(Integer sheetId) {
		this.sheetId = sheetId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isentoInputSheetCols == null) ? 0 : isentoInputSheetCols.hashCode());
		result = prime * result + ((isentoType == null) ? 0 : isentoType.hashCode());
		result = prime * result + ((sheetId == null) ? 0 : sheetId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IsentoInputSheet other = (IsentoInputSheet) obj;
		if (arquivoInput == null) {
			if (other.arquivoInput != null)
				return false;
		} else if (!arquivoInput.equals(other.arquivoInput))
			return false;
		if (isentoInputSheetCols == null) {
			if (other.isentoInputSheetCols != null)
				return false;
		} else if (!isentoInputSheetCols.equals(other.isentoInputSheetCols))
			return false;
		if (isentoType != other.isentoType)
			return false;
		if (sheetId == null) {
			if (other.sheetId != null)
				return false;
		} else if (!sheetId.equals(other.sheetId))
			return false;
		return true;
	}

}
