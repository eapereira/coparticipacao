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

	private ArquivoInputSheetColsDef arquivoInputSheetColsDef;

	private BeneficiarioColType beneficiarioColType;

	public BeneficiarioCols() {

	}

	public ArquivoInputSheetColsDef getArquivoInputSheetColsDef() {
		return arquivoInputSheetColsDef;
	}

	public void setArquivoInputSheetColsDef(ArquivoInputSheetColsDef arquivoInputSheetColsDef) {
		this.arquivoInputSheetColsDef = arquivoInputSheetColsDef;
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
		result = prime * result + ((arquivoInputSheetColsDef == null) ? 0 : arquivoInputSheetColsDef.hashCode());
		result = prime * result + ((beneficiarioColType == null) ? 0 : beneficiarioColType.hashCode());
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
		if (arquivoInputSheetColsDef == null) {
			if (other.arquivoInputSheetColsDef != null)
				return false;
		} else if (!arquivoInputSheetColsDef.equals(other.arquivoInputSheetColsDef))
			return false;
		if (beneficiarioColType != other.beneficiarioColType)
			return false;
		return true;
	}

}
