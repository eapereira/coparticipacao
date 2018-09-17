package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.time.LocalDateTime;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class ArquivoExecucao extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8083080558524800204L;

	private Integer mes;

	private Integer ano;

	private StatusExecucaoType statusExecucaoType;

	private Contrato contrato;

	private LocalDateTime started;

	private LocalDateTime finnished;
	
	private String nameArquivoInput;
	
	private String nameArquivoOutput;
	
	private String errorMessage;
	
	private Integer ordem;

	public ArquivoExecucao() {
		super();
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public StatusExecucaoType getStatusExecucaoType() {
		return statusExecucaoType;
	}

	public void setStatusExecucaoType(StatusExecucaoType statusExecucaoType) {
		this.statusExecucaoType = statusExecucaoType;
	}

	public LocalDateTime getStarted() {
		return started;
	}

	public void setStarted(LocalDateTime started) {
		this.started = started;
	}

	public LocalDateTime getFinnished() {
		return finnished;
	}

	public void setFinnished(LocalDateTime finnished) {
		this.finnished = finnished;
	}

	public String getNameArquivoInput() {
		return nameArquivoInput;
	}

	public void setNameArquivoInput(String nameArquivoInput) {
		this.nameArquivoInput = nameArquivoInput;
	}

	public String getNameArquivoOutput() {
		return nameArquivoOutput;
	}

	public void setNameArquivoOutput(String nameArquivoOutput) {
		this.nameArquivoOutput = nameArquivoOutput;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + ((finnished == null) ? 0 : finnished.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((nameArquivoInput == null) ? 0 : nameArquivoInput.hashCode());
		result = prime * result + ((nameArquivoOutput == null) ? 0 : nameArquivoOutput.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result + ((started == null) ? 0 : started.hashCode());
		result = prime * result + ((statusExecucaoType == null) ? 0 : statusExecucaoType.hashCode());
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
		ArquivoExecucao other = (ArquivoExecucao) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (finnished == null) {
			if (other.finnished != null)
				return false;
		} else if (!finnished.equals(other.finnished))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		if (nameArquivoInput == null) {
			if (other.nameArquivoInput != null)
				return false;
		} else if (!nameArquivoInput.equals(other.nameArquivoInput))
			return false;
		if (nameArquivoOutput == null) {
			if (other.nameArquivoOutput != null)
				return false;
		} else if (!nameArquivoOutput.equals(other.nameArquivoOutput))
			return false;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		if (started == null) {
			if (other.started != null)
				return false;
		} else if (!started.equals(other.started))
			return false;
		if (statusExecucaoType != other.statusExecucaoType)
			return false;
		return true;
	}

}
