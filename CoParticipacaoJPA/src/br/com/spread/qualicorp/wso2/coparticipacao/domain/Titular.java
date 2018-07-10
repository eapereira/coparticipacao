package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_titular database table.
 * 
 */
public abstract class Titular extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private LocalDate dtAdmissao;

	private LocalDate dtNascimento;
	private String nameTitular;
	private String cpf;
	private Integer matricula;
	private List<Lancamento> lancamentos;

	private List<Dependente> dependentes;

	private List<TitularIsento> titularIsentos;
	
	private Empresa empresa;

	public Titular() {
		lancamentos=new ArrayList<>();
		dependentes=new ArrayList<>();
		titularIsentos=new ArrayList<>();
	}

	public Titular(Titular entity) {
		super(entity);
	}

	public LocalDate getDtAdmissao() {
		return this.dtAdmissao;
	}

	public void setDtAdmissao(LocalDate dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public LocalDate getDtNascimento() {
		return this.dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getNameTitular() {
		return this.nameTitular;
	}

	public void setNameTitular(String nmTitular) {
		this.nameTitular = nmTitular;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String nrCpf) {
		this.cpf = nrCpf;
	}

	public Integer getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Integer nrMatricula) {
		this.matricula = nrMatricula;
	}

	public List<Lancamento> getLancamentos() {
		return this.lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Lancamento addLancamento(Lancamento lancamento) {
		getLancamentos().add(lancamento);
		lancamento.setTitular(this);

		return lancamento;
	}

	public Lancamento removeLancamento(Lancamento lancamento) {
		getLancamentos().remove(lancamento);
		lancamento.setTitular(null);

		return lancamento;
	}

	public List<TitularIsento> getTitularIsentos() {
		return this.titularIsentos;
	}

	public void setTitularIsentos(List<TitularIsento> titularIsentos) {
		this.titularIsentos = titularIsentos;
	}

	public TitularIsento addTitularIsento(TitularIsento titularIsento) {
		getTitularIsentos().add(titularIsento);
		titularIsento.setTitular(this);

		return titularIsento;
	}

	public TitularIsento removeTitularIsento(TitularIsento titularIsento) {
		getTitularIsentos().remove(titularIsento);
		titularIsento.setTitular(null);

		return titularIsento;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	public Dependente addDependente(Dependente dependente) {
		getDependentes().add(dependente);
		dependente.setTitular(this);

		return dependente;
	}

	public Dependente removeDependente(Dependente dependente) {
		getDependentes().remove(dependente);
		dependente.setTitular(null);

		return dependente;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}