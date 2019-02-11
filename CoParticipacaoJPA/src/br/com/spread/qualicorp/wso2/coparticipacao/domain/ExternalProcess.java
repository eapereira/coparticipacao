package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class ExternalProcess extends AbstractDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1971685328940748087L;
	
	private String regexpNameArquivoInput;
	private String regexpNameArquivoOutput;
	
	private Empresa empresa;
	
	public ExternalProcess() {
		
	}

	public String getRegexpNameArquivoInput() {
		return regexpNameArquivoInput;
	}

	public void setRegexpNameArquivoInput(String regexpNameArquivoInput) {
		this.regexpNameArquivoInput = regexpNameArquivoInput;
	}

	public String getRegexpNameArquivoOutput() {
		return regexpNameArquivoOutput;
	}

	public void setRegexpNameArquivoOutput(String regexpNameArquivoOutput) {
		this.regexpNameArquivoOutput = regexpNameArquivoOutput;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((regexpNameArquivoInput == null) ? 0 : regexpNameArquivoInput.hashCode());
		result = prime * result + ((regexpNameArquivoOutput == null) ? 0 : regexpNameArquivoOutput.hashCode());
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
		ExternalProcess other = (ExternalProcess) obj;
		if (regexpNameArquivoInput == null) {
			if (other.regexpNameArquivoInput != null)
				return false;
		} else if (!regexpNameArquivoInput.equals(other.regexpNameArquivoInput))
			return false;
		if (regexpNameArquivoOutput == null) {
			if (other.regexpNameArquivoOutput != null)
				return false;
		} else if (!regexpNameArquivoOutput.equals(other.regexpNameArquivoOutput))
			return false;
		return true;
	}
}
