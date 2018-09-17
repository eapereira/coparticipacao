package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_titular_isento database table.
 * 
 */
public abstract class TitularIsento extends Isento {
	private static final long serialVersionUID = 1L;

	private Titular titular;

	public TitularIsento() {
	}

	public Titular getTitular() {
		return titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

}