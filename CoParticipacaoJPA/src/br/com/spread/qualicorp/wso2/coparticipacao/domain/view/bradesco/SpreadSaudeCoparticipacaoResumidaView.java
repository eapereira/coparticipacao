package br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco;

import java.math.BigDecimal;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class SpreadSaudeCoparticipacaoResumidaView extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8407650218898367549L;

	private Integer mes;

	private Integer ano;

	private Long idContrato;

	private Long matricula;
	
	private Long matriculaEmpresa;
	
	private Long matriculaEspecial;

	private String subFatura;

	private String nameEmpresa;

	private String nameTitular;

	private BigDecimal valorPrincipal;
	
	private BigDecimal valorParticipacao;

	private BigDecimal valorIsento;

	private String plano;

	public SpreadSaudeCoparticipacaoResumidaView() {
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
		result = prime * result + ((idContrato == null) ? 0 : idContrato.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((matriculaEmpresa == null) ? 0 : matriculaEmpresa.hashCode());
		result = prime * result + ((matriculaEspecial == null) ? 0 : matriculaEspecial.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((nameEmpresa == null) ? 0 : nameEmpresa.hashCode());
		result = prime * result + ((nameTitular == null) ? 0 : nameTitular.hashCode());
		result = prime * result + ((plano == null) ? 0 : plano.hashCode());
		result = prime * result + ((subFatura == null) ? 0 : subFatura.hashCode());
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
		SpreadSaudeCoparticipacaoResumidaView other = (SpreadSaudeCoparticipacaoResumidaView) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
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
