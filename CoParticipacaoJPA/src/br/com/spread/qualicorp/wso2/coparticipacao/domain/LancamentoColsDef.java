package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_lancamento_cols_def database table.
 * 
 */
public abstract class LancamentoColsDef extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private ColDefType type;

	private String nameColumn;
	private Integer length;
	private List<InputLancamento> inputLancamentos;

	public LancamentoColsDef() {
		inputLancamentos=new ArrayList<>();
	}

	public LancamentoColsDef(LancamentoColsDef entity) {
		super(entity);
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

	public List<InputLancamento> getInputLancamentos() {
		return this.inputLancamentos;
	}

	public void setInputLancamentos(List<InputLancamento> inputLancamentos) {
		this.inputLancamentos = inputLancamentos;
	}

	public InputLancamento addInputLancamento(InputLancamento inputLancamento) {
		getInputLancamentos().add(inputLancamento);
		inputLancamento.setLancamentoColsDef(this);

		return inputLancamento;
	}

	public InputLancamento removeInputLancamento(InputLancamento inputLancamento) {
		getInputLancamentos().remove(inputLancamento);
		inputLancamento.setLancamentoColsDef(null);

		return inputLancamento;
	}

}