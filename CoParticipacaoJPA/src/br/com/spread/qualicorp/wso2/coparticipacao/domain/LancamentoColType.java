package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum LancamentoColType {
								ID_BENEFICIARIO(1, "ID_TITULAR"),
								ID_CONTRATO(2, "ID_CONTRATO"),
								CD_MES(3, "CD_MES"),
								CD_ANO(4, "CD_ANO");

	private Integer id;

	private String description;

	private LancamentoColType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static LancamentoColType parse(Integer id) {
		for (LancamentoColType lancamentoColType : LancamentoColType.values()) {
			if (lancamentoColType.getId().equals(id)) {
				return lancamentoColType;
			}
		}

		return null;
	}

	public static LancamentoColType parseByDescription(String description) {
		for (LancamentoColType lancamentoColType : LancamentoColType.values()) {
			if (lancamentoColType.getDescription().equals(description)) {
				return lancamentoColType;
			}
		}

		return null;
	}

}
