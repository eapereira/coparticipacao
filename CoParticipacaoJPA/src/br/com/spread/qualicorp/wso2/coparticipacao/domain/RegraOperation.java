package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_regra_operation database table.
 * 
 */
public abstract class RegraOperation extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private Regra regra;
	private OperationType tpOperation;

	private List<RegraField> regraFields;

	private List<RegraValor> regraValors;

	public RegraOperation() {
		regraFields=new ArrayList<>();
		regraValors=new ArrayList<>();
	}

	public RegraOperation(RegraOperation entity) {
		super(entity);
	}

	public OperationType getTpOperation() {
		return this.tpOperation;
	}

	public void setTpOperation(OperationType tpOperation) {
		this.tpOperation = tpOperation;
	}

	public List<RegraField> getRegraFields() {
		return this.regraFields;
	}

	public void setRegraFields(List<RegraField> regraFields) {
		this.regraFields = regraFields;
	}

	public RegraField addRegraField(RegraField regraField) {
		getRegraFields().add(regraField);
		regraField.setRegraOperation(this);

		return regraField;
	}

	public RegraField removeRegraField(RegraField regraField) {
		getRegraFields().remove(regraField);
		regraField.setRegraOperation(null);

		return regraField;
	}

	public Regra getRegra() {
		return regra;
	}

	public void setRegra(Regra regra) {
		this.regra = regra;
	}

	public List<RegraValor> getRegraValors() {
		return regraValors;
	}

	public void setRegraValors(List<RegraValor> regraValors) {
		this.regraValors = regraValors;
	}

	public RegraValor addRegraValor(RegraValor regraValor) {
		getRegraValors().add(regraValor);
		regraValor.setRegraOperation(this);

		return regraValor;
	}

	public RegraValor removeRegraValor(RegraValor regraValor) {
		getRegraValors().remove(regraValor);
		regraValor.setRegraOperation(null);

		return regraValor;
	}

}