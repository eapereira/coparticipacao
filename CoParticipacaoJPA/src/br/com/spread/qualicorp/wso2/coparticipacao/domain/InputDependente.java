package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_input_dependente database table.
 * 
 */
public abstract class InputDependente extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -284397717429140856L;
	
	private ArquivoInputColsDef arquivoInputColsDef;
	private DependenteColsDef dependenteColsDef;

	public InputDependente() {
	}

	public InputDependente(InputDependente entity) {
		super(entity);
	}

	public ArquivoInputColsDef getArquivoInputColsDef() {
		return this.arquivoInputColsDef;
	}

	public void setArquivoInputColsDef(ArquivoInputColsDef arquivoInputColsDef) {
		this.arquivoInputColsDef = arquivoInputColsDef;
	}

	public DependenteColsDef getDependenteColsDef() {
		return this.dependenteColsDef;
	}

	public void setDependenteColsDef(DependenteColsDef dependenteColsDef) {
		this.dependenteColsDef = dependenteColsDef;
	}

}