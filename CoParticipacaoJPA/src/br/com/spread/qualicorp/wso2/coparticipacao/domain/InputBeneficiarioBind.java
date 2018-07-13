package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class InputBeneficiarioBind extends AbstractDomain{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7383404930745300753L;

	private InputBeneficiario inputBeneficiario;
	
	private ArquivoInputColsDef arquivoInputColsDef;
	
	private BeneficiarioColsDef beneficiarioColsDef;
	
	public InputBeneficiarioBind() {
		
	}

	public InputBeneficiario getInputBeneficiario() {
		return inputBeneficiario;
	}

	public void setInputBeneficiario(InputBeneficiario inputBeneficiario) {
		this.inputBeneficiario = inputBeneficiario;
	}

	public ArquivoInputColsDef getArquivoInputColsDef() {
		return arquivoInputColsDef;
	}

	public void setArquivoInputColsDef(ArquivoInputColsDef arquivoInputColsDef) {
		this.arquivoInputColsDef = arquivoInputColsDef;
	}

	public BeneficiarioColsDef getBeneficiarioColsDef() {
		return beneficiarioColsDef;
	}

	public void setBeneficiarioColsDef(BeneficiarioColsDef beneficiarioColsDef) {
		this.beneficiarioColsDef = beneficiarioColsDef;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInputColsDef == null) ? 0
				: arquivoInputColsDef.hashCode());
		result = prime * result + ((beneficiarioColsDef == null) ? 0
				: beneficiarioColsDef.hashCode());
		result = prime * result + ((inputBeneficiario == null) ? 0
				: inputBeneficiario.hashCode());
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
		InputBeneficiarioBind other = (InputBeneficiarioBind) obj;
		if (arquivoInputColsDef == null) {
			if (other.arquivoInputColsDef != null)
				return false;
		} else if (!arquivoInputColsDef.equals(other.arquivoInputColsDef))
			return false;
		if (beneficiarioColsDef == null) {
			if (other.beneficiarioColsDef != null)
				return false;
		} else if (!beneficiarioColsDef.equals(other.beneficiarioColsDef))
			return false;
		if (inputBeneficiario == null) {
			if (other.inputBeneficiario != null)
				return false;
		} else if (!inputBeneficiario.equals(other.inputBeneficiario))
			return false;
		return true;
	}

}
