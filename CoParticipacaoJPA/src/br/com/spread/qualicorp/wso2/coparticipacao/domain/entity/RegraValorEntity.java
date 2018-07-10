package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraValor;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraValorUi;

/**
 * The persistent class for the tb_regra_valor database table.
 * 
 */
@Entity
@Table(name = "TB_REGRA_VALOR")
@NamedQuery(
		name = "RegraValorEntity.findAll",
		query = "SELECT r FROM RegraValorEntity r")
public class RegraValorEntity extends RegraValor implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -11198378234826917L;

	public RegraValorEntity() {
	}

	public RegraValorEntity(RegraValorUi ui) {
		super(ui);
	}

	@Column(name = "VL_REGRA_VALOR")
	public BigDecimal getValor() {
		return super.getValor();
	}

	// bi-directional many-to-one association to RegraOperation
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = RegraOperationEntity.class)
	@JoinColumn(name = "ID_REGRA_OPERATION")
	public RegraOperation getRegraOperation() {
		return super.getRegraOperation();
	}

}