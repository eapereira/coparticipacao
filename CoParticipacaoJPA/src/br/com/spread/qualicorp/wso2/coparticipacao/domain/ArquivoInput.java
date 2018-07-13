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
}