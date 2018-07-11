package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class ArquivoOutputDesconhecido extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4168384786061689139L;
	private ArquivoInput arquivoInput;
	private String nameArquivoFormat;
	private String description;

	private List<ArquivoOutputDesconhecidoColsDef> arquivoOutputDesconhecidoColsDefs;

	public ArquivoOutputDesconhecido() {

	}

	public ArquivoInput getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public String getNameArquivoFormat() {
		return nameArquivoFormat;
	}

	public void setNameArquivoFormat(String nameArquivoFormat) {
		this.nameArquivoFormat = nameArquivoFormat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ArquivoOutputDesconhecidoColsDef> getArquivoOutputDesconhecidoColsDefs() {
		return arquivoOutputDesconhecidoColsDefs;
	}

	public void setArquivoOutputDesconhecidoColsDefs(
			List<ArquivoOutputDesconhecidoColsDef> arquivoOutputDesconhecidoColsDefs) {
		this.arquivoOutputDesconhecidoColsDefs = arquivoOutputDesconhecidoColsDefs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arquivoInput == null) ? 0 : arquivoInput.hashCode());
		result = prime * result
				+ ((arquivoOutputDesconhecidoColsDefs == null) ? 0
						: arquivoOutputDesconhecidoColsDefs.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((nameArquivoFormat == null) ? 0
				: nameArquivoFormat.hashCode());
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
		ArquivoOutputDesconhecido other = (ArquivoOutputDesconhecido) obj;
		if (arquivoInput == null) {
			if (other.arquivoInput != null)
				return false;
		} else if (!arquivoInput.equals(other.arquivoInput))
			return false;
		if (arquivoOutputDesconhecidoColsDefs == null) {
			if (other.arquivoOutputDesconhecidoColsDefs != null)
				return false;
		} else if (!arquivoOutputDesconhecidoColsDefs
				.equals(other.arquivoOutputDesconhecidoColsDefs))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (nameArquivoFormat == null) {
			if (other.nameArquivoFormat != null)
				return false;
		} else if (!nameArquivoFormat.equals(other.nameArquivoFormat))
			return false;
		return true;
	}

}
