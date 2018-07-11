package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_parameter database table.
 * 
 */
public abstract class Parameter extends AbstractDomain {
	private static final long serialVersionUID = 1L; 

	private String nameParameter;
	private String value;
	private User userCreated;
	private User userAltered;
	private Empresa empresa;

	public Parameter() {
	}

	public Parameter(Parameter entity) {
		super(entity);
	}

	public String getNameParameter() {
		return this.nameParameter;
	}

	public void setNameParameter(String nmParameter) {
		this.nameParameter = nmParameter;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String vlParameter) {
		this.value = vlParameter;
	}

	public User getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(User userCreated) {
		this.userCreated = userCreated;
	}

	public User getUserAltered() {
		return this.userAltered;
	}

	public void setUserAltered(User userAltered) {
		this.userAltered = userAltered;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}