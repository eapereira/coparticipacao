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

	private InputTitularIsento inputTitularIsento;

	private InputTitularIsentoColsDef inputTitularIsentoColsDef;

	private ArquivoInputColsDef arquivoInputColsDef;

	public InputTitularIsentoCols() {

	}

	public InputTitularIsento getInputTitularIsento() {
		return inputTitularIsento;
	}

	public void setInputTitularIsento(InputTitularIsento inputTitularIsento) {
		this.inputTitularIsento = inputTitularIsento;
	}

	public InputTitularIsentoColsDef getInputTitularIsentoColsDef() {
		return inputTitularIsentoColsDef;
	}

	public void setInputTitularIsentoColsDef(InputTitularIsentoColsDef inputTitularIsentoColsDef) {
		this.inputTitularIsentoColsDef = inputTitularIsentoColsDef;
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
		result = prime * result + ((arquivoInputColsDef == null) ? 0 : arquivoInputColsDef.hashCode());
		result = prime * result + ((inputTitularIsento == null) ? 0 : inputTitularIsento.hashCode());
		result = prime * result + ((inputTitularIsentoColsDef == null) ? 0 : inputTitularIsentoColsDef.hashCode());
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
		if (inputTitularIsentoColsDef == null) {
			if (other.inputTitularIsentoColsDef != null)
				return false;
		} else if (!inputTitularIsentoColsDef.equals(other.inputTitularIsentoColsDef))
			return false;
		return true;
	}
}
