package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum TitularColType {
							NM_MATRICULA(1, "NM_MATRICULA"),
							NM_TITULAR(2, "NM_TITULAR"),
							NR_CPF(3, "NR_CPF"),
							DT_NASCIMENTO(4, "DT_NASCIMENTO"),
							DT_ADMISSAO(5, "DT_ADMISSAO");

	private Integer id;

	private String description;

	private TitularColType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static TitularColType parse(Integer id) {
		for (TitularColType TitularColType : TitularColType.values()) {
			if (TitularColType.getId().equals(id)) {
				return TitularColType;
			}
		}

		return null;
	}

	public static TitularColType parseByDescription(String description) {
		for (TitularColType TitularColType : TitularColType.values()) {
			if (TitularColType.getDescription().equals(description)) {
				return TitularColType;
			}
		}

		return null;
	}
}
