package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public enum UseType {

	FATUCOPA(1, "Arquivo de lançamentos"),
	MECSAS(2, "Arquivo de base de dados");

	private Integer id;

	private String description;

	private UseType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static UseType parse(Integer id) {
		for (UseType useType : UseType.values()) {
			if (useType.getId().equals(id)) {
				return useType;
			}
		}

		return null;
	}
}
