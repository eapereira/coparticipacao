package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum ReportQueryType {

								QUERY_BY_CONTRATO_AND_PERIODO(0, "Pesquisar por CD_CONTRATO e Período dos lançamentos"),
								QUERY_BY_PERIODO_ONLY(1, "Pesquisar apenas por Período dos lançamentos"),
								QUERY_BY_SINGLE_CONTRATO(2, "Apenas um contrato por página");

	private Integer id;
	private String description;

	private ReportQueryType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static ReportQueryType parse(Integer id) {
		for (ReportQueryType reportQueryType : ReportQueryType.values()) {
			if (reportQueryType.getId().equals(id)) {
				return reportQueryType;
			}
		}

		return null;
	}
}
