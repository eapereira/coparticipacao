package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_empresa database table.
 * 
 */
public abstract class Empresa extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private String nameEmpresa;

	private String cdEmpresa;

	private List<Contrato> contratos;

	private Operadora operadora;

	private List<Parameter> parameters;

	private List<Titular> titulars;

	private boolean automaticCreateBeneficiario;

	private String outputReportDir;
	
	private String inputDir;
	
	private boolean saveMecsasDetails;
	
	private boolean saveBeneficiarioDetails;

	private boolean enabledExternalProcess;
	
	private ExternalProcess externalProcess;
	
	public Empresa() {
		parameters = new ArrayList<>();
		titulars = new ArrayList<>();
		contratos = new ArrayList<>();
	}

	public Empresa(Empresa empresa) {
		super(empresa);
	}

	public String getNameEmpresa() {
		return this.nameEmpresa;
	}

	public void setNameEmpresa(String nameEmpresa) {
		this.nameEmpresa = nameEmpresa;
	}

	public List<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public Contrato addContrato(Contrato contrato) {
		getContratos().add(contrato);
		contrato.setEmpresa(this);

		return contrato;
	}

	public Contrato removeContrato(Contrato contrato) {
		getContratos().remove(contrato);
		contrato.setEmpresa(null);

		return contrato;
	}

	public Parameter addParameter(Parameter parameter) {
		getParameters().add(parameter);
		parameter.setEmpresa(this);

		return parameter;
	}

	public List<Parameter> getParameters() {
		return this.parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public List<Titular> getTitulars() {
		return titulars;
	}

	public void setTitulars(List<Titular> titulars) {
		this.titulars = titulars;
	}

	public Titular removeTitular(Titular titular) {
		getTitulars().remove(titular);
		titular.setEmpresa(null);

		return titular;
	}

	public Titular addTitular(Titular titular) {
		getTitulars().add(titular);
		titular.setEmpresa(this);

		return titular;
	}

	public Operadora getOperadora() {
		return operadora;
	}

	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}

	public boolean isAutomaticCreateBeneficiario() {
		return automaticCreateBeneficiario;
	}

	public void setAutomaticCreateBeneficiario(boolean automaticCreateBeneficiario) {
		this.automaticCreateBeneficiario = automaticCreateBeneficiario;
	}

	public String getOutputReportDir() {
		return outputReportDir;
	}

	public void setOutputReportDir(String outputReportDir) {
		this.outputReportDir = outputReportDir;
	}

	public String getCdEmpresa() {
		return cdEmpresa;
	}

	public void setCdEmpresa(String cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}

	public String getInputDir() {
		return inputDir;
	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}

	public boolean isSaveMecsasDetails() {
		return saveMecsasDetails;
	}

	public void setSaveMecsasDetails(boolean saveMecsasDetails) {
		this.saveMecsasDetails = saveMecsasDetails;
	}

	public boolean isSaveBeneficiarioDetails() {
		return saveBeneficiarioDetails;
	}

	public void setSaveBeneficiarioDetails(boolean saveBeneficiarioDetails) {
		this.saveBeneficiarioDetails = saveBeneficiarioDetails;
	}

	public boolean isEnabledExternalProcess() {
		return enabledExternalProcess;
	}

	public void setEnabledExternalProcess(boolean enabledExternalProcess) {
		this.enabledExternalProcess = enabledExternalProcess;
	}

	public ExternalProcess getExternalProcess() {
		return externalProcess;
	}

	public void setExternalProcess(ExternalProcess externalProcess) {
		this.externalProcess = externalProcess;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (automaticCreateBeneficiario ? 1231 : 1237);
		result = prime * result + ((cdEmpresa == null) ? 0 : cdEmpresa.hashCode());
		result = prime * result + ((contratos == null) ? 0 : contratos.hashCode());
		result = prime * result + (enabledExternalProcess ? 1231 : 1237);
		result = prime * result + ((externalProcess == null) ? 0 : externalProcess.hashCode());
		result = prime * result + ((inputDir == null) ? 0 : inputDir.hashCode());
		result = prime * result + ((nameEmpresa == null) ? 0 : nameEmpresa.hashCode());
		result = prime * result + ((outputReportDir == null) ? 0 : outputReportDir.hashCode());
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result + (saveBeneficiarioDetails ? 1231 : 1237);
		result = prime * result + (saveMecsasDetails ? 1231 : 1237);
		result = prime * result + ((titulars == null) ? 0 : titulars.hashCode());
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
		Empresa other = (Empresa) obj;
		if (automaticCreateBeneficiario != other.automaticCreateBeneficiario)
			return false;
		if (cdEmpresa == null) {
			if (other.cdEmpresa != null)
				return false;
		} else if (!cdEmpresa.equals(other.cdEmpresa))
			return false;
		if (contratos == null) {
			if (other.contratos != null)
				return false;
		} else if (!contratos.equals(other.contratos))
			return false;
		if (enabledExternalProcess != other.enabledExternalProcess)
			return false;
		if (externalProcess == null) {
			if (other.externalProcess != null)
				return false;
		} else if (!externalProcess.equals(other.externalProcess))
			return false;
		if (inputDir == null) {
			if (other.inputDir != null)
				return false;
		} else if (!inputDir.equals(other.inputDir))
			return false;
		if (nameEmpresa == null) {
			if (other.nameEmpresa != null)
				return false;
		} else if (!nameEmpresa.equals(other.nameEmpresa))
			return false;
		if (outputReportDir == null) {
			if (other.outputReportDir != null)
				return false;
		} else if (!outputReportDir.equals(other.outputReportDir))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (saveBeneficiarioDetails != other.saveBeneficiarioDetails)
			return false;
		if (saveMecsasDetails != other.saveMecsasDetails)
			return false;
		if (titulars == null) {
			if (other.titulars != null)
				return false;
		} else if (!titulars.equals(other.titulars))
			return false;
		return true;
	}



}
