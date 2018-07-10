package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_empresa database table.
 * 
 */
public abstract class Empresa extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private String nameEmpresa;
	private List<ArquivoOutput> arquivoOutputs;
	private List<Contrato> contratos;

	private Operadora operadora;

	private List<Parameter> parameters;

	private List<Titular> titulars;

	private List<Lancamento> lancamentos;

	public Empresa() {
		arquivoOutputs = new ArrayList<>();
		parameters = new ArrayList<>();
		titulars = new ArrayList<>();
		lancamentos = new ArrayList<>();
	}

	public Empresa(Empresa empresa) {
		super(empresa);
	}

	public String getNameEmpresa() {
		return this.nameEmpresa;
	}

	public void setNameEmpresa(String nameEmpresa) {
		this.nameEmpresa = nameEmpresa;
	}

	public List<ArquivoOutput> getArquivoOutputs() {
		return this.arquivoOutputs;
	}

	public void setArquivoOutputs(List<ArquivoOutput> arquivoOutputs) {
		this.arquivoOutputs = arquivoOutputs;
	}

	public ArquivoOutput addArquivoOutput(ArquivoOutput arquivoOutput) {
		getArquivoOutputs().add(arquivoOutput);
		arquivoOutput.setEmpresa(this);

		return arquivoOutput;
	}

	public ArquivoOutput removeArquivoOutput(ArquivoOutput arquivoOutput) {
		getArquivoOutputs().remove(arquivoOutput);
		arquivoOutput.setEmpresa(null);

		return arquivoOutput;
	}

	public List<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public Contrato addContrato(Contrato contrato) {
		getContratos().add(contrato);
		contrato.setEmpresa(this);

		return contrato;
	}

	public Contrato removeContrato(Contrato contrato) {
		getContratos().remove(contrato);
		contrato.setEmpresa(null);

		return contrato;
	}

	public Parameter addParameter(Parameter parameter) {
		getParameters().add(parameter);
		parameter.setEmpresa(this);

		return parameter;
	}

	public List<Parameter> getParameters() {
		return this.parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public List<Titular> getTitulars() {
		return titulars;
	}

	public void setTitulars(List<Titular> titulars) {
		this.titulars = titulars;
	}

	public Titular removeTitular(Titular titular) {
		getTitulars().remove(titular);
		titular.setEmpresa(null);

		return titular;
	}

	public Titular addTitular(Titular titular) {
		getTitulars().add(titular);
		titular.setEmpresa(this);

		return titular;
	}

	public Operadora getOperadora() {
		return operadora;
	}

	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

}
