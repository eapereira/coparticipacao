package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_dependente_cols_def database table.
 * 
 */
public abstract class DependenteColsDef extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private ColDefType cdType;

	private String nameColumn;
	private Integer length;

	private List<InputDependente> inputDependentes;

	public DependenteColsDef() {
		inputDependentes=new ArrayList<>();
	}

	public DependenteColsDef(DependenteColsDef entity) {
		super(entity);
	}

	public ColDefType getCdType() {
		return this.cdType;
	}

	public void setCdType(ColDefType cdType) {
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

	public void setLength(Integer length) {
		this.length = length;
	}

	public List<InputDependente> getInputDependentes() {
		return this.inputDependentes;
	}

	public void setInputDependentes(List<InputDependente> inputDependentes) {
		this.inputDependentes = inputDependentes;
	}

	public InputDependente addInputDependente(InputDependente inputDependente) {
		getInputDependentes().add(inputDependente);
		inputDependente.setDependenteColsDef(this);

		return inputDependente;
	}

	public InputDependente removeInputDependente(InputDependente inputDependente) {
		getInputDependentes().remove(inputDependente);
		inputDependente.setDependenteColsDef(null);

		return inputDependente;
	}

}