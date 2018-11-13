package br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.LMTransportesCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.LMTransportesResumoViewUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class LMTransportesReport extends BasicReport{

	private List<LMTransportesCoparticipacaoViewUi> lmTransportesCoparticipacaoViewUis;

	private List<LMTransportesResumoViewUi> lmTransportesResumoViewUis;

	public LMTransportesReport() {

	}

	public List<LMTransportesCoparticipacaoViewUi> getLmTransportesCoparticipacaoViewUis() {
		return lmTransportesCoparticipacaoViewUis;
	}

	public void setLmTransportesCoparticipacaoViewUis(
			List<LMTransportesCoparticipacaoViewUi> lmTransportesCoparticipacaoViewUis) {
		this.lmTransportesCoparticipacaoViewUis = lmTransportesCoparticipacaoViewUis;
	}

	public List<LMTransportesResumoViewUi> getLmTransportesResumoViewUis() {
		return lmTransportesResumoViewUis;
	}

	public void setLmTransportesResumoViewUis(List<LMTransportesResumoViewUi> lmTransportesResumoViewUis) {
		this.lmTransportesResumoViewUis = lmTransportesResumoViewUis;
	}
}
