package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_regra_operation database table.
 * 
 */
public abstract class RegraOperation extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private Integer ordem;
	
	private Regra regra;
	private OperationType tpOperation;

	private List<RegraField> regraFields;

	private List<RegraValor> regraValors;

	public RegraOperation() {
		regraFields = new ArrayList<>();
		regraValors = new ArrayList<>();
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

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result + ((regra == null) ? 0 : regra.hashCode());
		result = prime * result + ((regraFields == null) ? 0 : regraFields.hashCode());
		result = prime * result + ((regraValors == null) ? 0 : regraValors.hashCode());
		result = prime * result + ((tpOperation == null) ? 0 : tpOperation.hashCode());
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
		RegraOperation other = (RegraOperation) obj;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		if (regra == null) {
			if (other.regra != null)
				return false;
		} else if (!regra.equals(other.regra))
			return false;
		if (regraFields == null) {
			if (other.regraFields != null)
				return false;
		} else if (!regraFields.equals(other.regraFields))
			return false;
		if (regraValors == null) {
			if (other.regraValors != null)
				return false;
		} else if (!regraValors.equals(other.regraValors))
			return false;
		if (tpOperation != other.tpOperation)
			return false;
		return true;
	}

}