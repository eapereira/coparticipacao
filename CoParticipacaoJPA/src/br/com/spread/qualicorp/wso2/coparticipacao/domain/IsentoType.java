package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum IsentoType {
						GRAVIDA(1, "Grávida"),
						DIRETORIA(2, "Diretória");

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
}
