package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class ArquivoInputOutputDesconhecido extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5278662311594578215L;

	private ArquivoInputColsDef arquivoInputColsDef;

	private ArquivoOutputDesconhecidoColsDef arquivoOutputDesconhecidoColsDef;

	public ArquivoInputOutputDesconhecido() {

	}

	public ArquivoInputColsDef getArquivoInputColsDef() {
		return arquivoInputColsDef;
	}

	public void setArquivoInputColsDef(
			ArquivoInputColsDef arquivoInputColsDef) {
		this.arquivoInputColsDef = arquivoInputColsDef;
	}

	public ArquivoOutputDesconhecidoColsDef getArquivoOutputDesconhecidoColsDef() {
		return arquivoOutputDesconhecidoColsDef;
	}

	public void setArquivoOutputDesconhecidoColsDef(
			ArquivoOutputDesconhecidoColsDef arquivoOutputDesconhecidoColsDef) {
		this.arquivoOutputDesconhecidoColsDef = arquivoOutputDesconhecidoColsDef;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInputColsDef == null) ? 0
				: arquivoInputColsDef.hashCode());
		result = prime * result
				+ ((arquivoOutputDesconhecidoColsDef == null) ? 0
						: arquivoOutputDesconhecidoColsDef.hashCode());
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
		ArquivoInputOutputDesconhecido other = (ArquivoInputOutputDesconhecido) obj;
		if (arquivoInputColsDef == null) {
			if (other.arquivoInputColsDef != null)
				return false;
		} else if (!arquivoInputColsDef.equals(other.arquivoInputColsDef))
			return false;
		if (arquivoOutputDesconhecidoColsDef == null) {
			if (other.arquivoOutputDesconhecidoColsDef != null)
				return false;
		} else if (!arquivoOutputDesconhecidoColsDef
				.equals(other.arquivoOutputDesconhecidoColsDef))
			return false;
		return true;
	}

}
