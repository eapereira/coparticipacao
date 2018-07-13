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
	
	private String titleLabel;
	
	private List<ArquivoOutputSheet> arquivoOutputSheets;

	private List<ViewDestinationColsDef> viewDestinationColsDefs;

	public ViewDestination() {
		arquivoOutputSheets = new ArrayList<>();
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

	public void setArquivoOutputSheets(
			List<ArquivoOutputSheet> arquivoOutputSheets) {
		this.arquivoOutputSheets = arquivoOutputSheets;
	}

	public ArquivoOutputSheet addArquivoOutputSheet(
			ArquivoOutputSheet arquivoOutputSheet) {
		getArquivoOutputSheets().add(arquivoOutputSheet);
		arquivoOutputSheet.setViewDestination(this);

		return arquivoOutputSheet;
	}

	public ArquivoOutputSheet removeArquivoOutputSheet(
			ArquivoOutputSheet arquivoOutputSheet) {
		getArquivoOutputSheets().remove(arquivoOutputSheet);
		arquivoOutputSheet.setViewDestination(null);

		return arquivoOutputSheet;
	}

	public List<ViewDestinationColsDef> getViewDestinationColsDefs() {
		return this.viewDestinationColsDefs;
	}

	public void setViewDestinationColsDefs(
			List<ViewDestinationColsDef> viewDestinationColsDefs) {
		this.viewDestinationColsDefs = viewDestinationColsDefs;
	}

	public ViewDestinationColsDef addViewDestinationColsDef(
			ViewDestinationColsDef viewDestinationColsDef) {
		getViewDestinationColsDefs().add(viewDestinationColsDef);
		viewDestinationColsDef.setViewDestination(this);

		return viewDestinationColsDef;
	}

	public ViewDestinationColsDef removeViewDestinationColsDef(
			ViewDestinationColsDef viewDestinationColsDef) {
		getViewDestinationColsDefs().remove(viewDestinationColsDef);
		viewDestinationColsDef.setViewDestination(null);

		return viewDestinationColsDef;
	}
	
	public String getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoOutputSheets == null) ? 0
				: arquivoOutputSheets.hashCode());
		result = prime * result
				+ ((nameView == null) ? 0 : nameView.hashCode());
		result = prime * result
				+ ((titleLabel == null) ? 0 : titleLabel.hashCode());
		result = prime * result + ((viewDestinationColsDefs == null) ? 0
				: viewDestinationColsDefs.hashCode());
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
		ViewDestination other = (ViewDestination) obj;
		if (arquivoOutputSheets == null) {
			if (other.arquivoOutputSheets != null)
				return false;
		} else if (!arquivoOutputSheets.equals(other.arquivoOutputSheets))
			return false;
		if (nameView == null) {
			if (other.nameView != null)
				return false;
		} else if (!nameView.equals(other.nameView))
			return false;
		if (titleLabel == null) {
			if (other.titleLabel != null)
				return false;
		} else if (!titleLabel.equals(other.titleLabel))
			return false;
		if (viewDestinationColsDefs == null) {
			if (other.viewDestinationColsDefs != null)
				return false;
		} else if (!viewDestinationColsDefs
				.equals(other.viewDestinationColsDefs))
			return false;
		return true;
	}

}