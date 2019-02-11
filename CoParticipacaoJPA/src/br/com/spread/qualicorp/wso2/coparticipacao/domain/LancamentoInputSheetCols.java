package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class LancamentoInputSheetCols extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3009354352514778704L;

	private LancamentoInputSheet lancamentoInputSheet;

	private RegisterColumn registerColumn;

	private LancamentoColType lancamentoColType;

	public LancamentoInputSheetCols() {
		super();
	}

	public LancamentoInputSheet getLancamentoInputSheet() {
		return lancamentoInputSheet;
	}

	public void setLancamentoInputSheet(LancamentoInputSheet lancamentoInputSheet) {
		this.lancamentoInputSheet = lancamentoInputSheet;
	}

	public LancamentoColType getLancamentoColType() {
		return lancamentoColType;
	}

	public void setLancamentoColType(LancamentoColType lancamentoColType) {
		this.lancamentoColType = lancamentoColType;
	}

	public RegisterColumn getRegisterColumn() {
		return registerColumn;
	}

	public void setRegisterColumn(RegisterColumn registerColumn) {
		this.registerColumn = registerColumn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lancamentoColType == null) ? 0 : lancamentoColType.hashCode());
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
		LancamentoInputSheetCols other = (LancamentoInputSheetCols) obj;
		if (lancamentoColType != other.lancamentoColType)
			return false;
		if (registerColumn == null) {
			if (other.registerColumn != null)
				return false;
		} else if (!registerColumn.equals(other.registerColumn))
			return false;
		return true;
	}
}
