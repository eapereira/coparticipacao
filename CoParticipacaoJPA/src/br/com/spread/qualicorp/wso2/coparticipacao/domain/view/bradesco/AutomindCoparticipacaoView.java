package br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco;

import java.math.BigDecimal;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class AutomindCoparticipacaoView extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3078942775061985813L;
	private Integer mes;
	private Integer ano;
	private Long idContrato;
	private String cdContrato;
	private String cdEmpresa;
	
	private Long certificado;
	private Long matricula;
	private String nomeTitular;
	private BigDecimal fatorModerador;
	private Long matriculaEspecial;
	private String profissao;

	public AutomindCoparticipacaoView() {
		super();
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	public String getCdContrato() {
		return cdContrato;
	}

	public void setCdContrato(String cdContrato) {
		this.cdContrato = cdContrato;
	}

	public Long getCertificado() {
		return certificado;
	}

	public void setCertificado(Long certificado) {
		this.certificado = certificado;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public BigDecimal getFatorModerador() {
		return fatorModerador;
	}

	public void setFatorModerador(BigDecimal fatorModerador) {
		this.fatorModerador = fatorModerador;
	}

	public Long getMatriculaEspecial() {
		return matriculaEspecial;
	}

	public void setMatriculaEspecial(Long matriculaEspecial) {
		this.matriculaEspecial = matriculaEspecial;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getCdEmpresa() {
		return cdEmpresa;
	}

	public void setCdEmpresa(String cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((cdContrato == null) ? 0 : cdContrato.hashCode());
		result = prime * result + ((cdEmpresa == null) ? 0 : cdEmpresa.hashCode());
		result = prime * result + ((certificado == null) ? 0 : certificado.hashCode());
		result = prime * result + ((fatorModerador == null) ? 0 : fatorModerador.hashCode());
		result = prime * result + ((idContrato == null) ? 0 : idContrato.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((matriculaEspecial == null) ? 0 : matriculaEspecial.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((nomeTitular == null) ? 0 : nomeTitular.hashCode());
		result = prime * result + ((profissao == null) ? 0 : profissao.hashCode());
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
		AutomindCoparticipacaoView other = (AutomindCoparticipacaoView) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (cdContrato == null) {
			if (other.cdContrato != null)
				return false;
		} else if (!cdContrato.equals(other.cdContrato))
			return false;
		if (cdEmpresa == null) {
			if (other.cdEmpresa != null)
				return false;
		} else if (!cdEmpresa.equals(other.cdEmpresa))
			return false;
		if (certificado == null) {
			if (other.certificado != null)
				return false;
		} else if (!certificado.equals(other.certificado))
			return false;
		if (fatorModerador == null) {
			if (other.fatorModerador != null)
				return false;
		} else if (!fatorModerador.equals(other.fatorModerador))
			return false;
		if (idContrato == null) {
			if (other.idContrato != null)
				return false;
		} else if (!idContrato.equals(other.idContrato))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (matriculaEspecial == null) {
			if (other.matriculaEspecial != null)
				return false;
		} else if (!matriculaEspecial.equals(other.matriculaEspecial))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		if (nomeTitular == null) {
			if (other.nomeTitular != null)
				return false;
		} else if (!nomeTitular.equals(other.nomeTitular))
			return false;
		if (profissao == null) {
			if (other.profissao != null)
				return false;
		} else if (!profissao.equals(other.profissao))
			return false;
		return true;
	}
}
