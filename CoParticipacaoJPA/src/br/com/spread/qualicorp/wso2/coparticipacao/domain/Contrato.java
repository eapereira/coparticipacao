package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * The persistent class for the tb_contrato database table.
 * 
 */
public abstract class Contrato extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private String cdContrato;

	private String nameContrato;
	private Empresa empresa;

	private ArquivoInput arquivoInput;
	
	private UseType useType;

	private List<Lancamento> lancamentos;
	
	private Boolean spreadsheetAllPages;

	public Contrato() {
		lancamentos = new ArrayList<>();
	}

	public Contrato(Contrato entity) {
		super(entity);
	}

	@Column(name = "CD_CONTRATO")
	public String getCdContrato() {
		return this.cdContrato;
	}

	public void setCdContrato(String cdContrato) {
		this.cdContrato = cdContrato;
	}

	@Column(name = "NM_CONTRATO")
	public String getNameContrato() {
		return this.nameContrato;
	}

	public void setNameContrato(String nameContrato) {
		this.nameContrato = nameContrato;
	}

	// bi-directional many-to-one association to Empresa
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EMPRESA")
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	// bi-directional many-to-one association to Lancamento
	@OneToMany(mappedBy = "contrato")
	public List<Lancamento> getLancamentos() {
		return this.lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Lancamento addLancamento(Lancamento lancamento) {
		getLancamentos().add(lancamento);
		lancamento.setContrato(this);

		return lancamento;
	}

	public Lancamento removeLancamento(Lancamento lancamento) {
		getLancamentos().remove(lancamento);
		lancamento.setContrato(null);

		return lancamento;
	}

	// bi-directional many-to-one association to Lancamento
	@OneToOne(mappedBy = "contrato")
	public ArquivoInput getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public UseType getUseType() {
		return useType;
	}

	public void setUseType(UseType useType) {
		this.useType = useType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean isSpreadsheetAllPages() {
		return spreadsheetAllPages;
	}

	public void setSpreadsheetAllPages(Boolean spreadsheetAllPages) {
		this.spreadsheetAllPages = spreadsheetAllPages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInput == null) ? 0 : arquivoInput.hashCode());
		result = prime * result + ((cdContrato == null) ? 0 : cdContrato.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((lancamentos == null) ? 0 : lancamentos.hashCode());
		result = prime * result + ((nameContrato == null) ? 0 : nameContrato.hashCode());
		result = prime * result + (spreadsheetAllPages ? 1231 : 1237);
		result = prime * result + ((useType == null) ? 0 : useType.hashCode());
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
		Contrato other = (Contrato) obj;
		if (arquivoInput == null) {
			if (other.arquivoInput != null)
				return false;
		} else if (!arquivoInput.equals(other.arquivoInput))
			return false;
		if (cdContrato == null) {
			if (other.cdContrato != null)
				return false;
		} else if (!cdContrato.equals(other.cdContrato))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (lancamentos == null) {
			if (other.lancamentos != null)
				return false;
		} else if (!lancamentos.equals(other.lancamentos))
			return false;
		if (nameContrato == null) {
			if (other.nameContrato != null)
				return false;
		} else if (!nameContrato.equals(other.nameContrato))
			return false;
		if (spreadsheetAllPages != other.spreadsheetAllPages)
			return false;
		if (useType != other.useType)
			return false;
		return true;
	}

}