package br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class SpreadSaudeCoparticipacaoView extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2556876838994850396L;

	private Integer mes;
	
	private Integer ano;
	
	private Long idContrato;

	private Long matricula;
	
	private Long matriculaEmpresa;
	
	private Long matriculaEspecial;

	private String subFatura;

	private String nameEmpresa;

	private String nameTitular;

	private String nameBeneficiario;

	private String tpUtilizacao;

	private LocalDate dtUtilizacao;

	private String descrUtilizacao;

	private BigDecimal valorPrincipal;
	
	private BigDecimal valorParticipacao;

	private BigDecimal valorIsento;

	private String tpIsento;
	
	private String plano;

	public SpreadSaudeCoparticipacaoView() {
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

	public Long getMatriculaEspecial() {
		return matriculaEspecial;
	}

	public void setMatriculaEspecial(Long matriculaEspecial) {
		this.matriculaEspecial = matriculaEspecial;
	}

	public String getSubFatura() {
		return subFatura;
	}

	public void setSubFatura(String subFatura) {
		this.subFatura = subFatura;
	}

	public String getNameEmpresa() {
		return nameEmpresa;
	}

	public void setNameEmpresa(String nameEmpresa) {
		this.nameEmpresa = nameEmpresa;
	}

	public String getNameTitular() {
		return nameTitular;
	}

	public void setNameTitular(String nameTitular) {
		this.nameTitular = nameTitular;
	}

	public String getNameBeneficiario() {
		return nameBeneficiario;
	}

	public void setNameBeneficiario(String nameBeneficiario) {
		this.nameBeneficiario = nameBeneficiario;
	}

	public String getTpUtilizacao() {
		return tpUtilizacao;
	}

	public void setTpUtilizacao(String tpUtilizacao) {
		this.tpUtilizacao = tpUtilizacao;
	}

	public LocalDate getDtUtilizacao() {
		return dtUtilizacao;
	}

	public void setDtUtilizacao(LocalDate dtUtilizacao) {
		this.dtUtilizacao = dtUtilizacao;
	}

	public String getDescrUtilizacao() {
		return descrUtilizacao;
	}

	public void setDescrUtilizacao(String descrUtilizacao) {
		this.descrUtilizacao = descrUtilizacao;
	}

	public BigDecimal getValorPrincipal() {
		return valorPrincipal;
	}

	public void setValorPrincipal(BigDecimal valorPrincipal) {
		this.valorPrincipal = valorPrincipal;
	}

	public BigDecimal getValorIsento() {
		return valorIsento;
	}

	public void setValorIsento(BigDecimal valorIsento) {
		this.valorIsento = valorIsento;
	}

	public String getTpIsento() {
		return tpIsento;
	}

	public void setTpIsento(String tpIsento) {
		this.tpIsento = tpIsento;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public BigDecimal getValorParticipacao() {
		return valorParticipacao;
	}

	public void setValorParticipacao(BigDecimal valorParticipacao) {
		this.valorParticipacao = valorParticipacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((descrUtilizacao == null) ? 0 : descrUtilizacao.hashCode());
		result = prime * result + ((dtUtilizacao == null) ? 0 : dtUtilizacao.hashCode());
		result = prime * result + ((idContrato == null) ? 0 : idContrato.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((matriculaEmpresa == null) ? 0 : matriculaEmpresa.hashCode());
		result = prime * result + ((matriculaEspecial == null) ? 0 : matriculaEspecial.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((nameBeneficiario == null) ? 0 : nameBeneficiario.hashCode());
		result = prime * result + ((nameEmpresa == null) ? 0 : nameEmpresa.hashCode());
		result = prime * result + ((nameTitular == null) ? 0 : nameTitular.hashCode());
		result = prime * result + ((plano == null) ? 0 : plano.hashCode());
		result = prime * result + ((subFatura == null) ? 0 : subFatura.hashCode());
		result = prime * result + ((tpIsento == null) ? 0 : tpIsento.hashCode());
		result = prime * result + ((tpUtilizacao == null) ? 0 : tpUtilizacao.hashCode());
		result = prime * result + ((valorIsento == null) ? 0 : valorIsento.hashCode());
		result = prime * result + ((valorParticipacao == null) ? 0 : valorParticipacao.hashCode());
		result = prime * result + ((valorPrincipal == null) ? 0 : valorPrincipal.hashCode());
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
		SpreadSaudeCoparticipacaoView other = (SpreadSaudeCoparticipacaoView) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (descrUtilizacao == null) {
			if (other.descrUtilizacao != null)
				return false;
		} else if (!descrUtilizacao.equals(other.descrUtilizacao))
			return false;
		if (dtUtilizacao == null) {
			if (other.dtUtilizacao != null)
				return false;
		} else if (!dtUtilizacao.equals(other.dtUtilizacao))
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
		if (matriculaEmpresa == null) {
			if (other.matriculaEmpresa != null)
				return false;
		} else if (!matriculaEmpresa.equals(other.matriculaEmpresa))
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
		if (nameBeneficiario == null) {
			if (other.nameBeneficiario != null)
				return false;
		} else if (!nameBeneficiario.equals(other.nameBeneficiario))
			return false;
		if (nameEmpresa == null) {
			if (other.nameEmpresa != null)
				return false;
		} else if (!nameEmpresa.equals(other.nameEmpresa))
			return false;
		if (nameTitular == null) {
			if (other.nameTitular != null)
				return false;
		} else if (!nameTitular.equals(other.nameTitular))
			return false;
		if (plano == null) {
			if (other.plano != null)
				return false;
		} else if (!plano.equals(other.plano))
			return false;
		if (subFatura == null) {
			if (other.subFatura != null)
				return false;
		} else if (!subFatura.equals(other.subFatura))
			return false;
		if (tpIsento == null) {
			if (other.tpIsento != null)
				return false;
		} else if (!tpIsento.equals(other.tpIsento))
			return false;
		if (tpUtilizacao == null) {
			if (other.tpUtilizacao != null)
				return false;
		} else if (!tpUtilizacao.equals(other.tpUtilizacao))
			return false;
		if (valorIsento == null) {
			if (other.valorIsento != null)
				return false;
		} else if (!valorIsento.equals(other.valorIsento))
			return false;
		if (valorParticipacao == null) {
			if (other.valorParticipacao != null)
				return false;
		} else if (!valorParticipacao.equals(other.valorParticipacao))
			return false;
		if (valorPrincipal == null) {
			if (other.valorPrincipal != null)
				return false;
		} else if (!valorPrincipal.equals(other.valorPrincipal))
			return false;
		return true;
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

}
