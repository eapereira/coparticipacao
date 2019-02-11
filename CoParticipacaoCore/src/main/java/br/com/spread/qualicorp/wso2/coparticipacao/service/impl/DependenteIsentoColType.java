package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum DependenteIsentoColType {
										ID_DEPENDENTE(1, "ID_DEPENDENTE"),
										TP_ISENTO(2, "TP_ISENTO");

	private int id;

	private String columnName;

	private DependenteIsentoColType(int id, String columnName) {
		this.id = id;
		this.columnName = columnName;
	}

	public int getId() {
		return id;
	}

	public String getColumnName() {
		return columnName;
	}

	public static DependenteIsentoColType parse(String columnName) {
		for (DependenteIsentoColType dependenteIsentoColType : DependenteIsentoColType
				.values()) {
			if (dependenteIsentoColType.getColumnName().equals(columnName)) {
				return dependenteIsentoColType;
			}
		}

		return null;
	}
}
