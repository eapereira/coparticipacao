package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegisterColumnEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@MappedSuperclass
public abstract class Detail extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8625250104592728626L;

	private RegisterColumn registerColumn;

	private Integer intValue;

	private Long longValue;

	private BigDecimal bigDecimalValue;

	private LocalDate dateValue;

	private String stringValue;	

	public Detail() {
		super();
	}

	@Column(name = "VL_INT")
	public Integer getIntValue() {
		return intValue;
	}

	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}

	@Column(name = "VL_LONG")
	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	@Column(name = "VL_DOUBLE")
	public BigDecimal getBigDecimalValue() {
		return bigDecimalValue;
	}

	public void setBigDecimalValue(BigDecimal bigDecimalValue) {
		this.bigDecimalValue = bigDecimalValue;
	}

	@Column(name = "VL_DATE")
	public LocalDate getDateValue() {
		return dateValue;
	}

	public void setDateValue(LocalDate dateValue) {
		this.dateValue = dateValue;
	}

	@Column(name = "VL_STRING")
	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bigDecimalValue == null) ? 0 : bigDecimalValue.hashCode());
		result = prime * result + ((dateValue == null) ? 0 : dateValue.hashCode());
		result = prime * result + ((intValue == null) ? 0 : intValue.hashCode());
		result = prime * result + ((longValue == null) ? 0 : longValue.hashCode());
		result = prime * result + ((registerColumn == null) ? 0 : registerColumn.hashCode());
		result = prime * result + ((stringValue == null) ? 0 : stringValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Detail other = (Detail) obj;
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
		if (registerColumn == null) {
			if (other.registerColumn != null)
				return false;
		} else if (!registerColumn.equals(other.registerColumn))
			return false;
		if (stringValue == null) {
			if (other.stringValue != null)
				return false;
		} else if (!stringValue.equals(other.stringValue))
			return false;
		return true;
	}

	// bi-directional many-to-one association to ViewDestination
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegisterColumnEntity.class)
	@JoinColumn(name = "ID_REGISTER_COLUMN")
	public RegisterColumn getRegisterColumn() {
		return registerColumn;
	}

	public void setRegisterColumn(RegisterColumn registerColumn) {
		this.registerColumn = registerColumn;
	}
}
