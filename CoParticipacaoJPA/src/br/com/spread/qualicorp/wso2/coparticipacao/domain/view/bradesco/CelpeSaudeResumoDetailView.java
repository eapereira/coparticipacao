package br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco;

import java.math.BigDecimal;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class CelpeSaudeResumoDetailView extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2378498835656983230L;

	private Integer mes;
	private Integer ano;

	private String cdContrato;
	private String subEstipulante;
	private Integer vidas;
	private BigDecimal valor;
	private Float percentualVidas;
	private Float percentualValor;

	public CelpeSaudeResumoDetailView() {
		super();
	}

	public String getCdContrato() {
		return cdContrato;
	}

	public void setCdContrato(String cdContrato) {
		this.cdContrato = cdContrato;
	}

	public String getSubEstipulante() {
		return subEstipulante;
	}

	public void setSubEstipulante(String subEstipulante) {
		this.subEstipulante = subEstipulante;
	}

	public Integer getVidas() {
		return vidas;
	}

	public void setVidas(Integer vidas) {
		this.vidas = vidas;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Float getPercentualVidas() {
		return percentualVidas;
	}

	public void setPercentualVidas(Float percentualVidas) {
		this.percentualVidas = percentualVidas;
	}

	public Float getPercentualValor() {
		return percentualValor;
	}

	public void setPercentualValor(Float percentualValor) {
		this.percentualValor = percentualValor;
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
		result = prime * result + ((cdContrato == null) ? 0 : cdContrato.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((percentualValor == null) ? 0 : percentualValor.hashCode());
		result = prime * result + ((percentualVidas == null) ? 0 : percentualVidas.hashCode());
		result = prime * result + ((subEstipulante == null) ? 0 : subEstipulante.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + ((vidas == null) ? 0 : vidas.hashCode());
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
		CelpeSaudeResumoDetailView other = (CelpeSaudeResumoDetailView) obj;
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
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		if (percentualValor == null) {
			if (other.percentualValor != null)
				return false;
		} else if (!percentualValor.equals(other.percentualValor))
			return false;
		if (percentualVidas == null) {
			if (other.percentualVidas != null)
				return false;
		} else if (!percentualVidas.equals(other.percentualVidas))
			return false;
		if (subEstipulante == null) {
			if (other.subEstipulante != null)
				return false;
		} else if (!subEstipulante.equals(other.subEstipulante))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		if (vidas == null) {
			if (other.vidas != null)
				return false;
		} else if (!vidas.equals(other.vidas))
			return false;
		return true;
	}

}
