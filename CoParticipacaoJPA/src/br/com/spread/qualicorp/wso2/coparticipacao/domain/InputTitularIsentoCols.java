package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class InputTitularIsentoCols extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9101612902422916326L;

	private Integer tpIsento;
	
	private InputTitularIsento inputTitularIsento;

	private TitularIsentoColsDef titularIsentoColsDef;

	private ArquivoInputColsDef arquivoInputColsDef;

	public InputTitularIsentoCols() {

	}

	public Integer getTpIsento() {
		return tpIsento;
	}

	public void setTpIsento(Integer tpIsento) {
		this.tpIsento = tpIsento;
	}

	public InputTitularIsento getInputTitularIsento() {
		return inputTitularIsento;
	}

	public void setInputTitularIsento(InputTitularIsento inputTitularIsento) {
		this.inputTitularIsento = inputTitularIsento;
	}

	public TitularIsentoColsDef getTitularIsentoColsDef() {
		return titularIsentoColsDef;
	}

	public void setTitularIsentoColsDef(TitularIsentoColsDef titularIsentoColsDef) {
		this.titularIsentoColsDef = titularIsentoColsDef;
	}

	public ArquivoInputColsDef getArquivoInputColsDef() {
		return arquivoInputColsDef;
	}

	public void setArquivoInputColsDef(ArquivoInputColsDef arquivoInputColsDef) {
		this.arquivoInputColsDef = arquivoInputColsDef;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInputColsDef == null) ? 0
				: arquivoInputColsDef.hashCode());
		result = prime * result + ((inputTitularIsento == null) ? 0
				: inputTitularIsento.hashCode());
		result = prime * result + ((titularIsentoColsDef == null) ? 0
				: titularIsentoColsDef.hashCode());
		result = prime * result
				+ ((tpIsento == null) ? 0 : tpIsento.hashCode());
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
		InputTitularIsentoCols other = (InputTitularIsentoCols) obj;
		if (arquivoInputColsDef == null) {
			if (other.arquivoInputColsDef != null)
				return false;
		} else if (!arquivoInputColsDef.equals(other.arquivoInputColsDef))
			return false;
		if (inputTitularIsento == null) {
			if (other.inputTitularIsento != null)
				return false;
		} else if (!inputTitularIsento.equals(other.inputTitularIsento))
			return false;
		if (titularIsentoColsDef == null) {
			if (other.titularIsentoColsDef != null)
				return false;
		} else if (!titularIsentoColsDef.equals(other.titularIsentoColsDef))
			return false;
		if (tpIsento == null) {
			if (other.tpIsento != null)
				return false;
		} else if (!tpIsento.equals(other.tpIsento))
			return false;
		return true;
	}
}
