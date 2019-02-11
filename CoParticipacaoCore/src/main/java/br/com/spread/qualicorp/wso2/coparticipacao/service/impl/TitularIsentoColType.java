package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum TitularIsentoColType {
									ID_TITULAR(1, "ID_TITULAR"),
									TP_ISENTO(2, "TP_ISENTO");

	private int id;

	private String columnName;

	private TitularIsentoColType(int id, String columnName) {
		this.id = id;
		this.columnName = columnName;
	}

	public int getId() {
		return id;
	}

	public String getColumnName() {
		return columnName;
	}

	public static TitularIsentoColType parse(String columnName) {
		for (TitularIsentoColType titularIsentoColType : TitularIsentoColType
				.values()) {
			if (titularIsentoColType.getColumnName().equals(columnName)) {
				return titularIsentoColType;
			}
		}

		return null;
	}
}
