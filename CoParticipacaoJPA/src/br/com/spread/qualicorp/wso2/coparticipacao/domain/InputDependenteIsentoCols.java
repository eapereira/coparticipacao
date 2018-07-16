package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class InputDependenteIsentoCols extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 818677883331187364L;

	private InputDependenteIsento inputDependenteIsento;

	private InputDependenteIsentoColsDef inputDependenteIsentoColsDef;

	private ArquivoInputColsDef arquivoInputColsDef;

	public InputDependenteIsentoCols() {

	}

	public InputDependenteIsento getInputDependenteIsento() {
		return inputDependenteIsento;
	}

	public void setInputDependenteIsento(InputDependenteIsento inputDependenteIsento) {
		this.inputDependenteIsento = inputDependenteIsento;
	}

	public InputDependenteIsentoColsDef getInputDependenteIsentoColsDef() {
		return inputDependenteIsentoColsDef;
	}

	public void setInputDependenteIsentoColsDef(InputDependenteIsentoColsDef inputDependenteIsentoColsDef) {
		this.inputDependenteIsentoColsDef = inputDependenteIsentoColsDef;
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
		result = prime * result + ((inputDependenteIsento == null) ? 0 : inputDependenteIsento.hashCode());
		result = prime * result
				+ ((inputDependenteIsentoColsDef == null) ? 0 : inputDependenteIsentoColsDef.hashCode());
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
		InputDependenteIsentoCols other = (InputDependenteIsentoCols) obj;
		if (arquivoInputColsDef == null) {
			if (other.arquivoInputColsDef != null)
				return false;
		} else if (!arquivoInputColsDef.equals(other.arquivoInputColsDef))
			return false;
		if (inputDependenteIsento == null) {
			if (other.inputDependenteIsento != null)
				return false;
		} else if (!inputDependenteIsento.equals(other.inputDependenteIsento))
			return false;
		if (inputDependenteIsentoColsDef == null) {
			if (other.inputDependenteIsentoColsDef != null)
				return false;
		} else if (!inputDependenteIsentoColsDef.equals(other.inputDependenteIsentoColsDef))
			return false;
		return true;
	}

}
