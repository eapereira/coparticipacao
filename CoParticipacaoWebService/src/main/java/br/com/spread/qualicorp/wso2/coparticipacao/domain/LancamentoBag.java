package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.math.BigDecimal;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class LancamentoBag {

	private ContratoUi contratoUi;

	private Integer mes;

	private Integer ano;

	private Long matriculaDependente;

	private Long matriculaTitular;

	private BigDecimal valorPrincipal;

	public LancamentoBag() {

	}

	public ContratoUi getContratoUi() {
		return contratoUi;
	}

	public void setContratoUi(ContratoUi contratoUi) {
		this.contratoUi = contratoUi;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Long getMatriculaDependente() {
		return matriculaDependente;
	}

	public void setMatriculaDependente(Long matriculaDependente) {
		this.matriculaDependente = matriculaDependente;
	}

	public Long getMatriculaTitular() {
		return matriculaTitular;
	}

	public void setMatriculaTitular(Long matriculaTitular) {
		this.matriculaTitular = matriculaTitular;
	}

	public BigDecimal getValorPrincipal() {
		return valorPrincipal;
	}

	public void setValorPrincipal(BigDecimal valorPrincipal) {
		this.valorPrincipal = valorPrincipal;
	}
}
