package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public enum UseType {

						MECSAS(1, "Arquivo de base de dados"),
						MECSAS2(2, "Arquivo para atualizar base de dados"),
						NAO_LOCALIZADO(3, "Arquivos de criticas corrigidos pelo usuário"),
						ISENTO(4, "Relação de beneficiários isentos"),
						FATUCOPA(5, "Arquivo de lançamentos"),
						EXTRA_FILE(6, "Arquivo adicional");

	private Integer id;

	private String description;

	private UseType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static UseType parse(Integer id) {
		for (UseType useType : UseType.values()) {
			if (useType.getId().equals(id)) {
				return useType;
			}
		}

		return null;
	}
}
