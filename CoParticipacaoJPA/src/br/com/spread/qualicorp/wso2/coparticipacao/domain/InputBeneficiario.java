package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class InputBeneficiario extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6815423832361622021L;

	private ArquivoInput arquivoInput;

	private List<InputBeneficiarioBind> inputBeneficiarioBinds;

	public InputBeneficiario() {

	}

	public ArquivoInput getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public List<InputBeneficiarioBind> getInputBeneficiarioBinds() {
		return inputBeneficiarioBinds;
	}

	public void setInputBeneficiarioBinds(
			List<InputBeneficiarioBind> inputBeneficiarioBinds) {
		this.inputBeneficiarioBinds = inputBeneficiarioBinds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arquivoInput == null) ? 0 : arquivoInput.hashCode());
		result = prime * result + ((inputBeneficiarioBinds == null) ? 0
				: inputBeneficiarioBinds.hashCode());
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
		InputBeneficiario other = (InputBeneficiario) obj;
		if (arquivoInput == null) {
			if (other.arquivoInput != null)
				return false;
		} else if (!arquivoInput.equals(other.arquivoInput))
			return false;
		if (inputBeneficiarioBinds == null) {
			if (other.inputBeneficiarioBinds != null)
				return false;
		} else if (!inputBeneficiarioBinds.equals(other.inputBeneficiarioBinds))
			return false;
		return true;
	}
}
