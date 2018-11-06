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

	private List<Titular> titulars;

	private boolean automaticCreateBeneficiario;

	private String outputReportDir;

	private String inputDir;

	private String outputDir;

	private String failureDir;

	private boolean saveMecsasDetails;

	private boolean saveBeneficiarioDetails;

	private boolean enabledExternalProcess;

	private ExternalProcess externalProcess;

	private List<Execucao> execucaos;

	private ReportQueryType reportQueryType;

	private List<Report> reports;
	
	private boolean automaticCreateTitular;
	
	private boolean searchDependentesWithoutName;
	
	private boolean acceptTitularWithoutCpf;

	public Empresa() {
		titulars = new ArrayList<>();
		contratos = new ArrayList<>();
		execucaos = new ArrayList<>();
		reports = new ArrayList<>();
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

	public List<Titular> getTitulars() {
		return titulars;
	}

	public void setTitulars(List<Titular> titulars) {
		this.titulars = titulars;
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

	public List<Execucao> getExecucaos() {
		return execucaos;
	}

	public void setExecucaos(List<Execucao> execucaos) {
		this.execucaos = execucaos;
	}

	public void addExecucao(Execucao execucao) {
		getExecucaos().add(execucao);
		execucao.setEmpresa(this);
	}

	public void removeExecucao(Execucao execucao) {
		getExecucaos().remove(execucao);
		execucao.setEmpresa(null);
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String getFailureDir() {
		return failureDir;
	}

	public void setFailureDir(String failureDir) {
		this.failureDir = failureDir;
	}

	public ReportQueryType getReportQueryType() {
		return reportQueryType;
	}

	public void setReportQueryType(ReportQueryType reportQueryType) {
		this.reportQueryType = reportQueryType;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public void addReport(Report report) {
		getReports().add(report);
		report.setEmpresa(this);
	}

	public void removeReport(Report report) {
		getReports().remove(report);
		report.setEmpresa(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (automaticCreateBeneficiario ? 1231 : 1237);
		result = prime * result + ((cdEmpresa == null) ? 0 : cdEmpresa.hashCode());
		result = prime * result + ((contratos == null) ? 0 : contratos.hashCode());
		result = prime * result + (enabledExternalProcess ? 1231 : 1237);
		result = prime * result + ((execucaos == null) ? 0 : execucaos.hashCode());
		result = prime * result + ((externalProcess == null) ? 0 : externalProcess.hashCode());
		result = prime * result + ((failureDir == null) ? 0 : failureDir.hashCode());
		result = prime * result + ((inputDir == null) ? 0 : inputDir.hashCode());
		result = prime * result + ((nameEmpresa == null) ? 0 : nameEmpresa.hashCode());
		result = prime * result + ((outputDir == null) ? 0 : outputDir.hashCode());
		result = prime * result + ((outputReportDir == null) ? 0 : outputReportDir.hashCode());
		result = prime * result + ((reportQueryType == null) ? 0 : reportQueryType.hashCode());
		result = prime * result + ((reports == null) ? 0 : reports.hashCode());
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
		if (execucaos == null) {
			if (other.execucaos != null)
				return false;
		} else if (!execucaos.equals(other.execucaos))
			return false;
		if (externalProcess == null) {
			if (other.externalProcess != null)
				return false;
		} else if (!externalProcess.equals(other.externalProcess))
			return false;
		if (failureDir == null) {
			if (other.failureDir != null)
				return false;
		} else if (!failureDir.equals(other.failureDir))
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
		if (outputDir == null) {
			if (other.outputDir != null)
				return false;
		} else if (!outputDir.equals(other.outputDir))
			return false;
		if (outputReportDir == null) {
			if (other.outputReportDir != null)
				return false;
		} else if (!outputReportDir.equals(other.outputReportDir))
			return false;
		if (reportQueryType != other.reportQueryType)
			return false;
		if (reports == null) {
			if (other.reports != null)
				return false;
		} else if (!reports.equals(other.reports))
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

	public boolean isAutomaticCreateTitular() {
		return automaticCreateTitular;
	}

	public void setAutomaticCreateTitular(boolean automaticCreateTitular) {
		this.automaticCreateTitular = automaticCreateTitular;
	}

	public boolean isSearchDependentesWithoutName() {
		return searchDependentesWithoutName;
	}

	public void setSearchDependentesWithoutName(boolean searchDependentesWithoutName) {
		this.searchDependentesWithoutName = searchDependentesWithoutName;
	}

	public boolean isAcceptTitularWithoutCpf() {
		return acceptTitularWithoutCpf;
	}

	public void setAcceptTitularWithoutCpf(boolean acceptTitularWithoutCpf) {
		this.acceptTitularWithoutCpf = acceptTitularWithoutCpf;
	}

}
