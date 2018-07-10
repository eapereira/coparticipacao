package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_arquivo_output_sheet database table.
 * 
 */
public abstract class ArquivoOutputSheet extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private String nmSheet;
	private ArquivoOutput arquivoOutput;

	private ViewDestination viewDestination;

	public ArquivoOutputSheet() {
	}

	public ArquivoOutputSheet(ArquivoOutputSheet entity) {
		super(entity);
	}

	public String getNmSheet() {
		return this.nmSheet;
	}

	public void setNmSheet(String nmSheet) {
		this.nmSheet = nmSheet;
	}

	public ArquivoOutput getArquivoOutput() {
		return this.arquivoOutput;
	}

	public void setArquivoOutput(ArquivoOutput arquivoOutput) {
		this.arquivoOutput = arquivoOutput;
	}

	public ViewDestination getViewDestination() {
		return this.viewDestination;
	}

	public void setViewDestination(ViewDestination viewDestination) {
		this.viewDestination = viewDestination;
	}

}