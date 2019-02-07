package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.impl.sulamerica;

import java.math.BigDecimal;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class HaocPrnData {

	private Long matricula;
	private BigDecimal valorPrincipal;
	
	public HaocPrnData() {
		
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public BigDecimal getValorPrincipal() {
		return valorPrincipal;
	}

	public void setValorPrincipal(BigDecimal valorPrincipal) {
		this.valorPrincipal = valorPrincipal;
	}
}
