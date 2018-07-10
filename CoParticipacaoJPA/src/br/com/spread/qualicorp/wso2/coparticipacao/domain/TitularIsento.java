package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_titular_isento database table.
 * 
 */
public abstract class TitularIsento extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private Isento isento;
	private Titular titular;

	public TitularIsento() {
	}

	public TitularIsento(TitularIsento entity) {
		super(entity);
	}

	public Isento getIsento() {
		return this.isento;
	}

	public void setIsento(Isento isento) {
		this.isento = isento;
	}

	public Titular getTitular() {
		return this.titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

}