package br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco;

import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.SpreadSaudeResumoViewUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class SpreadSaudeResumoReport {

	private List<SpreadSaudeResumoViewUi> resumo;

	private List<SpreadSaudeResumoViewUi> resumoInativo;

	public SpreadSaudeResumoReport() {
		resumo = new ArrayList<>();
		resumoInativo = new ArrayList<>();
	}

	public List<SpreadSaudeResumoViewUi> getResumo() {
		return resumo;
	}

	public void setResumo(List<SpreadSaudeResumoViewUi> resumo) {
		this.resumo = resumo;
	}

	public List<SpreadSaudeResumoViewUi> getResumoInativo() {
		return resumoInativo;
	}

	public void setResumoInativo(List<SpreadSaudeResumoViewUi> resumoInativo) {
		this.resumoInativo = resumoInativo;
	}
}
