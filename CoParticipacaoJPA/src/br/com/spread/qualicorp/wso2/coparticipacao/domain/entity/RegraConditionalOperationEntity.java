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
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.OperationType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditional;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalValor;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.OperationTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_REGRA_CONDITIONAL_OPERATION")
@NamedQuery(name = "RegraConditionalOperationEntity.findAll", query = "SELECT r FROM RegraConditionalOperationEntity r")
public class RegraConditionalOperationEntity extends RegraConditionalOperation implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1818813591914732987L;

	public RegraConditionalOperationEntity() {
		super();
	}

	@Convert(converter = OperationTypeConverter.class)
	@Column(name = "TP_OPERATION")
	public OperationType getTpOperation() {
		return super.getTpOperation();
	}

	// bi-directional many-to-one association to RegraConditionalField
	@OneToMany(mappedBy = "regraConditionalOperation", targetEntity = RegraConditionalFieldEntity.class)
	public List<RegraConditionalField> getRegraConditionalFields() {
		return super.getRegraConditionalFields();
	}

	// bi-directional many-to-one association to RegraConditionalOperation
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegraConditionalEntity.class)
	@JoinColumn(name = "ID_REGRA_CONDITIONAL")
	public RegraConditional getRegraConditional() {
		return super.getRegraConditional();
	}

	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "regraConditionalOperation",
			targetEntity = RegraConditionalValorEntity.class)
	public List<RegraConditionalValor> getRegraConditionalValors() {
		return super.getRegraConditionalValors();
	}
}
