package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class RegraResult extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5033612908963955438L;

	private Regra regra;

	private RegisterColumn registerColumn;

	public RegraResult() {

	}

	public Regra getRegra() {
		return regra;
	}

	public void setRegra(Regra regra) {
		this.regra = regra;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((registerColumn == null) ? 0 : registerColumn.hashCode());
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
		RegraResult other = (RegraResult) obj;
		if (registerColumn == null) {
			if (other.registerColumn != null)
				return false;
		} else if (!registerColumn.equals(other.registerColumn))
			return false;
		return true;
	}

	public RegisterColumn getRegisterColumn() {
		return registerColumn;
	}

	public void setRegisterColumn(RegisterColumn registerColumn) {
		this.registerColumn = registerColumn;
	}
}
