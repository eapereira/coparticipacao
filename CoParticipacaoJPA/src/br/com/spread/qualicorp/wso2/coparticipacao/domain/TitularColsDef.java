package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_titular_cols_def database table.
 * 
 */
public abstract class TitularColsDef extends AbstractDomain {
	private static final long serialVersionUID = 1L;
	private ColDefType type;

	private String nameColumn;
	private int length;
	private List<InputTitular> inputTitulars;

	public TitularColsDef() {
		inputTitulars=new ArrayList<>();
	}

	public TitularColsDef(TitularColsDef entity) {
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

	public int getLength() {
		return this.length;
	}

	public void setLength(int vlLength) {
		this.length = vlLength;
	}

	public List<InputTitular> getInputTitulars() {
		return this.inputTitulars;
	}

	public void setInputTitulars(List<InputTitular> inputTitulars) {
		this.inputTitulars = inputTitulars;
	}

	public InputTitular addInputTitular(InputTitular inputTitular) {
		getInputTitulars().add(inputTitular);
		inputTitular.setTitularColsDef(this);

		return inputTitular;
	}

	public InputTitular removeInputTitular(InputTitular inputTitular) {
		getInputTitulars().remove(inputTitular);
		inputTitular.setTitularColsDef(null);

		return inputTitular;
	}

}