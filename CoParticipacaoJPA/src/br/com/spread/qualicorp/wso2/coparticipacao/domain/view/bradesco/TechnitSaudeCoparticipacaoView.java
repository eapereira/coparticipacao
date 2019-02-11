package br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco;

import java.math.BigDecimal;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class TechnitSaudeCoparticipacaoView extends TechnitCoparticipacaoView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3078942775061985813L;

	private String nameBeneficiario;
	
	private BigDecimal valorPrincipal;
	
	private BigDecimal valorParticipacao;
	
	public TechnitSaudeCoparticipacaoView() {
		super();
	}

	public String getNameBeneficiario() {
		return nameBeneficiario;
	}

	public void setNameBeneficiario(String nameBeneficiario) {
		this.nameBeneficiario = nameBeneficiario;
	}

	public BigDecimal getValorPrincipal() {
		return valorPrincipal;
	}

	public void setValorPrincipal(BigDecimal valorPrincipal) {
		this.valorPrincipal = valorPrincipal;
	}

	public BigDecimal getValorParticipacao() {
		return valorParticipacao;
	}

	public void setValorParticipacao(BigDecimal valorParticipacao) {
		this.valorParticipacao = valorParticipacao;
	}

}
