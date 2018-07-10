package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public enum UserStatusType {

	ATIVO(1, "Ativo"),
	BLOCKED(2, "bLOQUEADO");

	private Integer id;

	private String description;

	private UserStatusType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static UserStatusType parse(Integer id) {
		for (UserStatusType userStatusType : UserStatusType.values()) {
			if (userStatusType.getId().equals(id)) {
				return userStatusType;
			}
		}

		return null;
	}
}
