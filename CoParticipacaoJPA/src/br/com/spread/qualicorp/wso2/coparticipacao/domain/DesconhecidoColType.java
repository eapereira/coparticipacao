package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * <ul>
 * <li>1 - NR_MATRICULA_TITULAR</li>
 * <li>2 - NR_MATRICULA_DEPENDENTE</li>
 * <li>3 - NM_BENEFICIARIO_TITULAR</li>
 * <li>4 - NM_BENEFICIARIO_DEPENDENTE</li>
 * <li>5 - DT_NASCIMENTO</li>
 * <li>6 - DT_NASCIMENTO_TITULAR</li>
 * <li>7 - NR_CPF_TITULAR</li>
 * <li>8 - NR_CPF_DEPENDENTE</li>
 * <li>9 - VL_PRINCIPAL</li>
 * <ul>
 * </br>
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum DesconhecidoColType {
									NR_MATRICULA(1, "NR_MATRICULA_TITULAR", 20),
									NM_BENEFICIARIO(2, "NM_TITULAR", 40),
									DT_NASCIMENTO(3, "DT_NASCIMENTO", 20),
									NR_CPF(4, "NR_CPF_TITULAR", 20),
									VL_PRINCIPAL(5, "VL_PRINCIPAL", 20),
									DT_ADMISSAO(6, "DT_ADMISSAO", 20),
									NR_REF_CODE(7, "NR_REF_CODE", 20);

	private Integer id;

	private String description;

	private Integer length;

	private DesconhecidoColType(Integer id, String description, Integer length) {
		this.id = id;
		this.description = description;
		this.length = length;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static DesconhecidoColType parse(Integer id) {
		for (DesconhecidoColType desconhecidoColType : DesconhecidoColType.values()) {
			if (desconhecidoColType.getId().equals(id)) {
				return desconhecidoColType;
			}
		}

		return null;
	}

	public Integer getLength() {
		return length;
	}
}
