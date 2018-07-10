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

	private List<Lancamento> lancamentos;

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

}