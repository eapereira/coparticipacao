package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_dependente_isento database table.
 * 
 */
public abstract class DependenteIsento extends Isento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8922972948062065308L;
	
	private Dependente dependente;

	public DependenteIsento() {
	}

	public Dependente getDependente() {
		return dependente;
	}

	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}

}