package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class ArquivoInputSheetColsDef extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7033729485824183816L;

	private Integer ordem;
	private ColDefType type;

	private String nameColumn;
	private Integer length;
	private String format;
	private String localePattern;
	private String restrictedValue;

	private ArquivoInputSheet arquivoInputSheet;

	public ArquivoInputSheetColsDef() {
		super();
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

	public String getNameColumn() {
		return nameColumn;
	}

	public void setNameColumn(String nameColumn) {
		this.nameColumn = nameColumn;
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

	public String getLocalePattern() {
		return localePattern;
	}

	public void setLocalePattern(String localePattern) {
		this.localePattern = localePattern;
	}

	public ArquivoInputSheet getArquivoInputSheet() {
		return arquivoInputSheet;
	}

	public void setArquivoInputSheet(ArquivoInputSheet arquivoInputSheet) {
		this.arquivoInputSheet = arquivoInputSheet;
	}

	public String getRestrictedValue() {
		return restrictedValue;
	}

	public void setRestrictedValue(String restrictedValue) {
		this.restrictedValue = restrictedValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((localePattern == null) ? 0 : localePattern.hashCode());
		result = prime * result + ((nameColumn == null) ? 0 : nameColumn.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result + ((restrictedValue == null) ? 0 : restrictedValue.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArquivoInputSheetColsDef other = (ArquivoInputSheetColsDef) obj;
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
		if (localePattern == null) {
			if (other.localePattern != null)
				return false;
		} else if (!localePattern.equals(other.localePattern))
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
		if (restrictedValue == null) {
			if (other.restrictedValue != null)
				return false;
		} else if (!restrictedValue.equals(other.restrictedValue))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
