package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.OperationType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Regra;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraValor;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.OperationTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraOperationUi;

/**
 * The persistent class for the tb_regra_operation database table.
 * 
 */
@Entity
@Table(name = "TB_REGRA_OPERATION")
@NamedQuery(
		name = "RegraOperationEntity.findAll",
		query = "SELECT r FROM RegraOperationEntity r")
public class RegraOperationEntity extends RegraOperation
		implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6347949058272781245L;

	public RegraOperationEntity() {
	}

	public RegraOperationEntity(RegraOperationUi ui) {
		super(ui);
	}

	@Convert(converter = OperationTypeConverter.class)
	@Column(name = "TP_OPERATION")
	public OperationType getTpOperation() {
		return super.getTpOperation();
	}

	// bi-directional many-to-one association to RegraField
	@OneToMany(
			mappedBy = "regraOperation",
			targetEntity = RegraFieldEntity.class)
	@OrderColumn(name="INDEX")
	public List<RegraField> getRegraFields() {
		return super.getRegraFields();
	}

	// bi-directional many-to-one association to RegraOperation
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegraEntity.class)
	@JoinColumn(name = "ID_REGRA")
	public Regra getRegra() {
		return super.getRegra();
	}

	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "regraOperation",
			targetEntity = RegraValorEntity.class)
	@OrderColumn(name="INDEX")
	public List<RegraValor> getRegraValors() {
		return super.getRegraValors();
	}

}