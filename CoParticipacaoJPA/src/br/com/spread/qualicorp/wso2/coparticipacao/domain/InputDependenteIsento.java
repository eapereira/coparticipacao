package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class InputDependenteIsento extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5127144846470470301L;

	private ArquivoInput arquivoInput;

	private List<InputDependenteIsentoCols> inputDependenteIsentoCols;

	public InputDependenteIsento() {
		inputDependenteIsentoCols = new ArrayList<>();
	}

	public ArquivoInput getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public List<InputDependenteIsentoCols> getInputDependenteIsentoCols() {
		return inputDependenteIsentoCols;
	}

	public void setInputDependenteIsentoCols(List<InputDependenteIsentoCols> inputDependenteIsentoCols) {
		this.inputDependenteIsentoCols = inputDependenteIsentoCols;
	}

	public void addInputDependenteIsentoCols(InputDependenteIsentoCols inputDependenteIsentoCols) {
		getInputDependenteIsentoCols().add(inputDependenteIsentoCols);
		inputDependenteIsentoCols.setInputDependenteIsento(this);
	}

	public void renoveInputDependenteIsentoCols(InputDependenteIsentoCols inputDependenteIsentoCols) {
		getInputDependenteIsentoCols().remove(inputDependenteIsentoCols);
		inputDependenteIsentoCols.setInputDependenteIsento(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inputDependenteIsentoCols == null) ? 0 : inputDependenteIsentoCols.hashCode());
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
		InputDependenteIsento other = (InputDependenteIsento) obj;
		if (arquivoInput == null) {
			if (other.arquivoInput != null)
				return false;
		} else if (!arquivoInput.equals(other.arquivoInput))
			return false;
		if (inputDependenteIsentoCols == null) {
			if (other.inputDependenteIsentoCols != null)
				return false;
		} else if (!inputDependenteIsentoCols.equals(other.inputDependenteIsentoCols))
			return false;
		return true;
	}
}
