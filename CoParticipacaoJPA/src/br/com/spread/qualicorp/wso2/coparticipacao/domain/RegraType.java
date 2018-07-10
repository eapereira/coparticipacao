package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public enum RegraType {
	SIMPLS(1, "Simples"),
	CONDICIONAL(2, "Condicional");

	private Integer id;

	private String description;

	private RegraType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static RegraType parse(Integer id) {
		for (RegraType regraType : RegraType.values()) {
			if (regraType.getId().equals(id)) {
				return regraType;
			}
		}

		return null;
	}
}
