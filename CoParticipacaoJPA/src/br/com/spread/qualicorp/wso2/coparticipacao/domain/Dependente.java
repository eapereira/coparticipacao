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
	
	private Long matricula;
	
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

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dependenteIsentos == null) ? 0
				: dependenteIsentos.hashCode());
		result = prime * result
				+ ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
		result = prime * result
				+ ((lancamentos == null) ? 0 : lancamentos.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result
				+ ((nameDependente == null) ? 0 : nameDependente.hashCode());
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
		result = prime * result
				+ ((tpDependente == null) ? 0 : tpDependente.hashCode());
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
		Dependente other = (Dependente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dependenteIsentos == null) {
			if (other.dependenteIsentos != null)
				return false;
		} else if (!dependenteIsentos.equals(other.dependenteIsentos))
			return false;
		if (dtNascimento == null) {
			if (other.dtNascimento != null)
				return false;
		} else if (!dtNascimento.equals(other.dtNascimento))
			return false;
		if (lancamentos == null) {
			if (other.lancamentos != null)
				return false;
		} else if (!lancamentos.equals(other.lancamentos))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nameDependente == null) {
			if (other.nameDependente != null)
				return false;
		} else if (!nameDependente.equals(other.nameDependente))
			return false;
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		if (tpDependente != other.tpDependente)
			return false;
		return true;
	}

}