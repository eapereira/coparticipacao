package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_arquivo_output_sheet_cols_def database table.
 * 
 */
public abstract class ArquivoOutputSheetColsDef extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private ViewDestination viewDestination;
	private ViewDestinationColsDef viewDestinationColsDef;

	public ArquivoOutputSheetColsDef() {
	}

	public ArquivoOutputSheetColsDef(ArquivoOutputSheetColsDef entity) {
		super(entity);
	}

	public ViewDestination getViewDestination() {
		return this.viewDestination;
	}

	public void setViewDestination(ViewDestination viewDestination) {
		this.viewDestination = viewDestination;
	}

	public ViewDestinationColsDef getViewDestinationColsDef() {
		return this.viewDestinationColsDef;
	}

	public void setViewDestinationColsDef(ViewDestinationColsDef viewDestinationColsDef) {
		this.viewDestinationColsDef = viewDestinationColsDef;
	}

}