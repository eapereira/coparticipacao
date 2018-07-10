package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class RegraConditionalOperation extends AbstractDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7177325338389318350L;

	private RegraConditional regraConditional;
	
	private OperationType tpOperation;

	private List<RegraConditionalField> regraConditionalFields;

	private List<RegraConditionalValor> regraConditionalValors;
	
	public RegraConditionalOperation(){
		
	}

	public RegraConditional getRegraConditional() {
		return regraConditional;
	}

	public void setRegraConditional(RegraConditional regraConditional) {
		this.regraConditional = regraConditional;
	}

	public OperationType getTpOperation() {
		return tpOperation;
	}

	public void setTpOperation(OperationType tpOperation) {
		this.tpOperation = tpOperation;
	}

	public List<RegraConditionalField> getRegraConditionalFields() {
		return regraConditionalFields;
	}

	public void setRegraConditionalFields(
			List<RegraConditionalField> regraConditionalFields) {
		this.regraConditionalFields = regraConditionalFields;
	}

	public List<RegraConditionalValor> getRegraConditionalValors() {
		return regraConditionalValors;
	}

	public void setRegraConditionalValors(
			List<RegraConditionalValor> regraConditionalValors) {
		this.regraConditionalValors = regraConditionalValors;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((regraConditional == null) ? 0
				: regraConditional.hashCode());
		result = prime * result + ((regraConditionalFields == null) ? 0
				: regraConditionalFields.hashCode());
		result = prime * result + ((regraConditionalValors == null) ? 0
				: regraConditionalValors.hashCode());
		result = prime * result
				+ ((tpOperation == null) ? 0 : tpOperation.hashCode());
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
		RegraConditionalOperation other = (RegraConditionalOperation) obj;
		if (regraConditional == null) {
			if (other.regraConditional != null)
				return false;
		} else if (!regraConditional.equals(other.regraConditional))
			return false;
		if (regraConditionalFields == null) {
			if (other.regraConditionalFields != null)
				return false;
		} else if (!regraConditionalFields.equals(other.regraConditionalFields))
			return false;
		if (regraConditionalValors == null) {
			if (other.regraConditionalValors != null)
				return false;
		} else if (!regraConditionalValors.equals(other.regraConditionalValors))
			return false;
		if (tpOperation != other.tpOperation)
			return false;
		return true;
	}

	
}
