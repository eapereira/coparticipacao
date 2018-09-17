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

	private List<ArquivoInputColsDef> arquivoInputColsDefs;
	private List<Regra> regras;

	private ArquivoOutputDesconhecido arquivoOutputDesconhecido;

	private List<InputBeneficiario> inputBeneficiarios;

	private InputTitularIsento inputTitularIsento;

	private InputDependenteIsento inputDependenteIsento;

	private List<IsentoInputSheet> isentoInputSheets;

	public ArquivoInput() {
		arquivoInputColsDefs = new ArrayList<>();
		regras = new ArrayList<>();
		inputBeneficiarios = new ArrayList<>();

		isentoInputSheets = new ArrayList<>();
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

	public List<ArquivoInputColsDef> getArquivoInputColsDefs() {
		return arquivoInputColsDefs;
	}

	public void setArquivoInputColsDefs(List<ArquivoInputColsDef> arquivoInputColsDefs) {
		this.arquivoInputColsDefs = arquivoInputColsDefs;
	}

	public List<Regra> getRegras() {
		return regras;
	}

	public void setRegras(List<Regra> regras) {
		this.regras = regras;
	}

	public ArquivoOutputDesconhecido getArquivoOutputDesconhecido() {
		return arquivoOutputDesconhecido;
	}

	public void setArquivoOutputDesconhecido(ArquivoOutputDesconhecido arquivoOutputDesconhecido) {
		this.arquivoOutputDesconhecido = arquivoOutputDesconhecido;
	}

	public List<InputBeneficiario> getInputBeneficiarios() {
		return inputBeneficiarios;
	}

	public void setInputBeneficiarios(List<InputBeneficiario> inputBeneficiarios) {
		this.inputBeneficiarios = inputBeneficiarios;
	}

	public InputTitularIsento getInputTitularIsento() {
		return inputTitularIsento;
	}

	public void setInputTitularIsento(InputTitularIsento inputTitularIsento) {
		this.inputTitularIsento = inputTitularIsento;
	}

	public InputDependenteIsento getInputDependenteIsento() {
		return inputDependenteIsento;
	}

	public void setInputDependenteIsento(InputDependenteIsento inputDependenteIsento) {
		this.inputDependenteIsento = inputDependenteIsento;
	}

	public List<IsentoInputSheet> getIsentoInputSheets() {
		return isentoInputSheets;
	}

	public void setIsentoInputSheets(List<IsentoInputSheet> isentoInputSheets) {
		this.isentoInputSheets = isentoInputSheets;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((arquivoInputColsDefs == null) ? 0 : arquivoInputColsDefs.hashCode());
		result = prime * result + ((arquivoOutputDesconhecido == null) ? 0 : arquivoOutputDesconhecido.hashCode());
		result = prime * result + ((arquivoOutputs == null) ? 0 : arquivoOutputs.hashCode());
		result = prime * result + ((arquivoType == null) ? 0 : arquivoType.hashCode());
		result = prime * result + ((defaultLineLength == null) ? 0 : defaultLineLength.hashCode());
		result = prime * result + ((descrArquivo == null) ? 0 : descrArquivo.hashCode());
		result = prime * result + ((inputBeneficiarios == null) ? 0 : inputBeneficiarios.hashCode());
		result = prime * result + ((inputDependenteIsento == null) ? 0 : inputDependenteIsento.hashCode());
		result = prime * result + ((inputTitularIsento == null) ? 0 : inputTitularIsento.hashCode());
		result = prime * result + ((isentoInputSheets == null) ? 0 : isentoInputSheets.hashCode());
		result = prime * result + ((nameArquivoRegexp == null) ? 0 : nameArquivoRegexp.hashCode());
		result = prime * result + ((regras == null) ? 0 : regras.hashCode());
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
		if (arquivoInputColsDefs == null) {
			if (other.arquivoInputColsDefs != null)
				return false;
		} else if (!arquivoInputColsDefs.equals(other.arquivoInputColsDefs))
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
		if (inputBeneficiarios == null) {
			if (other.inputBeneficiarios != null)
				return false;
		} else if (!inputBeneficiarios.equals(other.inputBeneficiarios))
			return false;
		if (inputDependenteIsento == null) {
			if (other.inputDependenteIsento != null)
				return false;
		} else if (!inputDependenteIsento.equals(other.inputDependenteIsento))
			return false;
		if (inputTitularIsento == null) {
			if (other.inputTitularIsento != null)
				return false;
		} else if (!inputTitularIsento.equals(other.inputTitularIsento))
			return false;
		if (isentoInputSheets == null) {
			if (other.isentoInputSheets != null)
				return false;
		} else if (!isentoInputSheets.equals(other.isentoInputSheets))
			return false;
		if (nameArquivoRegexp == null) {
			if (other.nameArquivoRegexp != null)
				return false;
		} else if (!nameArquivoRegexp.equals(other.nameArquivoRegexp))
			return false;
		if (regras == null) {
			if (other.regras != null)
				return false;
		} else if (!regras.equals(other.regras))
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