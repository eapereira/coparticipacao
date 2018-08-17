package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class LancamentoInputCols extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3452243241595301408L;

	private LancamentoInput lancamentoInput;
	
	private ArquivoInputColsDef arquivoInputColsDef;
	
	private LancamentoColType lancamentoColType;

	public LancamentoInputCols() {

	}

	public LancamentoInput getLancamentoInput() {
		return lancamentoInput;
	}

	public void setLancamentoInput(LancamentoInput lancamentoInput) {
		this.lancamentoInput = lancamentoInput;
	}

	public ArquivoInputColsDef getArquivoInputColsDef() {
		return arquivoInputColsDef;
	}

	public void setArquivoInputColsDef(ArquivoInputColsDef arquivoInputColsDef) {
		this.arquivoInputColsDef = arquivoInputColsDef;
	}

	public LancamentoColType getLancamentoColType() {
		return lancamentoColType;
	}

	public void setLancamentoColType(LancamentoColType lancamentoColType) {
		this.lancamentoColType = lancamentoColType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInputColsDef == null) ? 0 : arquivoInputColsDef.hashCode());
		result = prime * result + ((lancamentoColType == null) ? 0 : lancamentoColType.hashCode());
		result = prime * result + ((lancamentoInput == null) ? 0 : lancamentoInput.hashCode());
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
		LancamentoInputCols other = (LancamentoInputCols) obj;
		if (arquivoInputColsDef == null) {
			if (other.arquivoInputColsDef != null)
				return false;
		} else if (!arquivoInputColsDef.equals(other.arquivoInputColsDef))
			return false;
		if (lancamentoColType != other.lancamentoColType)
			return false;
		if (lancamentoInput == null) {
			if (other.lancamentoInput != null)
				return false;
		} else if (!lancamentoInput.equals(other.lancamentoInput))
			return false;
		return true;
	}
}
