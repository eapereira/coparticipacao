package br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco;

import java.math.BigDecimal;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class LMTransportesCoparticipacaoView extends BradescoCoparticipacaoView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3078942775061985813L;

	private BigDecimal fatorModeradorInss;	

	public LMTransportesCoparticipacaoView() {
		super();
	}

	public BigDecimal getFatorModeradorInss() {
		return fatorModeradorInss;
	}

	public void setFatorModeradorInss(BigDecimal fatorModeradorInss) {
		this.fatorModeradorInss = fatorModeradorInss;
	}

}
