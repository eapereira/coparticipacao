package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum SexoType {

						MASCULINO("M", "MASCULINO"),
						FEMININO("F", "FEMININO");

	private String id;
	private String description;

	private SexoType(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static SexoType parse(String id) {
		for (SexoType sexoType : SexoType.values()) {
			if (sexoType.getId().equals(id)) {
				return sexoType;
			}
		}

		return null;
	}
}
