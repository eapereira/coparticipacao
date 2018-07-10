package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public enum BeneficiarioType {

	TITULAR(0, "Titular"),
	CONJUGE(1, "Conjuge"),
	FILHO(2, "Filho"),
	PAIS(3, "Pais"),
	COMPANHEIRO(4, "Companheira(o)"),
	OUTROS(5, "Outros");

	private Integer id;

	private String description;

	private BeneficiarioType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static BeneficiarioType parse(Integer id) {
		for (BeneficiarioType dependenteType : BeneficiarioType.values()) {
			if (dependenteType.getId().equals(id)) {
				return dependenteType;
			}
		}

		return null;
	}
}
