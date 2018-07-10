package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class RegraConditional extends AbstractDomain{
	/**
	 * 
	 */
	private static final long serialVersionUID = 598417944661402532L;

	private Integer ordem;

	private String nameRegra;
	private ArquivoInput arquivoInput;

	private List<RegraConditionalOperation> regraConditionalOperations;

	private List<RegraConditionalResult> regraConditionalResults;
	
	public RegraConditional(){
		
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public String getNameRegra() {
		return nameRegra;
	}

	public void setNameRegra(String nameRegra) {
		this.nameRegra = nameRegra;
	}

	public ArquivoInput getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public List<RegraConditionalOperation> getRegraConditionalOperations() {
		return regraConditionalOperations;
	}

	public void setRegraConditionalOperations(
			List<RegraConditionalOperation> regraConditionalOperations) {
		this.regraConditionalOperations = regraConditionalOperations;
	}

	public List<RegraConditionalResult> getRegraConditionalResults() {
		return regraConditionalResults;
	}

	public void setRegraConditionalResults(
			List<RegraConditionalResult> regraConditionalResults) {
		this.regraConditionalResults = regraConditionalResults;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arquivoInput == null) ? 0 : arquivoInput.hashCode());
		result = prime * result
				+ ((nameRegra == null) ? 0 : nameRegra.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result + ((regraConditionalOperations == null) ? 0
				: regraConditionalOperations.hashCode());
		result = prime * result + ((regraConditionalResults == null) ? 0
				: regraConditionalResults.hashCode());
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
		RegraConditional other = (RegraConditional) obj;
		if (arquivoInput == null) {
			if (other.arquivoInput != null)
				return false;
		} else if (!arquivoInput.equals(other.arquivoInput))
			return false;
		if (nameRegra == null) {
			if (other.nameRegra != null)
				return false;
		} else if (!nameRegra.equals(other.nameRegra))
			return false;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		if (regraConditionalOperations == null) {
			if (other.regraConditionalOperations != null)
				return false;
		} else if (!regraConditionalOperations
				.equals(other.regraConditionalOperations))
			return false;
		if (regraConditionalResults == null) {
			if (other.regraConditionalResults != null)
				return false;
		} else if (!regraConditionalResults
				.equals(other.regraConditionalResults))
			return false;
		return true;
	}
}
