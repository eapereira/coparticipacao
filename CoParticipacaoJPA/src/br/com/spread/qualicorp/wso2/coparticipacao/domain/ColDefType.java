package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public enum ColDefType {
	INT(1, "Integer"),
	DOUBLE(2, "Double"),
	STRING(3, "String"),
	DATE(4, "Date"),
	LONG(5, "Long");

	private Integer id;

	private String description;

	private ColDefType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static ColDefType parse(Integer id) {
		for (ColDefType colDefType : ColDefType.values()) {
			if (colDefType.getId().equals(id)) {
				return colDefType;
			}
		}

		return null;
	}
}
