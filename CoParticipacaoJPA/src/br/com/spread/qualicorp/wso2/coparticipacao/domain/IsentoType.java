package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum IsentoType {
						GRAVIDA(1, "GRÁVIDA"),
						GESTANTE(1, "GESTANTE"),
						FILHOS_ATE_12_MESES(2, "MENORES ATÉ 12 MESES"),
						FILHOS_ATE_12_MESES_02(2, "RECEM NASCIDO"),
						ESTAGIARIO(3, "ESTAGIÁRIO"),
						DIRETORIA(4, "DIRETÓRIA"),
						CRONICO(5, "CRÔNICOS"),
						CRONICO_INATIVO(6, "CRONICOS / INATIVO"),
						VALOR(7, "VALOR"),
						VALOR_CENTAVO(8, "VALOR CENTAVO"),
						DEMITIDO(9,"DEMITIDO");

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
