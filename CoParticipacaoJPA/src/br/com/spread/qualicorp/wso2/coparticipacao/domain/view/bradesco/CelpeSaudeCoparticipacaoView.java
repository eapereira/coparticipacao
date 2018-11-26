package br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco;

import java.math.BigDecimal;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class CelpeSaudeCoparticipacaoView extends AbstractDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4555460510745447322L;

	private Integer mes;
	private Integer ano;
	
	private String cdContrato;
	private String cdUsuario;
	private String nameTitular;
	private String nameBeneficiario;
	private BigDecimal valor;
	private String certificado;
	private String matriculaEspecial;
	private String plano;
	private String subFatura;
	private String carteiraIdentificacao;
	private String cpfBeneficiario;
	
	
	public CelpeSaudeCoparticipacaoView() {
		super();
	}

	public String getCdContrato() {
		return cdContrato;
	}

	public void setCdContrato(String cdContrato) {
		this.cdContrato = cdContrato;
	}

	public String getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getMatriculaEspecial() {
		return matriculaEspecial;
	}

	public void setMatriculaEspecial(String matriculaEspecial) {
		this.matriculaEspecial = matriculaEspecial;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public String getSubFatura() {
		return subFatura;
	}

	public void setSubFatura(String subFatura) {
		this.subFatura = subFatura;
	}

	public String getCarteiraIdentificacao() {
		return carteiraIdentificacao;
	}

	public void setCarteiraIdentificacao(String carteiraIdentificacao) {
		this.carteiraIdentificacao = carteiraIdentificacao;
	}

	public String getCpfBeneficiario() {
		return cpfBeneficiario;
	}

	public void setCpfBeneficiario(String cpfBeneficiario) {
		this.cpfBeneficiario = cpfBeneficiario;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((carteiraIdentificacao == null) ? 0 : carteiraIdentificacao.hashCode());
		result = prime * result + ((cdContrato == null) ? 0 : cdContrato.hashCode());
		result = prime * result + ((cdUsuario == null) ? 0 : cdUsuario.hashCode());
		result = prime * result + ((certificado == null) ? 0 : certificado.hashCode());
		result = prime * result + ((cpfBeneficiario == null) ? 0 : cpfBeneficiario.hashCode());
		result = prime * result + ((matriculaEspecial == null) ? 0 : matriculaEspecial.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((nameBeneficiario == null) ? 0 : nameBeneficiario.hashCode());
		result = prime * result + ((nameTitular == null) ? 0 : nameTitular.hashCode());
		result = prime * result + ((plano == null) ? 0 : plano.hashCode());
		result = prime * result + ((subFatura == null) ? 0 : subFatura.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		CelpeSaudeCoparticipacaoView other = (CelpeSaudeCoparticipacaoView) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (carteiraIdentificacao == null) {
			if (other.carteiraIdentificacao != null)
				return false;
		} else if (!carteiraIdentificacao.equals(other.carteiraIdentificacao))
			return false;
		if (cdContrato == null) {
			if (other.cdContrato != null)
				return false;
		} else if (!cdContrato.equals(other.cdContrato))
			return false;
		if (cdUsuario == null) {
			if (other.cdUsuario != null)
				return false;
		} else if (!cdUsuario.equals(other.cdUsuario))
			return false;
		if (certificado == null) {
			if (other.certificado != null)
				return false;
		} else if (!certificado.equals(other.certificado))
			return false;
		if (cpfBeneficiario == null) {
			if (other.cpfBeneficiario != null)
				return false;
		} else if (!cpfBeneficiario.equals(other.cpfBeneficiario))
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
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

}
