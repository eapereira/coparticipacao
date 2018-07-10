package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class RegraConditionalValor extends AbstractDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6427585225896455720L;

	private Integer intValue;

	private Long longValue;

	private BigDecimal bigDecimalValue;

	private String stringValue;
	
	private LocalDate dateValue;

	private RegraConditionalOperation regraConditionalOperation;

	public RegraConditionalValor() {

	}

	public Integer getIntValue() {
		return intValue;
	}

	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public BigDecimal getBigDecimalValue() {
		return bigDecimalValue;
	}

	public void setBigDecimalValue(BigDecimal bigDecimalValue) {
		this.bigDecimalValue = bigDecimalValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public LocalDate getDateValue() {
		return dateValue;
	}

	public void setDateValue(LocalDate dateValue) {
		this.dateValue = dateValue;
	}

	public RegraConditionalOperation getRegraConditionalOperation() {
		return regraConditionalOperation;
	}

	public void setRegraConditionalOperation(
			RegraConditionalOperation regraConditionalOperation) {
		this.regraConditionalOperation = regraConditionalOperation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bigDecimalValue == null) ? 0 : bigDecimalValue.hashCode());
		result = prime * result
				+ ((dateValue == null) ? 0 : dateValue.hashCode());
		result = prime * result
				+ ((intValue == null) ? 0 : intValue.hashCode());
		result = prime * result
				+ ((longValue == null) ? 0 : longValue.hashCode());
		result = prime * result + ((regraConditionalOperation == null) ? 0
				: regraConditionalOperation.hashCode());
		result = prime * result
				+ ((stringValue == null) ? 0 : stringValue.hashCode());
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
		RegraConditionalValor other = (RegraConditionalValor) obj;
		if (bigDecimalValue == null) {
			if (other.bigDecimalValue != null)
				return false;
		} else if (!bigDecimalValue.equals(other.bigDecimalValue))
			return false;
		if (dateValue == null) {
			if (other.dateValue != null)
				return false;
		} else if (!dateValue.equals(other.dateValue))
			return false;
		if (intValue == null) {
			if (other.intValue != null)
				return false;
		} else if (!intValue.equals(other.intValue))
			return false;
		if (longValue == null) {
			if (other.longValue != null)
				return false;
		} else if (!longValue.equals(other.longValue))
			return false;
		if (regraConditionalOperation == null) {
			if (other.regraConditionalOperation != null)
				return false;
		} else if (!regraConditionalOperation
				.equals(other.regraConditionalOperation))
			return false;
		if (stringValue == null) {
			if (other.stringValue != null)
				return false;
		} else if (!stringValue.equals(other.stringValue))
			return false;
		return true;
	}

}
