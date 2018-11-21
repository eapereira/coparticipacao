package br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum TechnitOdonto {

							SEDE(1, "SEDE", new String[] { "001", "005", "006" }),
							DUTRA(2, "DUTRA", new String[] { "003" });

	private int id;

	private String description;

	private String[] subFaturas;

	private TechnitOdonto(int id, String description, String[] subFaturas) {
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

	public static TechnitOdonto parse(String description) {
		for (TechnitOdonto technitOdonto : TechnitOdonto.values()) {
			if (technitOdonto.getDescription().equals(description)) {
				return technitOdonto;
			}
		}

		return null;
	}
}
