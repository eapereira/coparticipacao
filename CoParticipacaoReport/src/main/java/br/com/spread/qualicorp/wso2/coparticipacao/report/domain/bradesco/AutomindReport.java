package br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.AutomindCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.AutomindResumoViewUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class AutomindReport extends BasicReport {

	private List<AutomindCoparticipacaoViewUi> automindCoparticipacaoViewUis;

	private List<AutomindResumoViewUi> automindResumoViewUis;

	public AutomindReport() {

	}

	public List<AutomindCoparticipacaoViewUi> getAutomindCoparticipacaoViewUis() {
		return automindCoparticipacaoViewUis;
	}

	public void setAutomindCoparticipacaoViewUis(List<AutomindCoparticipacaoViewUi> automindCoparticipacaoViewUis) {
		this.automindCoparticipacaoViewUis = automindCoparticipacaoViewUis;
	}

	public List<AutomindResumoViewUi> getAutomindResumoViewUis() {
		return automindResumoViewUis;
	}

	public void setAutomindResumoViewUis(List<AutomindResumoViewUi> automindResumoViewUis) {
		this.automindResumoViewUis = automindResumoViewUis;
	}
	
}
