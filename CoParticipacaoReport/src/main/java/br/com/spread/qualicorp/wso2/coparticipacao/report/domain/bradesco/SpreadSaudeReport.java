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

	private List<SpreadSaudeResumoReport> spreadSaudeResumoReports;

	private List<SpreadSaudeCoparticipacaoViewUi> spreadSaudeCoparticipacaoViewUis;

	private List<SpreadSaudeCoparticipacaoResumidaViewUi> spreadSaudeCoparticipacaoResumidaViewUis;

	public SpreadSaudeReport() {

	}

	public List<SpreadSaudeResumoReport> getSpreadSaudeResumoReports() {
		return spreadSaudeResumoReports;
	}

	public void setSpreadSaudeResumoReports(List<SpreadSaudeResumoReport> spreadSaudeResumoReports) {
		this.spreadSaudeResumoReports = spreadSaudeResumoReports;
	}

	public List<SpreadSaudeCoparticipacaoViewUi> getSpreadSaudeCoparticipacaoViewUis() {
		return spreadSaudeCoparticipacaoViewUis;
	}

	public void setSpreadSaudeCoparticipacaoViewUis(
			List<SpreadSaudeCoparticipacaoViewUi> spreadSaudeCoparticipacaoViewUis) {
		this.spreadSaudeCoparticipacaoViewUis = spreadSaudeCoparticipacaoViewUis;
	}

	public List<SpreadSaudeCoparticipacaoResumidaViewUi> getSpreadSaudeCoparticipacaoResumidaViewUis() {
		return spreadSaudeCoparticipacaoResumidaViewUis;
	}

	public void setSpreadSaudeCoparticipacaoResumidaViewUis(
			List<SpreadSaudeCoparticipacaoResumidaViewUi> spreadSaudeCoparticipacaoResumidaViewUis) {
		this.spreadSaudeCoparticipacaoResumidaViewUis = spreadSaudeCoparticipacaoResumidaViewUis;
	}

}
