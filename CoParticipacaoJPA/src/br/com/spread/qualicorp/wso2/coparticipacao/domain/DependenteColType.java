package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum DependenteColType {
								TP_DEPENDENTE(1, "TP_DEPENDENTE"),
								NM_DEPENDENTE(2, "NM_DEPENDENTE"),
								NR_CPF(3, "NR_CPF"),
								DT_NASCIMENTO(4, "DT_NASCIMENTO");

	private Integer id;

	private String description;

	private DependenteColType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static DependenteColType parse(Integer id) {
		for (DependenteColType DependenteColType : DependenteColType.values()) {
			if (DependenteColType.getId().equals(id)) {
				return DependenteColType;
			}
		}

		return null;
	}

	public static DependenteColType parseByDescription(String description) {
		for (DependenteColType DependenteColType : DependenteColType.values()) {
			if (DependenteColType.getDescription().equals(description)) {
				return DependenteColType;
			}
		}

		return null;
	}
}
