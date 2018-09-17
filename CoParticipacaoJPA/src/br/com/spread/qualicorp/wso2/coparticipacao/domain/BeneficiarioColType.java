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
									NR_MATRICULA(2, "NR_MATRICULA"),
									NM_BENEFICIARIO(3, "NM_BENEFICIARIO"),
									DT_NASCIMENTO(4, "DT_NASCIMENTO"),
									NR_CPF(5, "NR_CPF"),
									DT_ADMISSAO(6, "DT_ADMISSAO"),
									NM_LABEL(7, "NM_LABEL"),
									NR_REFERENCE_CODE(8, "NR_REFERENCE_CODE"),
									NR_DIGITO_CPF(9, "NR_DIGITO_CPF"),
									NR_MATRICULA_EMPRESA(10, "NR_MATRICULA_EMPRESA"),
									DT_DEMISSAO(11, "DT_DEMISSAO"),
									NM_TITULAR(12, "NM_TITULAR");

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
