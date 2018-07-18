package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class TitularIsentoColsDef extends SimpleColsDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1880560710480125307L;

	private List<InputTitularIsentoCols> inputTitularIsentoCols;

	public TitularIsentoColsDef() {
		inputTitularIsentoCols = new ArrayList<InputTitularIsentoCols>();
	}

	public List<InputTitularIsentoCols> getInputTitularIsentoCols() {
		return inputTitularIsentoCols;
	}

	public void setInputTitularIsentoCols(
			List<InputTitularIsentoCols> inputTitularIsentoCols) {
		this.inputTitularIsentoCols = inputTitularIsentoCols;
	}

	public void addInputTitularIsentoCols(
			InputTitularIsentoCols inputTitularIsentoCols) {
		getInputTitularIsentoCols().add(inputTitularIsentoCols);
		inputTitularIsentoCols.setTitularIsentoColsDef(this);
	}

	public void removeInputTitularIsentoCols(
			InputTitularIsentoCols inputTitularIsentoCols) {
		getInputTitularIsentoCols().remove(inputTitularIsentoCols);
		inputTitularIsentoCols.setTitularIsentoColsDef(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((inputTitularIsentoCols == null) ? 0
				: inputTitularIsentoCols.hashCode());
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
		TitularIsentoColsDef other = (TitularIsentoColsDef) obj;
		if (inputTitularIsentoCols == null) {
			if (other.inputTitularIsentoCols != null)
				return false;
		} else if (!inputTitularIsentoCols.equals(other.inputTitularIsentoCols))
			return false;
		return true;
	}
}
