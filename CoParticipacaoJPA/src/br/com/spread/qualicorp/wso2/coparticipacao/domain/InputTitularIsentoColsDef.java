package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class InputTitularIsentoColsDef extends SimpleColsDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1880560710480125307L;

	private Integer tpIsento;
	
	private InputTitularIsentoCols inputTitularIsentoCols;

	public InputTitularIsentoColsDef() {

	}

	public InputTitularIsentoCols getInputTitularIsentoCols() {
		return inputTitularIsentoCols;
	}

	public void setInputTitularIsentoCols(InputTitularIsentoCols inputTitularIsentoCols) {
		this.inputTitularIsentoCols = inputTitularIsentoCols;
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
		result = prime * result + ((inputTitularIsentoCols == null) ? 0
				: inputTitularIsentoCols.hashCode());
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
		InputTitularIsentoColsDef other = (InputTitularIsentoColsDef) obj;
		if (inputTitularIsentoCols == null) {
			if (other.inputTitularIsentoCols != null)
				return false;
		} else if (!inputTitularIsentoCols.equals(other.inputTitularIsentoCols))
			return false;
		if (tpIsento == null) {
			if (other.tpIsento != null)
				return false;
		} else if (!tpIsento.equals(other.tpIsento))
			return false;
		return true;
	}
}
