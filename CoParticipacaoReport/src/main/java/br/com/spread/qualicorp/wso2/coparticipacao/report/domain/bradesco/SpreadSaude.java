package br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumerado com as <code>SUBFATURAS</code> utilizadas pela Spread para
 * diferenciar as empresas que devem ser listadas nos relatórios de
 * Coparticipação do sistema.
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum SpreadSaude {

							SPREAD_TELEINFORMATICA_001(1, "Spread Teleinformática"),
							SPREAD_TELEINFORMATICA_003(3, "Spread Teleinformática"),
							SPREAD_TELEINFORMATICA_008(8, "Spread Teleinformática (Petrobrás)"),
							SPREAD_TELEINFORMATICA_017(17, "Teleinformática"),
							SPREAD_TELEINFORMATICA_030(30, "Spread Teleinformática (CEG)"),
							NL_062(62, "NL"),
							NL_064(64, "NL"),
							NL_065(65, "NL"),
							SPREAD_TECNOLOGIA_SISTEMAS_110(110, "Spread Tecnologia em Sistemas"),
							SPREAD_TECNOLOGIA_SISTEMAS_111(111, "Spread Tecnologia em Sistemas"),
							NL_113(113, "NL"),
							NL_124(124, "NL"),
							SPREAD_TECNOLOGIA_SISTEMAS_128(128, "Spread Tecnologia em Sistemas"),
							SPREAD_AUTOMACAO_146(146, "Spread Sistemas e Automação"),
							NL_149(149, "NL"),
							SPREAD_SISTEMAS_150(150, "Sistemas"),
							SPREAD_SISTEMAS_162(162, "Sistemas"),
							SPREAD_TECNOLOGIA_SISTEMAS_180(180, "Spread Sistemas e Automação (Petrobrás)"),
							NL_186(186, "NL"),
							SPREAD_EQUIPAMENTOS_193(193, "Spread Comercio de Equipamentos"),
							SPREAD_TECNOLOGIA_SISTEMAS_300(300, "Spread Tecnologia em Sistemas (Diretoria)"),
							SPREAD_DEMITIDOS_851(851, "Extensão Inativos (Demitidos)"),
							SPREAD_APOSENTADOS_852(852, "Extensão Inativos (Aposentados)");

	private Integer id;
	private String description;

	private SpreadSaude(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static List<SpreadSaude> getInativos() {
		List<SpreadSaude> spreadSaudes = new ArrayList<>();

		spreadSaudes.add(SPREAD_APOSENTADOS_852);
		spreadSaudes.add(SPREAD_DEMITIDOS_851);

		return spreadSaudes;
	}

	public static List<SpreadSaude> getAtivos() {
		List<SpreadSaude> spreadSaudes = new ArrayList<>();

		spreadSaudes.add(SPREAD_TELEINFORMATICA_001);
		spreadSaudes.add(SPREAD_TELEINFORMATICA_003);
		spreadSaudes.add(SPREAD_TELEINFORMATICA_008);
		spreadSaudes.add(SPREAD_TELEINFORMATICA_017);
		spreadSaudes.add(SPREAD_TELEINFORMATICA_030);
		spreadSaudes.add(NL_062);
		spreadSaudes.add(NL_064);
		spreadSaudes.add(NL_065);
		spreadSaudes.add(SPREAD_TECNOLOGIA_SISTEMAS_110);
		spreadSaudes.add(SPREAD_TECNOLOGIA_SISTEMAS_111);
		spreadSaudes.add(NL_113);
		spreadSaudes.add(NL_124);
		spreadSaudes.add(SPREAD_TECNOLOGIA_SISTEMAS_128);
		spreadSaudes.add(SPREAD_AUTOMACAO_146);
		spreadSaudes.add(NL_149);
		spreadSaudes.add(SPREAD_SISTEMAS_150);
		spreadSaudes.add(SPREAD_SISTEMAS_162);
		spreadSaudes.add(SPREAD_TECNOLOGIA_SISTEMAS_180);
		spreadSaudes.add(NL_186);
		spreadSaudes.add(SPREAD_EQUIPAMENTOS_193);
		spreadSaudes.add(SPREAD_TECNOLOGIA_SISTEMAS_300);

		return spreadSaudes;
	}
}
