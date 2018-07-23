package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

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

	private List<LancamentoDetail> lancamentoDetails;

	public Lancamento() {
		lancamentoDetails = new ArrayList<>();
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

	public List<LancamentoDetail> getLancamentoDetails() {
		return lancamentoDetails;
	}

	public void setLancamentoDetails(List<LancamentoDetail> lancamentoDetails) {
		this.lancamentoDetails = lancamentoDetails;
	}

	public void addLancamentoDetail(LancamentoDetail lancamentoDetail) {
		getLancamentoDetails().add(lancamentoDetail);
		lancamentoDetail.setLancamento(this);
	}

	public void removeLAncamentoDetail(LancamentoDetail lancamentoDetail) {
		if (getLancamentoDetails().contains(lancamentoDetail)) {
			getLancamentoDetails().remove(lancamentoDetail);
			lancamentoDetail.setLancamento(null);
		}
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((contrato == null) ? 0 : contrato.hashCode());
		result = prime * result + ((dependente == null) ? 0 : dependente.hashCode());
		result = prime * result + ((lancamentoDetails == null) ? 0 : lancamentoDetails.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (contrato == null) {
			if (other.contrato != null)
				return false;
		} else if (!contrato.equals(other.contrato))
			return false;
		if (dependente == null) {
			if (other.dependente != null)
				return false;
		} else if (!dependente.equals(other.dependente))
			return false;
		if (lancamentoDetails == null) {
			if (other.lancamentoDetails != null)
				return false;
		} else if (!lancamentoDetails.equals(other.lancamentoDetails))
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
		return true;
	}

}