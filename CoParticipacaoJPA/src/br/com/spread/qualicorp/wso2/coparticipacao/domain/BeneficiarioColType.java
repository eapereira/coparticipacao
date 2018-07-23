package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * <ul>
 * <li>1 - TP_BENEFICIARIO</li>
 * <li>2 - NR_MATRICULA_TITULAR</li>
 * <li>3 - NR_MATRICULA_DEPENDENTE</li>
 * <li>4 - NM_BENEFICIARIO_TITULAR</li>
 * <li>5 - NM_BENEFICIARIO_DEPENDENTE</li>
 * <li>6 - DT_NASCIMENTO</li>
 * <li>7 - NR_CPF_TITULAR</li>
 * <li>8 - NR_CPF_DEPENDENTE</li>
 * <ul>
 * </br>
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum BeneficiarioColType {
									TP_BENEFICIARIO(1, "TP_BENEFICIARIO"),
									NR_MATRICULA_TITULAR(
											2,
											"NR_MATRICULA_TITULAR"),
									NR_MATRICULA_DEPENDENTE(
											3,
											"NR_MATRICULA_DEPENDENTE"),
									NM_BENEFICARIO_TITULAR(
											4,
											"NM_BENEFICIARIO_TITULAR"),
									NM_BENEFICIARIO_DEPENDENTE(
											5,
											"NM_BENEFICIARIO_DEPENDENTE"),
									DT_NASCIMENTO(6, "DT_NASCIMENTO"),
									NR_CPF_TITULAR(7, "NR_CPF_TITULAR"),
									NR_CPF_DEPENDENTE(8, "NR_CPF_DEPENDENTE");

	private Integer id;

	private String description;

	private BeneficiarioColType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static BeneficiarioColType parse(Integer id) {
		for (BeneficiarioColType mecsasColType : BeneficiarioColType.values()) {
			if (mecsasColType.getId().equals(id)) {
				return mecsasColType;
			}
		}

		return null;
	}

}
