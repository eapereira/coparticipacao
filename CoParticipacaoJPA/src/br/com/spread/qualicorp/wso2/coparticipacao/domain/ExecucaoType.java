package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum ExecucaoType {
							OPEN(1, "Open"),
							RUNNING(2, "Running"),
							CLOSED(3, "Closed");

	private Integer id;
	private String description;

	private ExecucaoType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static ExecucaoType parse(Integer id) {
		for (ExecucaoType execucaoType : ExecucaoType.values()) {
			if (execucaoType.getId().equals(id)) {
				return execucaoType;
			}
		}

		return null;
	}
}
