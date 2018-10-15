package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.math.BigDecimal;

/**
 * The persistent class for the tb_lancamento database table.
 * 
 */
public abstract class Lancamento extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private Integer ano;
	private Integer mes;

	private Contrato contrato;
	private Dependente dependente;
	private Titular titular;
	
	private BigDecimal valorPrincipal;

	public Lancamento() {
		super();
	}

	public Lancamento(Lancamento entity) {
		super(entity);
	}

	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Dependente getDependente() {
		return this.dependente;
	}

	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}

	public Titular getTitular() {
		return this.titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public BigDecimal getValorPrincipal() {
		return valorPrincipal;
	}

	public void setValorPrincipal(BigDecimal valorPrincipal) {
		this.valorPrincipal = valorPrincipal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((dependente == null) ? 0 : dependente.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
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
		Lancamento other = (Lancamento) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (dependente == null) {
			if (other.dependente != null)
				return false;
		} else if (!dependente.equals(other.dependente))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		if (valorPrincipal == null) {
			if (other.valorPrincipal != null)
				return false;
		} else if (!valorPrincipal.equals(other.valorPrincipal))
			return false;
		return true;
	}

}