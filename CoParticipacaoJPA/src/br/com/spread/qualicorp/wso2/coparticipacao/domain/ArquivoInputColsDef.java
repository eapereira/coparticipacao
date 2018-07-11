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

	private ArquivoInput arquivoInput;

	private List<InputDependente> inputDependentes;
	private List<InputLancamento> inputLancamentos;
	private List<InputTitular> inputTitulars;

	private List<RegraResult> regraResults;

	public ArquivoInputColsDef() {
		inputDependentes = new ArrayList<>();
		inputLancamentos = new ArrayList<>();
		inputTitulars = new ArrayList<>();
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

	public List<InputDependente> getInputDependentes() {
		return this.inputDependentes;
	}

	public void setInputDependentes(List<InputDependente> inputDependentes) {
		this.inputDependentes = inputDependentes;
	}

	public InputDependente addInputDependente(InputDependente inputDependente) {
		getInputDependentes().add(inputDependente);
		inputDependente.setArquivoInputColsDef(this);

		return inputDependente;
	}

	public InputDependente removeInputDependente(
			InputDependente inputDependente) {
		getInputDependentes().remove(inputDependente);
		inputDependente.setArquivoInputColsDef(null);

		return inputDependente;
	}

	public List<InputLancamento> getInputLancamentos() {
		return this.inputLancamentos;
	}

	public void setInputLancamentos(List<InputLancamento> inputLancamentos) {
		this.inputLancamentos = inputLancamentos;
	}

	public InputLancamento addInputLancamento(InputLancamento inputLancamento) {
		getInputLancamentos().add(inputLancamento);
		inputLancamento.setArquivoInputColsDef(this);

		return inputLancamento;
	}

	public InputLancamento removeInputLancamento(
			InputLancamento inputLancamento) {
		getInputLancamentos().remove(inputLancamento);
		inputLancamento.setArquivoInputColsDef(null);

		return inputLancamento;
	}

	public List<InputTitular> getInputTitulars() {
		return this.inputTitulars;
	}

	public void setInputTitulars(List<InputTitular> inputTitulars) {
		this.inputTitulars = inputTitulars;
	}

	public InputTitular addInputTitular(InputTitular inputTitular) {
		getInputTitulars().add(inputTitular);
		inputTitular.setArquivoInputColsDef(this);

		return inputTitular;
	}

	public InputTitular removeInputTitular(InputTitular inputTitular) {
		getInputTitulars().remove(inputTitular);
		inputTitular.setArquivoInputColsDef(null);

		return inputTitular;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arquivoInput == null) ? 0 : arquivoInput.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((inputDependentes == null) ? 0
				: inputDependentes.hashCode());
		result = prime * result + ((inputLancamentos == null) ? 0
				: inputLancamentos.hashCode());
		result = prime * result
				+ ((inputTitulars == null) ? 0 : inputTitulars.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result
				+ ((nameColumn == null) ? 0 : nameColumn.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result
				+ ((regraResults == null) ? 0 : regraResults.hashCode());
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
		if (inputDependentes == null) {
			if (other.inputDependentes != null)
				return false;
		} else if (!inputDependentes.equals(other.inputDependentes))
			return false;
		if (inputLancamentos == null) {
			if (other.inputLancamentos != null)
				return false;
		} else if (!inputLancamentos.equals(other.inputLancamentos))
			return false;
		if (inputTitulars == null) {
			if (other.inputTitulars != null)
				return false;
		} else if (!inputTitulars.equals(other.inputTitulars))
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