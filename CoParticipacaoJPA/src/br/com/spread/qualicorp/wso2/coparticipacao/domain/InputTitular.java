package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_input_titular database table.
 * 
 */
public abstract class InputTitular extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private ArquivoInputColsDef arquivoInputColsDef;

	private TitularColsDef titularColsDef;

	public InputTitular() {
	}

	public InputTitular(InputTitular entity) {
		super(entity);
	}

	public ArquivoInputColsDef getArquivoInputColsDef() {
		return this.arquivoInputColsDef;
	}

	public void setArquivoInputColsDef(ArquivoInputColsDef arquivoInputColsDef) {
		this.arquivoInputColsDef = arquivoInputColsDef;
	}

	public TitularColsDef getTitularColsDef() {
		return this.titularColsDef;
	}

	public void setTitularColsDef(TitularColsDef titularColsDef) {
		this.titularColsDef = titularColsDef;
	}

}