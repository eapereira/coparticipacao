package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class RegraConditionalResult extends AbstractDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -375550280971399446L;

	private RegraConditional regraConditional;
	
	private Regra regraExecution;

	public RegraConditionalResult(){
		
	}

	public RegraConditional getRegraConditional() {
		return regraConditional;
	}

	public void setRegraConditional(RegraConditional regraConditional) {
		this.regraConditional = regraConditional;
	}

	public Regra getRegraExecution() {
		return regraExecution;
	}

	public void setRegraExecution(Regra regraExecution) {
		this.regraExecution = regraExecution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((regraConditional == null) ? 0
				: regraConditional.hashCode());
		result = prime * result
				+ ((regraExecution == null) ? 0 : regraExecution.hashCode());
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
		RegraConditionalResult other = (RegraConditionalResult) obj;
		if (regraConditional == null) {
			if (other.regraConditional != null)
				return false;
		} else if (!regraConditional.equals(other.regraConditional))
			return false;
		if (regraExecution == null) {
			if (other.regraExecution != null)
				return false;
		} else if (!regraExecution.equals(other.regraExecution))
			return false;
		return true;
	}

	
}
