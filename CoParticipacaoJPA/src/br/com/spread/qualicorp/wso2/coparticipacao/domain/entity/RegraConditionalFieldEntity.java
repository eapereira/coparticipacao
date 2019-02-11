package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegisterColumn;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalOperation;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_REGRA_CONDITIONAL_FIELD")
@NamedQuery(name = "RegraConditionalFieldEntity.findAll", query = "SELECT r FROM RegraConditionalFieldEntity r")
public class RegraConditionalFieldEntity extends RegraConditionalField implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4802314184854893574L;

	public RegraConditionalFieldEntity() {
		super();
	}

	// bi-directional many-to-one association to RegraConditionalOperation
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegraConditionalOperationEntity.class)
	@JoinColumn(name = "ID_REGRA_CONDITIONAL_OPERATION")
	public RegraConditionalOperation getRegraConditionalOperation() {
		return super.getRegraConditionalOperation();
	}

	// bi-directional many-to-one association to RegraConditionalOperation
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegisterColumnEntity.class)
	@JoinColumn(name = "ID_REGISTER_COLUMN")
	public RegisterColumn getRegisterColumn() {
		return super.getRegisterColumn();
	}
}
