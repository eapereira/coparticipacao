package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class Report extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7123255214363603935L;

	private String nameReport;
	private String description;
	private String outputNameFormat;

	private Empresa empresa;

	public Report() {
		super();
	}

	public String getNameReport() {
		return nameReport;
	}

	public void setNameReport(String nameReport) {
		this.nameReport = nameReport;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOutputNameFormat() {
		return outputNameFormat;
	}

	public void setOutputNameFormat(String outputNameFormat) {
		this.outputNameFormat = outputNameFormat;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
