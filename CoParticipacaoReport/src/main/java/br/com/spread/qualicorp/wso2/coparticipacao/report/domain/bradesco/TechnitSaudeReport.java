package br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitSaudeCoparticipacaoViewUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitSaudeReport extends BasicReport {

	private List<TechnitSaudeCoparticipacaoViewUi> technitSaudeCoparticipacaoViewUis;

	public TechnitSaudeReport() {
		super();
	}

	public List<TechnitSaudeCoparticipacaoViewUi> getTechnitSaudeCoparticipacaoViewUis() {
		return technitSaudeCoparticipacaoViewUis;
	}

	public void setTechnitSaudeCoparticipacaoViewUis(
			List<TechnitSaudeCoparticipacaoViewUi> technitSaudeCoparticipacaoViewUis) {
		this.technitSaudeCoparticipacaoViewUis = technitSaudeCoparticipacaoViewUis;
	}
}
