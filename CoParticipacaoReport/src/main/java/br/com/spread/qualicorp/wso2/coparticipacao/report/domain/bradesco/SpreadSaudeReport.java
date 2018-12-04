package br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.SpreadSaudeCoparticipacaoResumidaViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.SpreadSaudeCoparticipacaoViewUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class SpreadSaudeReport {

	private SpreadSaudeResumoReport spreadSaudeResumoReport;

	private List<SpreadSaudeCoparticipacaoViewUi> spreadSaudeCoparticipacaoViewUis;

	private List<SpreadSaudeCoparticipacaoResumidaViewUi> saudeCoparticipacaoResumidaViewUis;

	public SpreadSaudeReport() {

	}

	public SpreadSaudeResumoReport getSpreadSaudeResumoReport() {
		return spreadSaudeResumoReport;
	}

	public void setSpreadSaudeResumoReport(SpreadSaudeResumoReport spreadSaudeResumoReport) {
		this.spreadSaudeResumoReport = spreadSaudeResumoReport;
	}

	public List<SpreadSaudeCoparticipacaoViewUi> getSpreadSaudeCoparticipacaoViewUis() {
		return spreadSaudeCoparticipacaoViewUis;
	}

	public void setSpreadSaudeCoparticipacaoViewUis(
			List<SpreadSaudeCoparticipacaoViewUi> spreadSaudeCoparticipacaoViewUis) {
		this.spreadSaudeCoparticipacaoViewUis = spreadSaudeCoparticipacaoViewUis;
	}

	public List<SpreadSaudeCoparticipacaoResumidaViewUi> getSaudeCoparticipacaoResumidaViewUis() {
		return saudeCoparticipacaoResumidaViewUis;
	}

	public void setSaudeCoparticipacaoResumidaViewUis(
			List<SpreadSaudeCoparticipacaoResumidaViewUi> saudeCoparticipacaoResumidaViewUis) {
		this.saudeCoparticipacaoResumidaViewUis = saudeCoparticipacaoResumidaViewUis;
	}
}
