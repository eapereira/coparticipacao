package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum ReportLayoutType {

								MULTIPLE_CONTRATO(0, "MULTIPLE-CONTRATO"),
								SINGLE_CONTRATO(1, "SINGLE-CONTRATO");

	private Integer id;
	private String description;

	private ReportLayoutType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static ReportLayoutType parse(Integer id) {
		for (ReportLayoutType reportLayoutType : ReportLayoutType.values()) {
			if (reportLayoutType.getId().equals(id)) {
				return reportLayoutType;
			}
		}

		return null;
	}
}
