package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum GrauEscolaridadeType {
									EDUCACAO_INFANTIL(1, "Educação Infantil"),
									FUNDAMENTAL(2, "Fundamental"),
									MEDIO(3, "Médio"),
									SUPERIOR(4, "Superior"),
									POS_GRADUACAO(5, "Pos-Graduação"),
									MESTRADO(6, "Mestrado"),
									DOUTORADO(7, "Doutorado");

	private Integer id;
	private String description;

	private GrauEscolaridadeType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static GrauEscolaridadeType parse(Integer id) {
		for (GrauEscolaridadeType grauEscolaridadeType : GrauEscolaridadeType.values()) {
			if (grauEscolaridadeType.getId().equals(id)) {
				return grauEscolaridadeType;
			}
		}

		return null;
	}

}
