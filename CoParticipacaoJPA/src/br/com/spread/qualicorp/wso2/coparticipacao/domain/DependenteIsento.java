package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_dependente_isento database table.
 * 
 */
public abstract class DependenteIsento extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private Dependente dependente;
	private Isento isento;

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

	public Isento getIsento() {
		return this.isento;
	}

	public void setIsento(Isento isento) {
		this.isento = isento;
	}

}