package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegisterColumn;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraFieldUi;

/**
 * The persistent class for the tb_regra_field database table.
 * 
 */
@Entity
@Table(name = "TB_REGRA_FIELD")
@NamedQuery(name = "RegraFieldEntity.findAll", query = "SELECT r FROM RegraFieldEntity r")
public class RegraFieldEntity extends RegraField implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7033764891952763358L;

	public RegraFieldEntity() {
	}

	public RegraFieldEntity(RegraFieldUi ui) {
		super(ui);
	}

	// bi-directional many-to-one association to RegraOperation
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegraOperationEntity.class)
	@JoinColumn(name = "ID_REGRA_OPERATION")
	public RegraOperation getRegraOperation() {
		return super.getRegraOperation();
	}

	// bi-directional many-to-one association to RegraOperation
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegisterColumnEntity.class)
	@JoinColumn(name = "ID_REGISTER_COLUMN")
	public RegisterColumn getRegisterColumn() {
		return super.getRegisterColumn();
	}

}