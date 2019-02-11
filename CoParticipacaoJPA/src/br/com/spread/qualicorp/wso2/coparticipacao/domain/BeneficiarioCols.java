package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class BeneficiarioCols extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7383404930745300753L;

	private RegisterColumn registerColumn;

	private BeneficiarioColType beneficiarioColType;

	public BeneficiarioCols() {

	}

	public BeneficiarioColType getBeneficiarioColType() {
		return beneficiarioColType;
	}

	public void setBeneficiarioColType(BeneficiarioColType beneficiarioColType) {
		this.beneficiarioColType = beneficiarioColType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beneficiarioColType == null) ? 0 : beneficiarioColType.hashCode());
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
		BeneficiarioCols other = (BeneficiarioCols) obj;
		if (beneficiarioColType != other.beneficiarioColType)
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
