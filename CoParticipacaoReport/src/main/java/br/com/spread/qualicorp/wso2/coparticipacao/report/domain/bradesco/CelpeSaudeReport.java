package br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco;

import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeRateioViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeResumoViewUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CelpeSaudeReport extends BasicReport {
	private List<CelpeSaudeResumoViewUi> celpeSaudeResumoViewUis;

	private List<CelpeSaudeCoparticipacaoViewUi> celpeSaudeCoparticipacaoViewUis;

	private List<CelpeSaudeRateioViewUi> celpeSaudeRateioViewUis;

	public CelpeSaudeReport() {
		super();

		celpeSaudeResumoViewUis = new ArrayList<>();
		celpeSaudeCoparticipacaoViewUis = new ArrayList<>();
		celpeSaudeRateioViewUis = new ArrayList<>();
	}

	public List<CelpeSaudeResumoViewUi> getCelpeSaudeResumoViewUis() {
		return celpeSaudeResumoViewUis;
	}

	public void setCelpeSaudeResumoViewUis(List<CelpeSaudeResumoViewUi> celpeSaudeResumoViewUis) {
		this.celpeSaudeResumoViewUis = celpeSaudeResumoViewUis;
	}

	public List<CelpeSaudeCoparticipacaoViewUi> getCelpeSaudeCoparticipacaoViewUis() {
		return celpeSaudeCoparticipacaoViewUis;
	}

	public void setCelpeSaudeCoparticipacaoViewUis(
			List<CelpeSaudeCoparticipacaoViewUi> celpeSaudeCoparticipacaoViewUis) {
		this.celpeSaudeCoparticipacaoViewUis = celpeSaudeCoparticipacaoViewUis;
	}

	public List<CelpeSaudeRateioViewUi> getCelpeSaudeRateioViewUis() {
		return celpeSaudeRateioViewUis;
	}

	public void setCelpeSaudeRateioViewUis(List<CelpeSaudeRateioViewUi> celpeSaudeRateioViewUis) {
		this.celpeSaudeRateioViewUis = celpeSaudeRateioViewUis;
	}

}
