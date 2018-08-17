package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * <ul>
 * <li>1 - TP_BENEFICIARIO</li>
 * <li>2 - NR_MATRICULA</li>
 * <li>3 - NM_BENEFICIARIO</li>
 * <li>4 - DT_NASCIMENTO</li>
 * <li>5 - NR_CPF</li>
 * <ul>
 * </br>
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum BeneficiarioIsentoColType {
										TP_ISENTO(1, "TP_ISENTO"),
										NR_MATRICULA(2, "NR_MATRICULA"),
										NM_BENEFICIARIO(3, "NM_BENEFICIARIO"),
										DT_NASCIMENTO(4, "DT_NASCIMENTO"),
										NR_CPF(5, "NR_CPF_DEPENDENTE"),
										NR_MATRICULA_TITULAR(6, "NR_MATRICULA_TITULAR"),
										NM_TITULAR(7, "NM_TITULAR");

	private Integer id;

	private String description;

	private BeneficiarioIsentoColType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static BeneficiarioIsentoColType parse(Integer id) {
		for (BeneficiarioIsentoColType beneficiarioIsentoColType : BeneficiarioIsentoColType.values()) {
			if (beneficiarioIsentoColType.getId().equals(id)) {
				return beneficiarioIsentoColType;
			}
		}

		return null;
	}

}
