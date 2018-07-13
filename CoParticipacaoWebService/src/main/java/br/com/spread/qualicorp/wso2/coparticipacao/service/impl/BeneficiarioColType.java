package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum BeneficiarioColType {

								NR_MATRICULA(1, "NR_MATRICULA"),
								NM_TITULAR(2, "NM_TITULAR"),
								NR_CPF(3, "NR_CPF"),
								NM_BENEFICIARIO(4, "NM_BENEFICIARIO");

	private int id;

	private String columnName;

	private BeneficiarioColType(int id, String columnName) {
		this.id = id;
		this.columnName = columnName;
	}

	public int getId() {
		return id;
	}

	public String getColumnName() {
		return columnName;
	}

	public static BeneficiarioColType parse(String columnName) {
		for (BeneficiarioColType beneficiarioColumn : BeneficiarioColType
				.values()) {
			if (beneficiarioColumn.getColumnName().equals(columnName)) {
				return beneficiarioColumn;
			}
		}

		return null;
	}
}
