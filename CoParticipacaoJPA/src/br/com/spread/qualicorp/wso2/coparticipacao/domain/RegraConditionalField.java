package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class RegraConditionalField extends AbstractDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7079838001281846490L;

	private ArquivoInputColsDef arquivoInputColsDef;

	private RegraConditionalOperation regraConditionalOperation;

	public RegraConditionalField() {

	}

	public ArquivoInputColsDef getArquivoInputColsDef() {
		return arquivoInputColsDef;
	}

	public void setArquivoInputColsDef(ArquivoInputColsDef arquivoInputColsDef) {
		this.arquivoInputColsDef = arquivoInputColsDef;
	}

	public RegraConditionalOperation getRegraConditionalOperation() {
		return regraConditionalOperation;
	}

	public void setRegraConditionalOperation(
			RegraConditionalOperation regraConditionalOperation) {
		this.regraConditionalOperation = regraConditionalOperation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInputColsDef == null) ? 0
				: arquivoInputColsDef.hashCode());
		result = prime * result + ((regraConditionalOperation == null) ? 0
				: regraConditionalOperation.hashCode());
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
		RegraConditionalField other = (RegraConditionalField) obj;
		if (arquivoInputColsDef == null) {
			if (other.arquivoInputColsDef != null)
				return false;
		} else if (!arquivoInputColsDef.equals(other.arquivoInputColsDef))
			return false;
		if (regraConditionalOperation == null) {
			if (other.regraConditionalOperation != null)
				return false;
		} else if (!regraConditionalOperation
				.equals(other.regraConditionalOperation))
			return false;
		return true;
	}
}
