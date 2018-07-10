package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalValor;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_REGRA_CONDITIONAL_VALOR")
@NamedQuery(
		name = "RegraConditionalValorEntity.findAll",
		query = "SELECT r FROM RegraConditionalValorEntity r")
public class RegraConditionalValorEntity extends RegraConditionalValor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6889430756040315813L;

	public RegraConditionalValorEntity() {
		super();
	}

	// bi-directional many-to-one association to RegraConditionalOperation
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = RegraConditionalOperationEntity.class)
	@JoinColumn(name = "ID_REGRA_CONDITIONAL_OPERATION")
	public RegraConditionalOperation getRegraConditionalOperation() {
		return super.getRegraConditionalOperation();
	}
	
	@Column(name = "VL_INT")
	@Override
	public Integer getIntValue() {
		// TODO Auto-generated method stub
		return super.getIntValue();
	}

	@Column(name = "VL_LONG")
	@Override
	public Long getLongValue() {
		// TODO Auto-generated method stub
		return super.getLongValue();
	}

	@Column(name = "VL_DOUBLE")
	@Override
	public BigDecimal getBigDecimalValue() {
		// TODO Auto-generated method stub
		return super.getBigDecimalValue();
	}

	@Column(name = "VL_DATE")
	@Override
	public LocalDate getDateValue() {
		// TODO Auto-generated method stub
		return super.getDateValue();
	}

	@Column(name = "VL_STRING")
	@Override
	public String getStringValue() {
		// TODO Auto-generated method stub
		return super.getStringValue();
	}	
}
