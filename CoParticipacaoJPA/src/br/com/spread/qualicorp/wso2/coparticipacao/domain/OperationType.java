package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public enum OperationType {
							ADD(1, "Somar"),
							SUBSTRACT(2, "Subtrair"),
							MULTIPLY(3, "Multiply"),
							DIVISION(4, "Divisão"),
							EQUALS(5, "Equals"),
							NOT_EQUALS(6, "Not Equals");

	private Integer id;

	private String description;

	private OperationType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static OperationType parse(Integer id) {
		for (OperationType operationType : OperationType.values()) {
			if (operationType.getId().equals(id)) {
				return operationType;
			}
		}

		return null;
	}
}
