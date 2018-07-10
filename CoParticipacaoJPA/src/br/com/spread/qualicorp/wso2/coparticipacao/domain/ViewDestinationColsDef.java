package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_view_destination_cols_def database table.
 * 
 */
public abstract class ViewDestinationColsDef extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private String nameColumn;
	private List<ArquivoOutputSheetColsDef> arquivoOutputSheetColsDefs;

	private ViewDestination viewDestination;

	public ViewDestinationColsDef() {
		arquivoOutputSheetColsDefs=new ArrayList<>();
	}

	public ViewDestinationColsDef(ViewDestinationColsDef entity) {
		super(entity);
	}

	public String getNameColumn() {
		return this.nameColumn;
	}

	public void setNameColumn(String nmColumn) {
		this.nameColumn = nmColumn;
	}

	public List<ArquivoOutputSheetColsDef> getArquivoOutputSheetColsDefs() {
		return this.arquivoOutputSheetColsDefs;
	}

	public void setArquivoOutputSheetColsDefs(List<ArquivoOutputSheetColsDef> arquivoOutputSheetColsDefs) {
		this.arquivoOutputSheetColsDefs = arquivoOutputSheetColsDefs;
	}

	public ArquivoOutputSheetColsDef addArquivoOutputSheetColsDef(ArquivoOutputSheetColsDef arquivoOutputSheetColsDef) {
		getArquivoOutputSheetColsDefs().add(arquivoOutputSheetColsDef);
		arquivoOutputSheetColsDef.setViewDestinationColsDef(this);

		return arquivoOutputSheetColsDef;
	}

	public ArquivoOutputSheetColsDef removeArquivoOutputSheetColsDef(
			ArquivoOutputSheetColsDef arquivoOutputSheetColsDef) {
		getArquivoOutputSheetColsDefs().remove(arquivoOutputSheetColsDef);
		arquivoOutputSheetColsDef.setViewDestinationColsDef(null);

		return arquivoOutputSheetColsDef;
	}

	public ViewDestination getViewDestination() {
		return this.viewDestination;
	}

	public void setViewDestination(ViewDestination viewDestination) {
		this.viewDestination = viewDestination;
	}

}