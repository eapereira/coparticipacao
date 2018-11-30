package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum LancamentoColType {
								NR_MATRICULA_DEPENDENTE(1, "NR_MATRICULA_DEPENDENTE"),
								ID_CONTRATO(2, "ID_CONTRATO"),
								CD_MES(3, "CD_MES"),
								CD_ANO(4, "CD_ANO"),
								VL_PRINCIPAL(5, "VL_PRINCIPAL"),
								DT_MOVIMENTO(6, "DT_MOVIMENTO"),
								TP_VALOR(7, "TP_VALOR"),
								NR_MATRICULA_TITULAR(8, "NR_MATRICULA_TITULAR"),
								NR_CPF(9, "NR_CPF"),
								NM_BENEFICIARIO(10, "NM_BENEFICIARIO"),
								NM_TITULAR(11, "NM_TITULAR"),
								DT_NASCIMENTO(12, "DT_NASCIMENTO"),
								VL_REEMBOLSO(13, "VL_REEMBOLSO"),
								VL_PARTICIPACAO(14, "VL_PARTICIPACAO"),
								CD_USUARIO(15, "CD_USUARIO"),
								DT_UTILIZACAO(16,"DT_UTILIZACAO");

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
