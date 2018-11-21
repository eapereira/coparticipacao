package br.com.spread.qualicorp.wso2.coparticipacao.test.report.dataSource;

import net.sf.jasperreports.engine.base.JRBaseField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CoParticipacaoJRField extends JRBaseField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3133730872232086195L;
	
	private String name;

	public CoParticipacaoJRField(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
