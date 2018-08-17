package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.List;

/**
 * The persistent class for the tb_input_lancamento database table.
 * 
 */
public abstract class LancamentoInput extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4485692443673974143L;

	private ArquivoInput arquivoInput;

	private List<LancamentoInputCols> lancamentoInputCols;

	public LancamentoInput() {
	}

	public ArquivoInput getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public List<LancamentoInputCols> getLancamentoInputCols() {
		return lancamentoInputCols;
	}

	public void setLancamentoInputCols(List<LancamentoInputCols> lancamentoInputCols) {
		this.lancamentoInputCols = lancamentoInputCols;
	}

	public void addLancamentoInputCols(LancamentoInputCols lancamentoInputCols) {
		getLancamentoInputCols().add(lancamentoInputCols);
		lancamentoInputCols.setLancamentoInput(this);
	}

	public void removeLancamentoInputCols(LancamentoInputCols lancamentoInputCols) {
		getLancamentoInputCols().remove(lancamentoInputCols);
		lancamentoInputCols.setLancamentoInput(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInput == null) ? 0 : arquivoInput.hashCode());
		result = prime * result + ((lancamentoInputCols == null) ? 0 : lancamentoInputCols.hashCode());
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
		LancamentoInput other = (LancamentoInput) obj;
		if (arquivoInput == null) {
			if (other.arquivoInput != null)
				return false;
		} else if (!arquivoInput.equals(other.arquivoInput))
			return false;
		if (lancamentoInputCols == null) {
			if (other.lancamentoInputCols != null)
				return false;
		} else if (!lancamentoInputCols.equals(other.lancamentoInputCols))
			return false;
		return true;
	}

}