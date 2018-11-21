package br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum TechnitSaude {
							SEDE(1, "SEDE", new String[] { "001", "007", "009" }),
							TENOVA(2, "TENOVA", new String[] { "003" }),
							TERNIUM(3, "TERNIUM", new String[] { "005" });

	private int id;

	private String description;

	private String[] subFaturas;

	private TechnitSaude(int id, String description, String[] subFaturas) {
		this.id = id;
		this.description = description;
		this.subFaturas = subFaturas;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String[] getSubFaturas() {
		return subFaturas;
	}

	public static TechnitSaude parse(String description) {
		for (TechnitSaude technitSaude : TechnitSaude.values()) {
			if (technitSaude.getDescription().equals(description)) {
				return technitSaude;
			}
		}

		return null;
	}

}
