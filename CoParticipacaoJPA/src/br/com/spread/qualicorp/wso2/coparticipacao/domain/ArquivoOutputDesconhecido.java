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

	private List<ArquivoOutputDesconhecidoSheet> arquivoOutputDesconhecidoSheets;

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

	public List<ArquivoOutputDesconhecidoSheet> getArquivoOutputDesconhecidoSheets() {
		return arquivoOutputDesconhecidoSheets;
	}

	public void setArquivoOutputDesconhecidoSheets(
			List<ArquivoOutputDesconhecidoSheet> arquivoOutputDesconhecidoSheets) {
		this.arquivoOutputDesconhecidoSheets = arquivoOutputDesconhecidoSheets;
	}

	public void addArquivoOutputDesconhecidoSheet(ArquivoOutputDesconhecidoSheet arquivoOutputDesconhecidoSheet) {
		getArquivoOutputDesconhecidoSheets().add(arquivoOutputDesconhecidoSheet);
		arquivoOutputDesconhecidoSheet.setArquivoOutputDesconhecido(this);
	}

	public void removeArquivoOutputDesconhecidoSheet(ArquivoOutputDesconhecidoSheet arquivoOutputDesconhecidoSheet) {
		getArquivoOutputDesconhecidoSheets().remove(arquivoOutputDesconhecidoSheet);
		arquivoOutputDesconhecidoSheet.setArquivoOutputDesconhecido(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((arquivoOutputDesconhecidoSheets == null) ? 0 : arquivoOutputDesconhecidoSheets.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((nameArquivoFormat == null) ? 0 : nameArquivoFormat.hashCode());
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
		ArquivoOutputDesconhecido other = (ArquivoOutputDesconhecido) obj;
		if (arquivoOutputDesconhecidoSheets == null) {
			if (other.arquivoOutputDesconhecidoSheets != null)
				return false;
		} else if (!arquivoOutputDesconhecidoSheets.equals(other.arquivoOutputDesconhecidoSheets))
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
