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
	private Long cpf;
	private BeneficiarioType tpDependente;

	/**
	 * Código de matricula usado pela operadora, campo fornecido pelos arquivos
	 * <code.MECSAS</code>
	 */
	private Long matricula;

	/**
	 * Código de matricula da empresa cliente, nem todas utilizam este número.
	 * Seu valor é carregado por arquivo chamados de <b>Base de Ativos</b> da
	 * empresa, são um tipo de <code>MECSAS</code> que atualiza alguns campos do
	 * {@link Titular} e do {@link Dependente}.
	 */
	private Long matriculaEmpresa;

	private String label;

	private Long referenceCode;

	private Titular titular;

	private List<DependenteIsento> dependenteIsentos;
	private List<Lancamento> lancamentos;

	private BeneficiarioDetail beneficiarioDetail;

	public Dependente() {
		dependenteIsentos = new ArrayList<>();
		lancamentos = new ArrayList<>();

		beneficiarioDetail = new BeneficiarioDetail();
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

	public Long getCpf() {
		return this.cpf;
	}

	public void setCpf(Long cpf) {
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

	/**
	 * Código de matricula usado pela operadora, campo fornecido pelos arquivos
	 * <code.MECSAS</code>
	 */
	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(Long referenceCode) {
		this.referenceCode = referenceCode;
	}

	/**
	 * Código de matricula da empresa cliente, nem todas utilizam este número.
	 * Seu valor é carregado por arquivo chamados de <b>Base de Ativos</b> da
	 * empresa, são um tipo de <code>MECSAS</code> que atualiza alguns campos do
	 * {@link Titular} e do {@link Dependente}.
	 */
	public Long getMatriculaEmpresa() {
		return matriculaEmpresa;
	}

	public void setMatriculaEmpresa(Long matriculaEmpresa) {
		this.matriculaEmpresa = matriculaEmpresa;
	}

	public BeneficiarioDetail getBeneficiarioDetail() {
		return beneficiarioDetail;
	}

	public void setBeneficiarioDetail(BeneficiarioDetail beneficiarioDetail) {
		this.beneficiarioDetail = beneficiarioDetail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beneficiarioDetail == null) ? 0 : beneficiarioDetail.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dependenteIsentos == null) ? 0 : dependenteIsentos.hashCode());
		result = prime * result + ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((lancamentos == null) ? 0 : lancamentos.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((matriculaEmpresa == null) ? 0 : matriculaEmpresa.hashCode());
		result = prime * result + ((nameDependente == null) ? 0 : nameDependente.hashCode());
		result = prime * result + ((referenceCode == null) ? 0 : referenceCode.hashCode());
		result = prime * result + ((tpDependente == null) ? 0 : tpDependente.hashCode());
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
		Dependente other = (Dependente) obj;
		if (beneficiarioDetail == null) {
			if (other.beneficiarioDetail != null)
				return false;
		} else if (!beneficiarioDetail.equals(other.beneficiarioDetail))
			return false;
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
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
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
		if (matriculaEmpresa == null) {
			if (other.matriculaEmpresa != null)
				return false;
		} else if (!matriculaEmpresa.equals(other.matriculaEmpresa))
			return false;
		if (nameDependente == null) {
			if (other.nameDependente != null)
				return false;
		} else if (!nameDependente.equals(other.nameDependente))
			return false;
		if (referenceCode == null) {
			if (other.referenceCode != null)
				return false;
		} else if (!referenceCode.equals(other.referenceCode))
			return false;
		if (tpDependente != other.tpDependente)
			return false;
		return true;
	}

}