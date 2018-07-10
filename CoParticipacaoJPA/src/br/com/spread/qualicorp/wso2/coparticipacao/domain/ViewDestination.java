package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_view_destination database table.
 * 
 */
public abstract class ViewDestination extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private String nameView;
	private List<ArquivoOutputSheet> arquivoOutputSheets;
	private List<ArquivoOutputSheetColsDef> arquivoOutputSheetColsDefs;

	private List<ViewDestinationColsDef> viewDestinationColsDefs;

	public ViewDestination() {
		arquivoOutputSheetColsDefs=new ArrayList<>();
		arquivoOutputSheets=new ArrayList<>();
	}

	public ViewDestination(ViewDestination entity) {
		super(entity);
	}

	public String getNameView() {
		return this.nameView;
	}

	public void setNameView(String nmView) {
		this.nameView = nmView;
	}

	public List<ArquivoOutputSheet> getArquivoOutputSheets() {
		return this.arquivoOutputSheets;
	}

	public void setArquivoOutputSheets(List<ArquivoOutputSheet> arquivoOutputSheets) {
		this.arquivoOutputSheets = arquivoOutputSheets;
	}

	public ArquivoOutputSheet addArquivoOutputSheet(ArquivoOutputSheet arquivoOutputSheet) {
		getArquivoOutputSheets().add(arquivoOutputSheet);
		arquivoOutputSheet.setViewDestination(this);

		return arquivoOutputSheet;
	}

	public ArquivoOutputSheet removeArquivoOutputSheet(ArquivoOutputSheet arquivoOutputSheet) {
		getArquivoOutputSheets().remove(arquivoOutputSheet);
		arquivoOutputSheet.setViewDestination(null);

		return arquivoOutputSheet;
	}

	public List<ArquivoOutputSheetColsDef> getArquivoOutputSheetColsDefs() {
		return this.arquivoOutputSheetColsDefs;
	}

	public void setArquivoOutputSheetColsDefs(List<ArquivoOutputSheetColsDef> arquivoOutputSheetColsDefs) {
		this.arquivoOutputSheetColsDefs = arquivoOutputSheetColsDefs;
	}

	public ArquivoOutputSheetColsDef addArquivoOutputSheetColsDef(ArquivoOutputSheetColsDef arquivoOutputSheetColsDef) {
		getArquivoOutputSheetColsDefs().add(arquivoOutputSheetColsDef);
		arquivoOutputSheetColsDef.setViewDestination(this);

		return arquivoOutputSheetColsDef;
	}

	public ArquivoOutputSheetColsDef removeArquivoOutputSheetColsDef(
			ArquivoOutputSheetColsDef arquivoOutputSheetColsDef) {
		getArquivoOutputSheetColsDefs().remove(arquivoOutputSheetColsDef);
		arquivoOutputSheetColsDef.setViewDestination(null);

		return arquivoOutputSheetColsDef;
	}

	public List<ViewDestinationColsDef> getViewDestinationColsDefs() {
		return this.viewDestinationColsDefs;
	}

	public void setViewDestinationColsDefs(List<ViewDestinationColsDef> viewDestinationColsDefs) {
		this.viewDestinationColsDefs = viewDestinationColsDefs;
	}

	public ViewDestinationColsDef addViewDestinationColsDef(ViewDestinationColsDef viewDestinationColsDef) {
		getViewDestinationColsDefs().add(viewDestinationColsDef);
		viewDestinationColsDef.setViewDestination(this);

		return viewDestinationColsDef;
	}

	public ViewDestinationColsDef removeViewDestinationColsDef(ViewDestinationColsDef viewDestinationColsDef) {
		getViewDestinationColsDefs().remove(viewDestinationColsDef);
		viewDestinationColsDef.setViewDestination(null);

		return viewDestinationColsDef;
	}

}