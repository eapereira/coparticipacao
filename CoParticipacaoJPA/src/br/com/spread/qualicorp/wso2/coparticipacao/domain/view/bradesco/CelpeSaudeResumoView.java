package br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco;

import java.math.BigDecimal;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class CelpeSaudeResumoView extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6393107110995481349L;

	private String ramo;
	private String operadora;
	private String competencia;
	private BigDecimal valor;

	private String competenciaAnterior;
	private BigDecimal valorAnterior;

	public CelpeSaudeResumoView() {
		super();
	}

	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getCompetenciaAnterior() {
		return competenciaAnterior;
	}

	public void setCompetenciaAnterior(String competenciaAnterior) {
		this.competenciaAnterior = competenciaAnterior;
	}

	public BigDecimal getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(BigDecimal valorAnterior) {
		this.valorAnterior = valorAnterior;
	}
}
