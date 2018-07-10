package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class ArquivoOutputDesconhecidoColsDef extends AbstractDomain{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5744067170574192256L;
	private ArquivoOutputDesconhecido arquivoOutputDesconhecido;
	private ArquivoInputColsDef arquivoInputColsDef;
	
	public ArquivoOutputDesconhecidoColsDef(){
		
	}

	public ArquivoOutputDesconhecido getArquivoOutputDesconhecido() {
		return arquivoOutputDesconhecido;
	}

	public void setArquivoOutputDesconhecido(
			ArquivoOutputDesconhecido arquivoOutputDesconhecido) {
		this.arquivoOutputDesconhecido = arquivoOutputDesconhecido;
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
		result = prime * result + ((arquivoOutputDesconhecido == null) ? 0
				: arquivoOutputDesconhecido.hashCode());
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
		ArquivoOutputDesconhecidoColsDef other = (ArquivoOutputDesconhecidoColsDef) obj;
		if (arquivoInputColsDef == null) {
			if (other.arquivoInputColsDef != null)
				return false;
		} else if (!arquivoInputColsDef.equals(other.arquivoInputColsDef))
			return false;
		if (arquivoOutputDesconhecido == null) {
			if (other.arquivoOutputDesconhecido != null)
				return false;
		} else if (!arquivoOutputDesconhecido
				.equals(other.arquivoOutputDesconhecido))
			return false;
		return true;
	}
}
