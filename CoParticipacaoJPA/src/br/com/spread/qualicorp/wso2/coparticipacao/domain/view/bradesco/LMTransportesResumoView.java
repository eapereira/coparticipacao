package br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco;

import java.math.BigDecimal;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class LMTransportesResumoView extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 934960789369893579L;

	private Integer mes;
	private Integer ano;
	private Long idContrato;
	private String cdContrato;
	private String cdEmpresa;

	private Integer qtdeSegurados;
	private BigDecimal valorProporcao;
	private String subFatura;
	private BigDecimal valorAlocacao;

	public LMTransportesResumoView() {
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

	public String getCdEmpresa() {
		return cdEmpresa;
	}

	public void setCdEmpresa(String cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}

	public Integer getQtdeSegurados() {
		return qtdeSegurados;
	}

	public void setQtdeSegurados(Integer qtdeSegurados) {
		this.qtdeSegurados = qtdeSegurados;
	}

	public BigDecimal getValorProporcao() {
		return valorProporcao;
	}

	public void setValorProporcao(BigDecimal valorProporcao) {
		this.valorProporcao = valorProporcao;
	}

	public String getSubFatura() {
		return subFatura;
	}

	public void setSubFatura(String subFatura) {
		this.subFatura = subFatura;
	}

	public BigDecimal getValorAlocacao() {
		return valorAlocacao;
	}

	public void setValorAlocacao(BigDecimal valorAlocacao) {
		this.valorAlocacao = valorAlocacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((cdContrato == null) ? 0 : cdContrato.hashCode());
		result = prime * result + ((cdEmpresa == null) ? 0 : cdEmpresa.hashCode());
		result = prime * result + ((idContrato == null) ? 0 : idContrato.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((qtdeSegurados == null) ? 0 : qtdeSegurados.hashCode());
		result = prime * result + ((subFatura == null) ? 0 : subFatura.hashCode());
		result = prime * result + ((valorAlocacao == null) ? 0 : valorAlocacao.hashCode());
		result = prime * result + ((valorProporcao == null) ? 0 : valorProporcao.hashCode());
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
		LMTransportesResumoView other = (LMTransportesResumoView) obj;
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
		if (idContrato == null) {
			if (other.idContrato != null)
				return false;
		} else if (!idContrato.equals(other.idContrato))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		if (qtdeSegurados == null) {
			if (other.qtdeSegurados != null)
				return false;
		} else if (!qtdeSegurados.equals(other.qtdeSegurados))
			return false;
		if (subFatura == null) {
			if (other.subFatura != null)
				return false;
		} else if (!subFatura.equals(other.subFatura))
			return false;
		if (valorAlocacao == null) {
			if (other.valorAlocacao != null)
				return false;
		} else if (!valorAlocacao.equals(other.valorAlocacao))
			return false;
		if (valorProporcao == null) {
			if (other.valorProporcao != null)
				return false;
		} else if (!valorProporcao.equals(other.valorProporcao))
			return false;
		return true;
	}
}
