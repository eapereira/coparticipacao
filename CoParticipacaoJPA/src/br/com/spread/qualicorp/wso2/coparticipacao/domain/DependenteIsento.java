package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_dependente_isento database table.
 * 
 */
public abstract class DependenteIsento extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private Dependente dependente;
	private IsentoType isentoType;

	public DependenteIsento() {
	}

	public DependenteIsento(DependenteIsento entity) {
		super(entity);
	}

	public Dependente getDependente() {
		return this.dependente;
	}

	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}

	public IsentoType getIsentoType() {
		return isentoType;
	}

	public void setIsentoType(IsentoType isentoType) {
		this.isentoType = isentoType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dependente == null) ? 0 : dependente.hashCode());
		result = prime * result
				+ ((isentoType == null) ? 0 : isentoType.hashCode());
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
		DependenteIsento other = (DependenteIsento) obj;
		if (dependente == null) {
			if (other.dependente != null)
				return false;
		} else if (!dependente.equals(other.dependente))
			return false;
		if (isentoType != other.isentoType)
			return false;
		return true;
	}


}