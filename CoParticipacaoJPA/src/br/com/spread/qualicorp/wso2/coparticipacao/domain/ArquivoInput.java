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

	private Integer regexpContrato;
	private Integer regexpDia;
	private Integer regexpMes;
	private Integer regexpAno;

	private ArquivoOutput arquivoOutput;

	private List<ArquivoInputColsDef> arquivoInputColsDefs;
	private List<Regra> regras;

	private ArquivoOutputDesconhecido arquivoOutputDesconhecido;

	private List<InputBeneficiario> inputBeneficiarios;
	
	private InputTitularIsento inputTitularIsento;
	
	private InputDependenteIsento inputDependenteIsento;

	public ArquivoInput() {
		arquivoInputColsDefs = new ArrayList<>();
		regras = new ArrayList<>();
		inputBeneficiarios = new ArrayList<>();
	}

	public ArquivoInput(ArquivoInput entity) {
		super(entity);
	}

	public String getDescrArquivo() {
		return this.descrArquivo;
	}

	public void setDescrArquivo(String descrArquivo) {
		this.descrArquivo = descrArquivo;
	}

	public String getNameArquivoRegexp() {
		return this.nameArquivoRegexp;
	}

	public void setNameArquivoRegexp(String nameArquivoRegexp) {
		this.nameArquivoRegexp = nameArquivoRegexp;
	}

	public ArquivoType getArquivoType() {
		return this.arquivoType;
	}

	public void setArquivoType(ArquivoType tpArquivo) {
		this.arquivoType = tpArquivo;
	}

	public UseType getUseType() {
		return this.useType;
	}

	public void setUseType(UseType useType) {
		this.useType = useType;
	}

	public List<ArquivoInputColsDef> getArquivoInputColsDefs() {
		return this.arquivoInputColsDefs;
	}

	public void setArquivoInputColsDefs(
			List<ArquivoInputColsDef> arquivoInputColsDefs) {
		this.arquivoInputColsDefs = arquivoInputColsDefs;
	}

	public ArquivoInputColsDef addArquivoInputColsDef(
			ArquivoInputColsDef arquivoInputColsDef) {
		getArquivoInputColsDefs().add(arquivoInputColsDef);
		arquivoInputColsDef.setArquivoInput(this);

		return arquivoInputColsDef;
	}

	public ArquivoInputColsDef removeArquivoInputColsDef(
			ArquivoInputColsDef arquivoInputColsDef) {
		getArquivoInputColsDefs().remove(arquivoInputColsDef);
		arquivoInputColsDef.setArquivoInput(null);

		return arquivoInputColsDef;
	}

	public List<Regra> getRegras() {
		return this.regras;
	}

	public void setRegras(List<Regra> regras) {
		this.regras = regras;
	}

	public Regra addRegra(Regra regra) {
		getRegras().add(regra);
		regra.setArquivoInput(this);

		return regra;
	}

	public Regra removeRegra(Regra regra) {
		getRegras().remove(regra);
		regra.setArquivoInput(null);

		return regra;
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

	public ArquivoOutputDesconhecido getArquivoOutputDesconhecido() {
		return arquivoOutputDesconhecido;
	}

	public void setArquivoOutputDesconhecido(
			ArquivoOutputDesconhecido arquivoOutputDesconhecido) {
		this.arquivoOutputDesconhecido = arquivoOutputDesconhecido;
	}

	public Integer getDefaultLineLength() {
		return defaultLineLength;
	}

	public void setDefaultLineLength(Integer defaultLineLength) {
		this.defaultLineLength = defaultLineLength;
	}

	public Integer getRegexpContrato() {
		return regexpContrato;
	}

	public void setRegexpContrato(Integer regexpContrato) {
		this.regexpContrato = regexpContrato;
	}

	public Integer getRegexpMes() {
		return regexpMes;
	}

	public void setRegexpMes(Integer regexpMes) {
		this.regexpMes = regexpMes;
	}

	public Integer getRegexpAno() {
		return regexpAno;
	}

	public void setRegexpAno(Integer regexpAno) {
		this.regexpAno = regexpAno;
	}

	public ArquivoOutput getArquivoOutput() {
		return arquivoOutput;
	}

	public void setArquivoOutput(ArquivoOutput arquivoOutput) {
		this.arquivoOutput = arquivoOutput;
	}

	public Integer getRegexpDia() {
		return regexpDia;
	}

	public void setRegexpDia(Integer regexpDia) {
		this.regexpDia = regexpDia;
	}

	public List<InputBeneficiario> getInputBeneficiarios() {
		return inputBeneficiarios;
	}

	public void setInputBeneficiarios(
			List<InputBeneficiario> inputBeneficiarios) {
		this.inputBeneficiarios = inputBeneficiarios;
	}

	public void addInputBeneficiario(InputBeneficiario inputBeneficiario) {
		getInputBeneficiarios().add(inputBeneficiario);
		inputBeneficiario.setArquivoInput(this);
	}

	public void removeInputBeneficiario(InputBeneficiario inputBeneficiario) {
		getInputBeneficiarios().remove(inputBeneficiario);
		inputBeneficiario.setArquivoInput(null);
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

	public void setInputDependenteIsento(
			InputDependenteIsento inputDependenteIsento) {
		this.inputDependenteIsento = inputDependenteIsento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInputColsDefs == null) ? 0
				: arquivoInputColsDefs.hashCode());
		result = prime * result
				+ ((arquivoOutput == null) ? 0 : arquivoOutput.hashCode());
		result = prime * result + ((arquivoOutputDesconhecido == null) ? 0
				: arquivoOutputDesconhecido.hashCode());
		result = prime * result
				+ ((arquivoType == null) ? 0 : arquivoType.hashCode());
		result = prime * result
				+ ((contrato == null) ? 0 : contrato.hashCode());
		result = prime * result + ((defaultLineLength == null) ? 0
				: defaultLineLength.hashCode());
		result = prime * result
				+ ((descrArquivo == null) ? 0 : descrArquivo.hashCode());
		result = prime * result + ((inputBeneficiarios == null) ? 0
				: inputBeneficiarios.hashCode());
		result = prime * result + ((inputDependenteIsento == null) ? 0
				: inputDependenteIsento.hashCode());
		result = prime * result + ((inputTitularIsento == null) ? 0
				: inputTitularIsento.hashCode());
		result = prime * result + ((nameArquivoRegexp == null) ? 0
				: nameArquivoRegexp.hashCode());
		result = prime * result
				+ ((regexpAno == null) ? 0 : regexpAno.hashCode());
		result = prime * result
				+ ((regexpContrato == null) ? 0 : regexpContrato.hashCode());
		result = prime * result
				+ ((regexpDia == null) ? 0 : regexpDia.hashCode());
		result = prime * result
				+ ((regexpMes == null) ? 0 : regexpMes.hashCode());
		result = prime * result + ((regras == null) ? 0 : regras.hashCode());
		result = prime * result
				+ ((skipLines == null) ? 0 : skipLines.hashCode());
		result = prime * result + ((useType == null) ? 0 : useType.hashCode());
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
		ArquivoInput other = (ArquivoInput) obj;
		if (arquivoInputColsDefs == null) {
			if (other.arquivoInputColsDefs != null)
				return false;
		} else if (!arquivoInputColsDefs.equals(other.arquivoInputColsDefs))
			return false;
		if (arquivoOutput == null) {
			if (other.arquivoOutput != null)
				return false;
		} else if (!arquivoOutput.equals(other.arquivoOutput))
			return false;
		if (arquivoOutputDesconhecido == null) {
			if (other.arquivoOutputDesconhecido != null)
				return false;
		} else if (!arquivoOutputDesconhecido
				.equals(other.arquivoOutputDesconhecido))
			return false;
		if (arquivoType != other.arquivoType)
			return false;
		if (contrato == null) {
			if (other.contrato != null)
				return false;
		} else if (!contrato.equals(other.contrato))
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
		if (nameArquivoRegexp == null) {
			if (other.nameArquivoRegexp != null)
				return false;
		} else if (!nameArquivoRegexp.equals(other.nameArquivoRegexp))
			return false;
		if (regexpAno == null) {
			if (other.regexpAno != null)
				return false;
		} else if (!regexpAno.equals(other.regexpAno))
			return false;
		if (regexpContrato == null) {
			if (other.regexpContrato != null)
				return false;
		} else if (!regexpContrato.equals(other.regexpContrato))
			return false;
		if (regexpDia == null) {
			if (other.regexpDia != null)
				return false;
		} else if (!regexpDia.equals(other.regexpDia))
			return false;
		if (regexpMes == null) {
			if (other.regexpMes != null)
				return false;
		} else if (!regexpMes.equals(other.regexpMes))
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