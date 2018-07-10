package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public enum ArquivoType {
	FIXED_LENGTH(1, "Flat File"),
	CSV(2, "Comma Delimited");

	private Integer id;

	private String description;

	private ArquivoType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static ArquivoType parse(Integer id) {
		for (ArquivoType arquivoType : ArquivoType.values()) {
			if (arquivoType.getId().equals(id)) {
				return arquivoType;
			}
		}

		return null;
	}
}
