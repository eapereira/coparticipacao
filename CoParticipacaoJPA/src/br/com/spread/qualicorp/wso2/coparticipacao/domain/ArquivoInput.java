package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_arquivo_input database table.
 * 
 */
public abstract class ArquivoInput extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private String descrArquivo;

	private String nameArquivoRegexp;
	private ArquivoType arquivoType;
	private UseType useType;

	private Contrato contrato;

	private Integer skipLines;

	private Integer defaultLineLength;

	private List<ArquivoOutput> arquivoOutputs;

	private ArquivoOutputDesconhecido arquivoOutputDesconhecido;

	private List<ArquivoInputSheet> arquivoInputSheets;

	public ArquivoInput() {
		arquivoOutputs = new ArrayList<>();
	}

	public String getDescrArquivo() {
		return descrArquivo;
	}

	public void setDescrArquivo(String descrArquivo) {
		this.descrArquivo = descrArquivo;
	}

	public String getNameArquivoRegexp() {
		return nameArquivoRegexp;
	}

	public void setNameArquivoRegexp(String nameArquivoRegexp) {
		this.nameArquivoRegexp = nameArquivoRegexp;
	}

	public ArquivoType getArquivoType() {
		return arquivoType;
	}

	public void setArquivoType(ArquivoType arquivoType) {
		this.arquivoType = arquivoType;
	}

	public UseType getUseType() {
		return useType;
	}

	public void setUseType(UseType useType) {
		this.useType = useType;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Integer getSkipLines() {
		return skipLines;
	}

	public void setSkipLines(Integer skipLines) {
		this.skipLines = skipLines;
	}

	public Integer getDefaultLineLength() {
		return defaultLineLength;
	}

	public void setDefaultLineLength(Integer defaultLineLength) {
		this.defaultLineLength = defaultLineLength;
	}

	public ArquivoOutputDesconhecido getArquivoOutputDesconhecido() {
		return arquivoOutputDesconhecido;
	}

	public void setArquivoOutputDesconhecido(ArquivoOutputDesconhecido arquivoOutputDesconhecido) {
		this.arquivoOutputDesconhecido = arquivoOutputDesconhecido;
	}

	public List<ArquivoOutput> getArquivoOutputs() {
		return arquivoOutputs;
	}

	public void setArquivoOutputs(List<ArquivoOutput> arquivoOutputs) {
		this.arquivoOutputs = arquivoOutputs;
	}

	public void addArquivoOutput(ArquivoOutput arquivoOutput) {
		getArquivoOutputs().add(arquivoOutput);
		arquivoOutput.setArquivoInput(this);
	}

	public void removeArquivoOutput(ArquivoOutput arquivoOutput) {
		getArquivoOutputs().remove(arquivoOutput);
		arquivoOutput.setArquivoInput(null);
	}

	public List<ArquivoInputSheet> getArquivoInputSheets() {
		return arquivoInputSheets;
	}

	public void setArquivoInputSheets(List<ArquivoInputSheet> arquivoInputSheets) {
		this.arquivoInputSheets = arquivoInputSheets;
	}

	public void addArquivoInputSheet(ArquivoInputSheet arquivoInputSheet) {
		getArquivoInputSheets().add(arquivoInputSheet);
		arquivoInputSheet.setArquivoInput(this);
	}

	public void removeArquivoInputSheet(ArquivoInputSheet arquivoInputSheet) {
		getArquivoInputSheets().remove(arquivoInputSheet);
		arquivoInputSheet.setArquivoInput(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((arquivoInputSheets == null) ? 0 : arquivoInputSheets.hashCode());
		result = prime * result + ((arquivoOutputDesconhecido == null) ? 0 : arquivoOutputDesconhecido.hashCode());
		result = prime * result + ((arquivoOutputs == null) ? 0 : arquivoOutputs.hashCode());
		result = prime * result + ((arquivoType == null) ? 0 : arquivoType.hashCode());
		result = prime * result + ((defaultLineLength == null) ? 0 : defaultLineLength.hashCode());
		result = prime * result + ((descrArquivo == null) ? 0 : descrArquivo.hashCode());
		result = prime * result + ((nameArquivoRegexp == null) ? 0 : nameArquivoRegexp.hashCode());
		result = prime * result + ((skipLines == null) ? 0 : skipLines.hashCode());
		result = prime * result + ((useType == null) ? 0 : useType.hashCode());
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
		ArquivoInput other = (ArquivoInput) obj;
		if (arquivoInputSheets == null) {
			if (other.arquivoInputSheets != null)
				return false;
		} else if (!arquivoInputSheets.equals(other.arquivoInputSheets))
			return false;
		if (arquivoOutputDesconhecido == null) {
			if (other.arquivoOutputDesconhecido != null)
				return false;
		} else if (!arquivoOutputDesconhecido.equals(other.arquivoOutputDesconhecido))
			return false;
		if (arquivoOutputs == null) {
			if (other.arquivoOutputs != null)
				return false;
		} else if (!arquivoOutputs.equals(other.arquivoOutputs))
			return false;
		if (arquivoType != other.arquivoType)
			return false;
		if (defaultLineLength == null) {
			if (other.defaultLineLength != null)
				return false;
		} else if (!defaultLineLength.equals(other.defaultLineLength))
			return false;
		if (descrArquivo == null) {
			if (other.descrArquivo != null)
				return false;
		} else if (!descrArquivo.equals(other.descrArquivo))
			return false;
		if (nameArquivoRegexp == null) {
			if (other.nameArquivoRegexp != null)
				return false;
		} else if (!nameArquivoRegexp.equals(other.nameArquivoRegexp))
			return false;
		if (skipLines == null) {
			if (other.skipLines != null)
				return false;
		} else if (!skipLines.equals(other.skipLines))
			return false;
		if (useType != other.useType)
			return false;
		return true;
	}

}