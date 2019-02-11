package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum CreateBeneficiarioType {

									ADD(0, "ADD"),
									ADD_AS_DEMITIDO(1, "ADD as Demitido");

	private Integer id;

	private String description;

	private CreateBeneficiarioType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static CreateBeneficiarioType parse(Integer id) {
		for (CreateBeneficiarioType createBeneficiarioType : CreateBeneficiarioType.values()) {
			if (createBeneficiarioType.getId().equals(id)) {
				return createBeneficiarioType;
			}
		}

		return null;
	}
}
