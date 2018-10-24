package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.time.LocalDate;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class Beneficiario extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -915793768611562251L;

	private LocalDate dtNascimento;

	private String nameBeneficiario;

	private Long cpf;

	private Integer digitoCpf;

	private Long matricula;

	private Long matriculaEmpresa;

	private BeneficiarioType type;

	private LocalDate dtAdmissao;

	private String label;

	private Long referenceCode;

	private LocalDate dtDemissao;

	private String nameTitular;

	private Long matriculaTitular;
	
	private String cdContrato;

	private BeneficiarioDetail beneficiarioDetail;

	public Beneficiario() {
		beneficiarioDetail = new BeneficiarioDetail();
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getNameBeneficiario() {
		return nameBeneficiario;
	}

	public void setNameBeneficiario(String nameBeneficiario) {
		this.nameBeneficiario = nameBeneficiario;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Integer getDigitoCpf() {
		return digitoCpf;
	}

	public void setDigitoCpf(Integer digitoCpf) {
		this.digitoCpf = digitoCpf;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public Long getMatriculaEmpresa() {
		return matriculaEmpresa;
	}

	public void setMatriculaEmpresa(Long matriculaEmpresa) {
		this.matriculaEmpresa = matriculaEmpresa;
	}

	public BeneficiarioType getType() {
		return type;
	}

	public void setType(BeneficiarioType type) {
		this.type = type;
	}

	public LocalDate getDtAdmissao() {
		return dtAdmissao;
	}

	public void setDtAdmissao(LocalDate dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
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

	public LocalDate getDtDemissao() {
		return dtDemissao;
	}

	public void setDtDemissao(LocalDate dtDemissao) {
		this.dtDemissao = dtDemissao;
	}

	public String getNameTitular() {
		return nameTitular;
	}

	public void setNameTitular(String nameTitular) {
		this.nameTitular = nameTitular;
	}

	public Long getMatriculaTitular() {
		return matriculaTitular;
	}

	public void setMatriculaTitular(Long matriculaTitular) {
		this.matriculaTitular = matriculaTitular;
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
		result = prime * result + ((digitoCpf == null) ? 0 : digitoCpf.hashCode());
		result = prime * result + ((dtAdmissao == null) ? 0 : dtAdmissao.hashCode());
		result = prime * result + ((dtDemissao == null) ? 0 : dtDemissao.hashCode());
		result = prime * result + ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((matriculaEmpresa == null) ? 0 : matriculaEmpresa.hashCode());
		result = prime * result + ((matriculaTitular == null) ? 0 : matriculaTitular.hashCode());
		result = prime * result + ((nameBeneficiario == null) ? 0 : nameBeneficiario.hashCode());
		result = prime * result + ((nameTitular == null) ? 0 : nameTitular.hashCode());
		result = prime * result + ((referenceCode == null) ? 0 : referenceCode.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Beneficiario other = (Beneficiario) obj;
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
		if (digitoCpf == null) {
			if (other.digitoCpf != null)
				return false;
		} else if (!digitoCpf.equals(other.digitoCpf))
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
		if (matriculaTitular == null) {
			if (other.matriculaTitular != null)
				return false;
		} else if (!matriculaTitular.equals(other.matriculaTitular))
			return false;
		if (nameBeneficiario == null) {
			if (other.nameBeneficiario != null)
				return false;
		} else if (!nameBeneficiario.equals(other.nameBeneficiario))
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
		if (type != other.type)
			return false;
		return true;
	}

	public String getCdContrato() {
		return cdContrato;
	}

	public void setCdContrato(String cdContrato) {
		this.cdContrato = cdContrato;
	}

}
