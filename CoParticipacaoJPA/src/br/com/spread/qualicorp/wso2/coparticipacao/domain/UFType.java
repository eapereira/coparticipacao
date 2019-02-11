package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum UFType {
					ACRE("AC", "Acre"),
					ALAGOAS("AL", "Alagoas"),
					AMAZONAS("AM", "Amazonas"),
					AMAPA("AP", "Amapá"),
					BAHIA("BA", "Bahia"),
					CEARA("CE", "Ceara"),
					DISTRITO_FEDERAL("DF", "Distrito Federal"),
					ESPIRITO_SANTO("ES", "Espitito Santo"),
					GOIAS("GO", "Goias"),
					MARANHAO("MA", "Maranhão"),
					MINAS_GERAIS("MG", "Minas Gerais"),
					MATO_GROSSO_SUL("MS", "Mato Grosso do Sul"),
					MATO_GROSSO("MT", "Mato Grosso"),
					PARA("PA", "Pará"),
					PARAIBA("PB", "Paraíba"),
					PERNAMBUCO("PE", "Pernambuco"),
					PIAUI("PI", "Piaui"),
					PARANA("PR", "Parana"),
					RIO_JANEIRO("RJ", "Rio de Janeiro"),
					RIO_GRANDE_NORTE("RN", "Rio Grande do Norte"),
					RONDONIA("RO", "Rondônia"),
					RORAIMA("RR", "Roraima"),
					RIO_GRANDE_SUL("RS", "Rio Grande do Sul"),
					SANTA_CATARINA("SC", "Santa Catarina"),
					SERGIPE("SE", "Sergipe"),
					SAO_PAULO("SP", "São Paulo"),
					TOCANTINS("TO", "Tocantins");

	private String id;
	private String description;

	private UFType(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static UFType parse(String id) {
		for (UFType ufType : UFType.values()) {
			if (ufType.getId().equals(id)) {
				return ufType;
			}
		}

		return null;
	}

}
