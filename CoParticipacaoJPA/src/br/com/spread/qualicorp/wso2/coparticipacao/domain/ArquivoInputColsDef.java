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
	private ColDefType cdType;

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
		return this.cdType;
	}

	public void setType(ColDefType cdType) {
		this.cdType = cdType;
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

	
}