package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum IsentoType {
						GRAVIDA(1, "GRÁVIDA", "GRÁVIDA|GESTANTE|GESTANTES"),
						FILHOS_ATE_12_MESES(2, "MENORES ATÉ 12 MESES", "MENORES ATÉ 12 MESES|RECEM NASCIDO"),
						ESTAGIARIO(3, "ESTAGIÁRIO", "ESTAGIÁRIO"),
						DIRETORIA(4, "DIRETÓRIA", "DIRETÓRIA"),
						CRONICO(5, "CRÔNICOS", "CRÔNICOS|CRONICOS|CRÔNICO|CRONICO"),
						CRONICO_INATIVO(6, "CRONICOS / INATIVO", "CRONICOS / INATIVO"),
						VALOR(7, "VALOR", "VALOR"),
						VALOR_CENTAVO(8, "VALOR CENTAVO", "VALOR CENTAVO"),
						DEMITIDO(9, "DEMITIDO", "DEMITIDO");

	private Integer id;

	private String description;

	private Pattern regexpDescription;

	private IsentoType(Integer id, String description, String regexpDescription) {
		this.id = id;
		this.description = description;
		this.regexpDescription = Pattern.compile(regexpDescription);
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static IsentoType parse(Integer id) {
		for (IsentoType isentoType : IsentoType.values()) {
			if (isentoType.getId().equals(id)) {
				return isentoType;
			}
		}

		return null;
	}

	public static IsentoType parse(String description) {
		Matcher matcher;

		for (IsentoType isentoType : IsentoType.values()) {
			matcher = isentoType.getRegexpDescription().matcher(description);

			if (matcher.find()) {
				return isentoType;
			}
		}

		return null;
	}

	public Pattern getRegexpDescription() {
		return regexpDescription;
	}
}
