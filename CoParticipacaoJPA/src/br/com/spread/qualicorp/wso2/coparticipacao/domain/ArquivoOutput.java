package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_arquivo_output database table.
 * 
 */
public abstract class ArquivoOutput extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7623160858553621671L;

	private String descrArquivo;

	private String nameArquivoFormat;

	private ArquivoInput arquivoInput;

	private List<ArquivoOutputSheet> arquivoOutputSheets;

	public ArquivoOutput() {
		arquivoOutputSheets = new ArrayList<>();
	}

	public ArquivoOutput(ArquivoOutput entity) {
		super(entity);
	}

	public String getDescrArquivo() {
		return this.descrArquivo;
	}

	public void setDescrArquivo(String descrArquivo) {
		this.descrArquivo = descrArquivo;
	}

	public String getNameArquivoFormat() {
		return this.nameArquivoFormat;
	}

	public void setNameArquivoFormat(String nmArquivoFormat) {
		this.nameArquivoFormat = nmArquivoFormat;
	}

	public List<ArquivoOutputSheet> getArquivoOutputSheets() {
		return this.arquivoOutputSheets;
	}

	public void setArquivoOutputSheets(
			List<ArquivoOutputSheet> arquivoOutputSheets) {
		this.arquivoOutputSheets = arquivoOutputSheets;
	}

	public ArquivoOutputSheet addArquivoOutputSheet(
			ArquivoOutputSheet arquivoOutputSheet) {
		getArquivoOutputSheets().add(arquivoOutputSheet);
		arquivoOutputSheet.setArquivoOutput(this);

		return arquivoOutputSheet;
	}

	public ArquivoOutputSheet removeArquivoOutputSheet(
			ArquivoOutputSheet arquivoOutputSheet) {
		getArquivoOutputSheets().remove(arquivoOutputSheet);
		arquivoOutputSheet.setArquivoOutput(null);

		return arquivoOutputSheet;
	}

	public ArquivoInput getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arquivoInput == null) ? 0 : arquivoInput.hashCode());
		result = prime * result + ((arquivoOutputSheets == null) ? 0
				: arquivoOutputSheets.hashCode());
		result = prime * result
				+ ((descrArquivo == null) ? 0 : descrArquivo.hashCode());
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
		ArquivoOutput other = (ArquivoOutput) obj;
		if (arquivoInput == null) {
			if (other.arquivoInput != null)
				return false;
		} else if (!arquivoInput.equals(other.arquivoInput))
			return false;
		if (arquivoOutputSheets == null) {
			if (other.arquivoOutputSheets != null)
				return false;
		} else if (!arquivoOutputSheets.equals(other.arquivoOutputSheets))
			return false;
		if (descrArquivo == null) {
			if (other.descrArquivo != null)
				return false;
		} else if (!descrArquivo.equals(other.descrArquivo))
			return false;
		if (nameArquivoFormat == null) {
			if (other.nameArquivoFormat != null)
				return false;
		} else if (!nameArquivoFormat.equals(other.nameArquivoFormat))
			return false;
		return true;
	}

}