package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.math.BigDecimal;

/**
 * The persistent class for the tb_regra_valor database table.
 * 
 */
public abstract class RegraValor extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private BigDecimal valor;
	private RegraOperation regraOperation;

	public RegraValor() {
	}

	public RegraValor(RegraValor entity) {
		super(entity);
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal vlRegraValor) {
		this.valor = vlRegraValor;
	}

	public RegraOperation getRegraOperation() {
		return this.regraOperation;
	}

	public void setRegraOperation(RegraOperation regraOperation) {
		this.regraOperation = regraOperation;
	}

}