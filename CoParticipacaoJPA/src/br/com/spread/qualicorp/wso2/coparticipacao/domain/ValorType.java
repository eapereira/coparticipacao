package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum ValorType {

						POSITIVO("+", "POSITIVO"),
						NEGATIVO("-", "NEGATIVO");

	private String id;
	private String description;

	private ValorType(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static ValorType parse(String id) {
		for (ValorType valorType : ValorType.values()) {
			if (valorType.getId().equals(id)) {
				return valorType;
			}
		}

		return null;
	}
}
