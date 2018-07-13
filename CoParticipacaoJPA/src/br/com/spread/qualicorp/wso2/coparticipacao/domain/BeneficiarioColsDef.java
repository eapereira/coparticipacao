package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class BeneficiarioColsDef extends SimpleColsDef {

	/**
		 * 
		 */
	private static final long serialVersionUID = 8067413022625315329L;

	private List<InputBeneficiarioBind> inputBeneficiarioBinds;

	public BeneficiarioColsDef() {

	}

	public List<InputBeneficiarioBind> getInputBeneficiarioBinds() {
		return inputBeneficiarioBinds;
	}

	public void setInputBeneficiarioBinds(
			List<InputBeneficiarioBind> inputBeneficiarioBinds) {
		this.inputBeneficiarioBinds = inputBeneficiarioBinds;
	}

	public void addInputBeneficiarioBind(
			InputBeneficiarioBind inputBeneficiarioBind) {
		getInputBeneficiarioBinds().add(inputBeneficiarioBind);
		inputBeneficiarioBind.setBeneficiarioColsDef(this);
	}

	public void removeInputBeneficiarioBind(
			InputBeneficiarioBind inputBeneficiarioBind) {
		getInputBeneficiarioBinds().remove(inputBeneficiarioBind);
		inputBeneficiarioBind.setArquivoInputColsDef(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((inputBeneficiarioBinds == null) ? 0
				: inputBeneficiarioBinds.hashCode());
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
		BeneficiarioColsDef other = (BeneficiarioColsDef) obj;
		if (inputBeneficiarioBinds == null) {
			if (other.inputBeneficiarioBinds != null)
				return false;
		} else if (!inputBeneficiarioBinds.equals(other.inputBeneficiarioBinds))
			return false;
		return true;
	}

}
