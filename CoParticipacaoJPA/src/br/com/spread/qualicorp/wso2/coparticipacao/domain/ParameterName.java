package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum ParameterName {

							OUTPUT_FILE_PATH("outputReportDir");

	private String name;

	private ParameterName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
