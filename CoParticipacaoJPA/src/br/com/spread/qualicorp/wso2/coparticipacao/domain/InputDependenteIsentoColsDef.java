package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author edson.apereira
 *
 */
public abstract class InputDependenteIsentoColsDef extends SimpleColsDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1481985115555371606L;

	private Integer tpIsento;
	
	private InputDependenteIsentoCols inputDependenteIsentoCols;
	
	public InputDependenteIsentoColsDef() {
		
	}

	public InputDependenteIsentoCols getInputDependenteIsentoCols() {
		return inputDependenteIsentoCols;
	}

	public void setInputDependenteIsentoCols(InputDependenteIsentoCols inputDependenteIsentoCols) {
		this.inputDependenteIsentoCols = inputDependenteIsentoCols;
	}


	public Integer getTpIsento() {
		return tpIsento;
	}

	public void setTpIsento(Integer tpIsento) {
		this.tpIsento = tpIsento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((inputDependenteIsentoCols == null) ? 0
				: inputDependenteIsentoCols.hashCode());
		result = prime * result
				+ ((tpIsento == null) ? 0 : tpIsento.hashCode());
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
		InputDependenteIsentoColsDef other = (InputDependenteIsentoColsDef) obj;
		if (inputDependenteIsentoCols == null) {
			if (other.inputDependenteIsentoCols != null)
				return false;
		} else if (!inputDependenteIsentoCols
				.equals(other.inputDependenteIsentoCols))
			return false;
		if (tpIsento == null) {
			if (other.tpIsento != null)
				return false;
		} else if (!tpIsento.equals(other.tpIsento))
			return false;
		return true;
	}
}
