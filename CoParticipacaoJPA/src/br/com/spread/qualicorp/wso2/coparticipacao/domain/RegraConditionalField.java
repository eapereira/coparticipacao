package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class RegraConditionalField extends AbstractDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7079838001281846490L;

	private RegisterColumn registerColumn;

	private RegraConditionalOperation regraConditionalOperation;

	public RegraConditionalField() {

	}

	public RegraConditionalOperation getRegraConditionalOperation() {
		return regraConditionalOperation;
	}

	public void setRegraConditionalOperation(
			RegraConditionalOperation regraConditionalOperation) {
		this.regraConditionalOperation = regraConditionalOperation;
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
		RegraConditionalField other = (RegraConditionalField) obj;
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
