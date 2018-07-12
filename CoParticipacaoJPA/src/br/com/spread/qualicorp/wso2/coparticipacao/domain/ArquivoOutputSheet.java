package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_arquivo_output_sheet database table.
 * 
 */
public abstract class ArquivoOutputSheet extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private String nmSheet;
	private ArquivoOutput arquivoOutput;

	private ViewDestination viewDestination;

	private List<ArquivoOutputSheetColsDef> arquivoOutputSheetColsDefs;

	public ArquivoOutputSheet() {
		arquivoOutputSheetColsDefs = new ArrayList<>();
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

	public List<ArquivoOutputSheetColsDef> getArquivoOutputSheetColsDefs() {
		return arquivoOutputSheetColsDefs;
	}

	public void setArquivoOutputSheetColsDefs(
			List<ArquivoOutputSheetColsDef> arquivoOutputSheetColsDefs) {
		this.arquivoOutputSheetColsDefs = arquivoOutputSheetColsDefs;
	}

	public void addArquivoOutputSheetColsDef(
			ArquivoOutputSheetColsDef arquivoOutputSheetColsDef) {
		getArquivoOutputSheetColsDefs().add(arquivoOutputSheetColsDef);
		arquivoOutputSheetColsDef.setArquivoOutputSheet(this);
	}

	public void removeArquivoOutputSheetColsDef(
			ArquivoOutputSheetColsDef arquivoOutputSheetColsDef) {
		getArquivoOutputSheetColsDefs().remove(arquivoOutputSheetColsDef);
		arquivoOutputSheetColsDef.setArquivoOutputSheet(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arquivoOutput == null) ? 0 : arquivoOutput.hashCode());
		result = prime * result + ((arquivoOutputSheetColsDefs == null) ? 0
				: arquivoOutputSheetColsDefs.hashCode());
		result = prime * result + ((nmSheet == null) ? 0 : nmSheet.hashCode());
		result = prime * result
				+ ((viewDestination == null) ? 0 : viewDestination.hashCode());
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
		ArquivoOutputSheet other = (ArquivoOutputSheet) obj;
		if (arquivoOutput == null) {
			if (other.arquivoOutput != null)
				return false;
		} else if (!arquivoOutput.equals(other.arquivoOutput))
			return false;
		if (arquivoOutputSheetColsDefs == null) {
			if (other.arquivoOutputSheetColsDefs != null)
				return false;
		} else if (!arquivoOutputSheetColsDefs
				.equals(other.arquivoOutputSheetColsDefs))
			return false;
		if (nmSheet == null) {
			if (other.nmSheet != null)
				return false;
		} else if (!nmSheet.equals(other.nmSheet))
			return false;
		if (viewDestination == null) {
			if (other.viewDestination != null)
				return false;
		} else if (!viewDestination.equals(other.viewDestination))
			return false;
		return true;
	}
}