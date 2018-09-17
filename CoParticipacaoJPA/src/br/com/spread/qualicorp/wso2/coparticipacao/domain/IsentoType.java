package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum IsentoType {
						GRAVIDA(1, "Grávida"),
						GESTANTE(1, "Gestante"),
						FILHOS_ATE_12_MESES(2, "Menores até 12 meses"),
						ESTAGIARIO(3, "Estagiário"),
						DIRETORIA(4, "Diretória"),
						CRONICO(5, "Crônicos"),
						CRONICO_INATIVO(6, "CRONICOS / INATIVO"),
						VALOR(7, "VALOR"),
						VALOR_CENTAVO(8, "VALOR CENTAVO");

	private Integer id;

	private String description;

	private IsentoType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static IsentoType parse(Integer id) {
		for (IsentoType isentoType : IsentoType.values()) {
			if (isentoType.getId().equals(id)) {
				return isentoType;
			}
		}

		return null;
	}

	public static IsentoType parse(String description) {
		for (IsentoType isentoType : IsentoType.values()) {
			if (isentoType.getDescription().equalsIgnoreCase(description)) {
				return isentoType;
			}
		}

		return null;
	}
}
