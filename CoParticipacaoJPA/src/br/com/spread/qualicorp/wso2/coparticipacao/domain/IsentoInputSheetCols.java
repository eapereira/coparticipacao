package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class IsentoInputSheetCols extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1799537054712694938L;

	private RegisterColumn registerColumn;

	private BeneficiarioIsentoColType beneficiarioIsentoColType;

	private IsentoInputSheet isentoInputSheet;

	public IsentoInputSheetCols() {

	}

	public BeneficiarioIsentoColType getBeneficiarioIsentoColType() {
		return beneficiarioIsentoColType;
	}

	public void setBeneficiarioIsentoColType(BeneficiarioIsentoColType beneficiarioIsentoColType) {
		this.beneficiarioIsentoColType = beneficiarioIsentoColType;
	}

	public IsentoInputSheet getIsentoInputSheet() {
		return isentoInputSheet;
	}

	public void setIsentoInputSheet(IsentoInputSheet isentoInputSheet) {
		this.isentoInputSheet = isentoInputSheet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beneficiarioIsentoColType == null) ? 0 : beneficiarioIsentoColType.hashCode());
		result = prime * result + ((isentoInputSheet == null) ? 0 : isentoInputSheet.hashCode());
		result = prime * result + ((registerColumn == null) ? 0 : registerColumn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		IsentoInputSheetCols other = (IsentoInputSheetCols) obj;
		if (beneficiarioIsentoColType != other.beneficiarioIsentoColType)
			return false;
		if (isentoInputSheet == null) {
			if (other.isentoInputSheet != null)
				return false;
		} else if (!isentoInputSheet.equals(other.isentoInputSheet))
			return false;
		if (registerColumn == null) {
			if (other.registerColumn != null)
				return false;
		} else if (!registerColumn.equals(other.registerColumn))
			return false;
		return true;
	}

	public RegisterColumn getRegisterColumn() {
		return registerColumn;
	}

	public void setRegisterColumn(RegisterColumn registerColumn) {
		this.registerColumn = registerColumn;
	}

}
