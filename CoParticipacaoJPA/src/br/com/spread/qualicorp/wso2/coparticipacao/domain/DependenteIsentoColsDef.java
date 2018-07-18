package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.List;

/**
 * 
 * @author edson.apereira
 *
 */
public abstract class DependenteIsentoColsDef extends SimpleColsDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1481985115555371606L;

	private List<InputDependenteIsentoCols> inputDependenteIsentoCols;

	public DependenteIsentoColsDef() {

	}

	public List<InputDependenteIsentoCols> getInputDependenteIsentoCols() {
		return inputDependenteIsentoCols;
	}

	public void setInputDependenteIsentoCols(
			List<InputDependenteIsentoCols> inputDependenteIsentoCols) {
		this.inputDependenteIsentoCols = inputDependenteIsentoCols;
	}

	public void addInputDependenteIsentoCols(
			InputDependenteIsentoCols inputDependenteIsentoCols) {
		getInputDependenteIsentoCols().add(inputDependenteIsentoCols);
		inputDependenteIsentoCols.setDependenteIsentoColsDef(this);
	}

	public void removeInputDependenteIsentoCols(
			InputDependenteIsentoCols inputDependenteIsentoCols) {
		getInputDependenteIsentoCols().remove(inputDependenteIsentoCols);
		inputDependenteIsentoCols.setDependenteIsentoColsDef(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((inputDependenteIsentoCols == null) ? 0
				: inputDependenteIsentoCols.hashCode());
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
		DependenteIsentoColsDef other = (DependenteIsentoColsDef) obj;
		if (inputDependenteIsentoCols == null) {
			if (other.inputDependenteIsentoCols != null)
				return false;
		} else if (!inputDependenteIsentoCols
				.equals(other.inputDependenteIsentoCols))
			return false;
		return true;
	}

}
