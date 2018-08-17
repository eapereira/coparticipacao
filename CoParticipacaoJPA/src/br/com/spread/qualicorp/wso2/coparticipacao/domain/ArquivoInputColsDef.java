package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_arquivo_input_cols_def database table.
 * 
 */
public abstract class ArquivoInputColsDef extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private Integer ordem;
	private ColDefType type;

	private String nameColumn;
	private Integer length;
	private String format;
	private String localePattern;

	private ArquivoInput arquivoInput;

	private LancamentoInputCols lancamentoInputCols;

	private List<RegraResult> regraResults;

	public ArquivoInputColsDef() {
		regraResults = new ArrayList<>();
	}

	public ArquivoInputColsDef(ArquivoInputColsDef entity) {
		super(entity);
	}

	public Integer getOrdem() {
		return this.ordem;
	}

	public void setOrdem(Integer cdOrdem) {
		this.ordem = cdOrdem;
	}

	public ColDefType getType() {
		return this.type;
	}

	public void setType(ColDefType cdType) {
		this.type = cdType;
	}

	public String getNameColumn() {
		return this.nameColumn;
	}

	public void setNameColumn(String nmColumn) {
		this.nameColumn = nmColumn;
	}

	public Integer getLength() {
		return this.length;
	}

	public void setLength(Integer vlLength) {
		this.length = vlLength;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public ArquivoInput getArquivoInput() {
		return this.arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public List<RegraResult> getRegraResults() {
		return regraResults;
	}

	public void setRegraResults(List<RegraResult> regraResults) {
		this.regraResults = regraResults;
	}

	public RegraResult addRegraResult(RegraResult RegraResult) {
		getRegraResults().add(RegraResult);
		RegraResult.setArquivoInputColsDef(this);

		return RegraResult;
	}

	public RegraResult removeRegraResult(RegraResult RegraResult) {
		getRegraResults().remove(RegraResult);
		RegraResult.setArquivoInputColsDef(null);

		return RegraResult;
	}

	public String getLocalePattern() {
		return localePattern;
	}

	public void setLocalePattern(String localePattern) {
		this.localePattern = localePattern;
	}

	public LancamentoInputCols getLancamentoInputCols() {
		return lancamentoInputCols;
	}

	public void setLancamentoInputCols(LancamentoInputCols lancamentoInputCols) {
		this.lancamentoInputCols = lancamentoInputCols;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInput == null) ? 0 : arquivoInput.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((lancamentoInputCols == null) ? 0 : lancamentoInputCols.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((localePattern == null) ? 0 : localePattern.hashCode());
		result = prime * result + ((nameColumn == null) ? 0 : nameColumn.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result + ((regraResults == null) ? 0 : regraResults.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ArquivoInputColsDef other = (ArquivoInputColsDef) obj;
		if (arquivoInput == null) {
			if (other.arquivoInput != null)
				return false;
		} else if (!arquivoInput.equals(other.arquivoInput))
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (lancamentoInputCols == null) {
			if (other.lancamentoInputCols != null)
				return false;
		} else if (!lancamentoInputCols.equals(other.lancamentoInputCols))
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
		if (regraResults == null) {
			if (other.regraResults != null)
				return false;
		} else if (!regraResults.equals(other.regraResults))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}