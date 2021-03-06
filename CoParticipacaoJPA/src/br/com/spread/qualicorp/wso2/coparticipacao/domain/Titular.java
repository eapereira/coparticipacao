package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;

/**
 * The persistent class for the tb_titular database table.
 * 
 */
public abstract class Titular extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private LocalDate dtAdmissao;

	private LocalDate dtDemissao;

	private LocalDate dtNascimento;
	private String nameTitular;
	private Long cpf;

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

	private List<Lancamento> lancamentos;

	private List<Dependente> dependentes;

	private List<TitularIsento> titularIsentos;

	private Contrato contrato;

	private BeneficiarioDetail beneficiarioDetail;

	public Titular() {
		lancamentos = new ArrayList<>();
		dependentes = new ArrayList<>();
		titularIsentos = new ArrayList<>();

		beneficiarioDetail = new BeneficiarioDetail();
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

	public Long getCpf() {
		return this.cpf;
	}

	public void setCpf(Long nrCpf) {
		this.cpf = nrCpf;
	}

	/**
	 * Código de matricula usado pela operadora, campo fornecido pelos arquivos
	 * <code.MECSAS</code>
	 */
	public Long getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Long nrMatricula) {
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

	public LocalDate getDtDemissao() {
		return dtDemissao;
	}

	public void setDtDemissao(LocalDate dtDemissao) {
		this.dtDemissao = dtDemissao;
	}

	@Embedded
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
		result = prime * result + ((dependentes == null) ? 0 : dependentes.hashCode());
		result = prime * result + ((dtAdmissao == null) ? 0 : dtAdmissao.hashCode());
		result = prime * result + ((dtDemissao == null) ? 0 : dtDemissao.hashCode());
		result = prime * result + ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((lancamentos == null) ? 0 : lancamentos.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((matriculaEmpresa == null) ? 0 : matriculaEmpresa.hashCode());
		result = prime * result + ((nameTitular == null) ? 0 : nameTitular.hashCode());
		result = prime * result + ((referenceCode == null) ? 0 : referenceCode.hashCode());
		result = prime * result + ((titularIsentos == null) ? 0 : titularIsentos.hashCode());
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
		Titular other = (Titular) obj;
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
		if (dependentes == null) {
			if (other.dependentes != null)
				return false;
		} else if (!dependentes.equals(other.dependentes))
			return false;
		if (dtAdmissao == null) {
			if (other.dtAdmissao != null)
				return false;
		} else if (!dtAdmissao.equals(other.dtAdmissao))
			return false;
		if (dtDemissao == null) {
			if (other.dtDemissao != null)
				return false;
		} else if (!dtDemissao.equals(other.dtDemissao))
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
		if (nameTitular == null) {
			if (other.nameTitular != null)
				return false;
		} else if (!nameTitular.equals(other.nameTitular))
			return false;
		if (referenceCode == null) {
			if (other.referenceCode != null)
				return false;
		} else if (!referenceCode.equals(other.referenceCode))
			return false;
		if (titularIsentos == null) {
			if (other.titularIsentos != null)
				return false;
		} else if (!titularIsentos.equals(other.titularIsentos))
			return false;
		return true;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}