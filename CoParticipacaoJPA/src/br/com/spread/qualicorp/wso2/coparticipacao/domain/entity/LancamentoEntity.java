package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;

/**
 * The persistent class for the tb_lancamento database table.
 * 
 */
@Entity
@Table(name = "TB_LANCAMENTO")
@NamedQuery(name = "LancamentoEntity.findAll", query = "SELECT l FROM LancamentoEntity l")
public class LancamentoEntity extends Lancamento implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6558537394029218408L;

	public LancamentoEntity() {
	}

	public LancamentoEntity(LancamentoUi ui) {
		super(ui);
	}

	@Column(name = "CD_ANO")
	public Integer getAno() {
		return super.getAno();
	}

	@Column(name = "CD_MES")
	public Integer getMes() {
		return super.getMes();
	}

	// bi-directional many-to-one association to Contrato
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ContratoEntity.class)
	@JoinColumn(name = "ID_CONTRATO")
	public Contrato getContrato() {
		return super.getContrato();
	}

	// bi-directional many-to-one association to Dependente
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = DependenteEntity.class)
	@JoinColumn(name = "ID_DEPENDENTE")
	public Dependente getDependente() {
		return super.getDependente();
	}

	// bi-directional many-to-one association to Titular
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = TitularEntity.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_TITULAR")
	public Titular getTitular() {
		return super.getTitular();
	}

	@Column(name = "VL_PRINCIPAL")
	@Override
	public BigDecimal getValorPrincipal() {
		// TODO Auto-generated method stub
		return super.getValorPrincipal();
	}

	@Column(name = "VL_REEMBOLSO")
	@Override
	public BigDecimal getValorRembolso() {
		// TODO Auto-generated method stub
		return super.getValorRembolso();
	}

	@Column(name = "VL_PARTICIPACAO")
	@Override
	public BigDecimal getValorParticipacao() {
		// TODO Auto-generated method stub
		return super.getValorParticipacao();
	}

	@Column(name = "DT_UTILIZACAO")
	@Override
	public LocalDate getDtUtilizacao() {
		// TODO Auto-generated method stub
		return super.getDtUtilizacao();
	}

}