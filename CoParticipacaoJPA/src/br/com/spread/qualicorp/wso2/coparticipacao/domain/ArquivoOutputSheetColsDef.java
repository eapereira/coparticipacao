package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_arquivo_output_sheet_cols_def database table.
 * 
 */
public abstract class ArquivoOutputSheetColsDef extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private ArquivoOutputSheet arquivoOutputSheet;

	private ViewDestinationColsDef viewDestinationColsDef;

	public ArquivoOutputSheetColsDef() {
	}

	public ArquivoOutputSheetColsDef(ArquivoOutputSheetColsDef entity) {
		super(entity);
	}

	public ViewDestinationColsDef getViewDestinationColsDef() {
		return this.viewDestinationColsDef;
	}

	public void setViewDestinationColsDef(
			ViewDestinationColsDef viewDestinationColsDef) {
		this.viewDestinationColsDef = viewDestinationColsDef;
	}

	public ArquivoOutputSheet getArquivoOutputSheet() {
		return arquivoOutputSheet;
	}

	public void setArquivoOutputSheet(ArquivoOutputSheet arquivoOutputSheet) {
		this.arquivoOutputSheet = arquivoOutputSheet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoOutputSheet == null) ? 0
				: arquivoOutputSheet.hashCode());
		result = prime * result + ((viewDestinationColsDef == null) ? 0
				: viewDestinationColsDef.hashCode());
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
		ArquivoOutputSheetColsDef other = (ArquivoOutputSheetColsDef) obj;
		if (arquivoOutputSheet == null) {
			if (other.arquivoOutputSheet != null)
				return false;
		} else if (!arquivoOutputSheet.equals(other.arquivoOutputSheet))
			return false;
		if (viewDestinationColsDef == null) {
			if (other.viewDestinationColsDef != null)
				return false;
		} else if (!viewDestinationColsDef.equals(other.viewDestinationColsDef))
			return false;
		return true;
	}

}