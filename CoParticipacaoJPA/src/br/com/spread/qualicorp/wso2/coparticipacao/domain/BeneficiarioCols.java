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

	private ArquivoInputColsDef arquivoInputColsDef;

	private BeneficiarioColType beneficiarioColType;

	public BeneficiarioCols() {

	}

	public ArquivoInputColsDef getArquivoInputColsDef() {
		return arquivoInputColsDef;
	}

	public void setArquivoInputColsDef(ArquivoInputColsDef arquivoInputColsDef) {
		this.arquivoInputColsDef = arquivoInputColsDef;
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
		int result = 1;
		result = prime * result + ((arquivoInputColsDef == null) ? 0
				: arquivoInputColsDef.hashCode());
		result = prime * result + ((beneficiarioColType == null) ? 0
				: beneficiarioColType.hashCode());
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
		BeneficiarioCols other = (BeneficiarioCols) obj;
		if (arquivoInputColsDef == null) {
			if (other.arquivoInputColsDef != null)
				return false;
		} else if (!arquivoInputColsDef.equals(other.arquivoInputColsDef))
			return false;
		if (beneficiarioColType != other.beneficiarioColType)
			return false;
		return true;
	}



}
