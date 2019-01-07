package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_regra_field database table.
 * 
 */
public abstract class RegraField extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private RegisterColumn registerColumn;

	private RegraOperation regraOperation;

	public RegraField() {
	}

	public RegraField(RegraField entity) {
		super(entity);
	}

	public RegraOperation getRegraOperation() {
		return this.regraOperation;
	}

	public void setRegraOperation(RegraOperation regraOperation) {
		this.regraOperation = regraOperation;
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
		RegraField other = (RegraField) obj;
		if (registerColumn == null) {
			if (other.registerColumn != null)
				return false;
		} else if (!registerColumn.equals(other.registerColumn))
			return false;
		return true;
	}


}