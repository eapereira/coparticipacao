package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_dependente database table.
 * 
 */
public abstract class Dependente extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private LocalDate dtNascimento;
	private String nameDependente;
	private String cpf;
	private BeneficiarioType tpDependente;
	
	private Titular titular;

	private List<DependenteIsento> dependenteIsentos;
	private List<Lancamento> lancamentos;

	public Dependente() {
		dependenteIsentos=new ArrayList<>();
		lancamentos=new ArrayList<>();
	}

	public Dependente(Dependente entity) {
		super(entity);
	}

	public LocalDate getDtNascimento() {
		return this.dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getNameDependente() {
		return this.nameDependente;
	}

	public void setNameDependente(String nameDependente) {
		this.nameDependente = nameDependente;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BeneficiarioType getTpDependente() {
		return this.tpDependente;
	}

	public void setTpDependente(BeneficiarioType tpDependente) {
		this.tpDependente = tpDependente;
	}

	public List<DependenteIsento> getDependenteIsentos() {
		return this.dependenteIsentos;
	}

	public void setDependenteIsentos(List<DependenteIsento> dependenteIsentos) {
		this.dependenteIsentos = dependenteIsentos;
	}

	public DependenteIsento addDependenteIsento(DependenteIsento dependenteIsento) {
		getDependenteIsentos().add(dependenteIsento);
		dependenteIsento.setDependente(this);

		return dependenteIsento;
	}

	public DependenteIsento removeDependenteIsento(DependenteIsento dependenteIsento) {
		getDependenteIsentos().remove(dependenteIsento);
		dependenteIsento.setDependente(null);

		return dependenteIsento;
	}

	public List<Lancamento> getLancamentos() {
		return this.lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Lancamento addLancamento(Lancamento lancamento) {
		getLancamentos().add(lancamento);
		lancamento.setDependente(this);

		return lancamento;
	}

	public Lancamento removeLancamento(Lancamento lancamento) {
		getLancamentos().remove(lancamento);
		lancamento.setDependente(null);

		return lancamento;
	}

	public Titular getTitular() {
		return titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

}