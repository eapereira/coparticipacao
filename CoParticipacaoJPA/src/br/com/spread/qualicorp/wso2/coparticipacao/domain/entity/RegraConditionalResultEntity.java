package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Regra;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditional;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalResult;

@Entity
@Table(name = "TB_REGRA_CONDITIONAL_RESULT")
@NamedQuery(name = "RegraConditionalResultEntity.findAll", query = "SELECT r FROM RegraConditionalResultEntity r")
public class RegraConditionalResultEntity extends RegraConditionalResult implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1895262297195141833L;

	public RegraConditionalResultEntity() {
		// TODO Auto-generated constructor stub
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegraConditionalEntity.class)
	@JoinColumn(name = "ID_REGRA_CONDITIONAL")
	@Override
	public RegraConditional getRegraConditional() {
		return super.getRegraConditional();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegraEntity.class)
	@JoinColumn(name = "ID_REGRA_EXECUTION")
	@Override
	public Regra getRegraExecution() {
		return super.getRegraExecution();
	}
}
