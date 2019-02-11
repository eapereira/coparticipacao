package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class RegisterColumn extends AbstractDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2400851351717127976L;
	private Integer ordem;
	private ColDefType type;

	private String nameColumn;
	private Integer length;
	private String format;
	private String localePattern;
	private String restrictedValue;

	private Register register;

	private List<BeneficiarioCols> beneficiarioCols;
	
	private String regexpValue;
	private Integer regexpGroupValue;
	
	public RegisterColumn() {
		super();
		
		beneficiarioCols=new ArrayList<>();
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public ColDefType getType() {
		return type;
	}

	public void setType(ColDefType type) {
		this.type = type;
	}

	public String getNameColumn() {
		return nameColumn;
	}

	public void setNameColumn(String nameColumn) {
		this.nameColumn = nameColumn;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLocalePattern() {
		return localePattern;
	}

	public void setLocalePattern(String localePattern) {
		this.localePattern = localePattern;
	}

	public String getRestrictedValue() {
		return restrictedValue;
	}

	public void setRestrictedValue(String restrictedValue) {
		this.restrictedValue = restrictedValue;
	}

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	public List<BeneficiarioCols> getBeneficiarioCols() {
		return beneficiarioCols;
	}

	public void setBeneficiarioCols(List<BeneficiarioCols> beneficiarioCols) {
		this.beneficiarioCols = beneficiarioCols;
	}

	public String getRegexpValue() {
		return regexpValue;
	}

	public void setRegexpValue(String regexpValue) {
		this.regexpValue = regexpValue;
	}

	public Integer getRegexpGroupValue() {
		return regexpGroupValue;
	}

	public void setRegexpGroupValue(Integer regexpGroupValue) {
		this.regexpGroupValue = regexpGroupValue;
	}
}
