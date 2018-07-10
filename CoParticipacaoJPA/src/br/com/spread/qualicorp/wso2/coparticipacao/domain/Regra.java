package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_regra database table.
 * 
 */
public abstract class Regra extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private Integer ordem;

	private String nameRegra;
	private RegraType tpRegra;
	private ArquivoInput arquivoInput;

	private List<RegraOperation> regraOperations;

	private List<RegraResult> regraResults;

	public Regra() {
		regraOperations = new ArrayList<>();
		regraResults = new ArrayList<>();
	}

	public Regra(Regra entity) {
		super(entity);
	}

	public Integer getOrdem() {
		return this.ordem;
	}

	public void setOrdem(Integer cdOrdem) {
		this.ordem = cdOrdem;
	}

	public String getNameRegra() {
		return this.nameRegra;
	}

	public void setNameRegra(String nmRegra) {
		this.nameRegra = nmRegra;
	}

	public RegraType getTpRegra() {
		return this.tpRegra;
	}

	public void setTpRegra(RegraType tpRegra) {
		this.tpRegra = tpRegra;
	}

	public ArquivoInput getArquivoInput() {
		return this.arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public List<RegraOperation> getRegraOperations() {
		return regraOperations;
	}

	public void setRegraOperations(List<RegraOperation> regraOperation) {
		this.regraOperations = regraOperation;
	}

	public void addRegraOperations(RegraOperation regraOperation) {
		getRegraOperations().add(regraOperation);
		regraOperation.setRegra(this);
	}

	public void removeRegraOperation(RegraOperation regraOperation) {
		if (getRegraOperations().contains(regraOperation)) {
			getRegraOperations().remove(regraOperation);
			regraOperation.setRegra(null);
		}
	}

	public List<RegraResult> getRegraResults() {
		return regraResults;
	}

	public void setRegraResults(List<RegraResult> regraResults) {
		this.regraResults = regraResults;
	}

	public void addRegraResults(RegraResult regraResult) {
		getRegraResults().add(regraResult);
		regraResult.setRegra(this);
	}

	public void removeRegraResult(RegraResult regraResult) {
		if (getRegraResults().contains(regraResult)) {
			getRegraResults().remove(regraResult);
			regraResult.setRegra(null);
		}
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
		result = prime * result
				+ ((regraOperations == null) ? 0 : regraOperations.hashCode());
		result = prime * result
				+ ((regraResults == null) ? 0 : regraResults.hashCode());
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
		Regra other = (Regra) obj;
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
		if (regraOperations == null) {
			if (other.regraOperations != null)
				return false;
		} else if (!regraOperations.equals(other.regraOperations))
			return false;
		if (regraResults == null) {
			if (other.regraResults != null)
				return false;
		} else if (!regraResults.equals(other.regraResults))
			return false;
		return true;
	}

}