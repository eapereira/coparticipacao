package br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitOdontoCoparticipacaoViewUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitOdontoReport extends BasicReport {

	private List<TechnitOdontoCoparticipacaoViewUi> technitOdontoCoparticipacaoViewUis;

	public TechnitOdontoReport() {
		super();
	}

	public List<TechnitOdontoCoparticipacaoViewUi> getTechnitOdontoCoparticipacaoViewUis() {
		return technitOdontoCoparticipacaoViewUis;
	}

	public void setTechnitOdontoCoparticipacaoViewUis(
			List<TechnitOdontoCoparticipacaoViewUi> technitOdontoCoparticipacaoViewUis) {
		this.technitOdontoCoparticipacaoViewUis = technitOdontoCoparticipacaoViewUis;
	}
}
