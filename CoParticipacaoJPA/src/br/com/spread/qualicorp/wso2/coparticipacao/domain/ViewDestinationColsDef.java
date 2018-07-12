package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_view_destination_cols_def database table.
 * 
 */
public abstract class ViewDestinationColsDef extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private Integer ordem;
	private ColDefType type;

	private String nameColumn;
	private Integer length;
	private String format;
	private String columnTitleLabel;

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

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public ColDefType getType() {
		return type;
	}

	public void setType(ColDefType type) {
		this.type = type;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getColumnTitleLabel() {
		return columnTitleLabel;
	}

	public void setColumnTitleLabel(String columnTitleLabel) {
		this.columnTitleLabel = columnTitleLabel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoOutputSheetColsDefs == null) ? 0
				: arquivoOutputSheetColsDefs.hashCode());
		result = prime * result + ((columnTitleLabel == null) ? 0
				: columnTitleLabel.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result
				+ ((nameColumn == null) ? 0 : nameColumn.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ViewDestinationColsDef other = (ViewDestinationColsDef) obj;
		if (arquivoOutputSheetColsDefs == null) {
			if (other.arquivoOutputSheetColsDefs != null)
				return false;
		} else if (!arquivoOutputSheetColsDefs
				.equals(other.arquivoOutputSheetColsDefs))
			return false;
		if (columnTitleLabel == null) {
			if (other.columnTitleLabel != null)
				return false;
		} else if (!columnTitleLabel.equals(other.columnTitleLabel))
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (nameColumn == null) {
			if (other.nameColumn != null)
				return false;
		} else if (!nameColumn.equals(other.nameColumn))
			return false;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		if (type != other.type)
			return false;
		if (viewDestination == null) {
			if (other.viewDestination != null)
				return false;
		} else if (!viewDestination.equals(other.viewDestination))
			return false;
		return true;
	}

}