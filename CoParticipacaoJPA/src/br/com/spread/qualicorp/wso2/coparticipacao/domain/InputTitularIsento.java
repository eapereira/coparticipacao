package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class InputTitularIsento extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3435413787844175955L;

	private ArquivoInput arquivoInput;

	private List<InputTitularIsentoCols> inputTitularIsentoCols;

	public InputTitularIsento() {
		inputTitularIsentoCols = new ArrayList<>();
	}

	public ArquivoInput getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public List<InputTitularIsentoCols> getInputTitularIsentoCols() {
		return inputTitularIsentoCols;
	}

	public void setInputTitularIsentoCols(List<InputTitularIsentoCols> inputTitularIsentoCols) {
		this.inputTitularIsentoCols = inputTitularIsentoCols;
	}

	public void addInputTitularIsentoCols(InputTitularIsentoCols inputTitularIsentoCols) {
		getInputTitularIsentoCols().add(inputTitularIsentoCols);
		inputTitularIsentoCols.setInputTitularIsento(this);
	}

	public void removeInputTitularIsentoCols(InputTitularIsentoCols inputTitularIsentoCols) {
		getInputTitularIsentoCols().remove(inputTitularIsentoCols);
		inputTitularIsentoCols.setInputTitularIsento(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInput == null) ? 0 : arquivoInput.hashCode());
		result = prime * result + ((inputTitularIsentoCols == null) ? 0 : inputTitularIsentoCols.hashCode());
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
		InputTitularIsento other = (InputTitularIsento) obj;
		if (arquivoInput == null) {
			if (other.arquivoInput != null)
				return false;
		} else if (!arquivoInput.equals(other.arquivoInput))
			return false;
		if (inputTitularIsentoCols == null) {
			if (other.inputTitularIsentoCols != null)
				return false;
		} else if (!inputTitularIsentoCols.equals(other.inputTitularIsentoCols))
			return false;
		return true;
	}

}
