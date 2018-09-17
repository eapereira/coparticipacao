package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum StatusExecucaoType {
								ERROR(0, "ERROR"),
								SUCCESS(1, "SUCCESS"),
								NOT_COMPLETED(3, "NOT_COMPLETED"),
								PENDING(4, "PENDING"),
								STARTED(5, "STARTED"),
								RUNNING(6, "RUNNING");

	private Integer id;

	private String description;

	private StatusExecucaoType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static StatusExecucaoType parse(Integer id) {
		for (StatusExecucaoType statusExecucaoType : StatusExecucaoType.values()) {
			if (statusExecucaoType.getId().equals(id)) {
				return statusExecucaoType;
			}
		}

		return null;
	}

	public static StatusExecucaoType parseByDescription(String description) {
		for (StatusExecucaoType statusExecucaoType : StatusExecucaoType.values()) {
			if (statusExecucaoType.getDescription().equals(description)) {
				return statusExecucaoType;
			}
		}

		return null;
	}

}
