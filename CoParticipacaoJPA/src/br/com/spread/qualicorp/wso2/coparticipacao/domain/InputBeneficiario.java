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

	private ArquivoInputSheet arquivoInputSheet;

	private List<BeneficiarioCols> inputBeneficiarioBinds;

	public InputBeneficiario() {

	}

	public List<BeneficiarioCols> getInputBeneficiarioBinds() {
		return inputBeneficiarioBinds;
	}

	public void setInputBeneficiarioBinds(List<BeneficiarioCols> inputBeneficiarioBinds) {
		this.inputBeneficiarioBinds = inputBeneficiarioBinds;
	}

	public ArquivoInputSheet getArquivoInputSheet() {
		return arquivoInputSheet;
	}

	public void setArquivoInputSheet(ArquivoInputSheet arquivoInputSheet) {
		this.arquivoInputSheet = arquivoInputSheet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((inputBeneficiarioBinds == null) ? 0 : inputBeneficiarioBinds.hashCode());
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
		InputBeneficiario other = (InputBeneficiario) obj;
		if (inputBeneficiarioBinds == null) {
			if (other.inputBeneficiarioBinds != null)
				return false;
		} else if (!inputBeneficiarioBinds.equals(other.inputBeneficiarioBinds))
			return false;
		return true;
	}

}
