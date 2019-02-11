package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.CelpeSaudeResumoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CelpeSaudeResumoViewUi extends CelpeSaudeResumoView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8618511607013432464L;

	private List<CelpeSaudeResumoDetailViewUi> celpeSaudeResumoDetailViewUis;

	public CelpeSaudeResumoViewUi() {
		super();
	}

	public String getRamoAndOperadora() {
		StringBuilder sb = new StringBuilder();

		sb.append(getRamo());
		sb.append("/");
		sb.append(getOperadora());

		return sb.toString();
	}

	public List<CelpeSaudeResumoDetailViewUi> getCelpeSaudeResumoDetailViewUis() {
		return celpeSaudeResumoDetailViewUis;
	}

	public void setCelpeSaudeResumoDetailViewUis(List<CelpeSaudeResumoDetailViewUi> celpeSaudeResumoDetailViewUis) {
		this.celpeSaudeResumoDetailViewUis = celpeSaudeResumoDetailViewUis;
	}
}
