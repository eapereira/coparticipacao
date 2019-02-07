package br.com.spread.qualicorp.wso2.coparticipacao.test.bean;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TestInfo {
	private int totalRegistros;

	private BigDecimal totalValorPrincipal;

	public TestInfo() {
		totalRegistros = NumberUtils.INTEGER_ZERO;
		totalValorPrincipal = BigDecimal.ZERO;
	}

	public int getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public BigDecimal getTotalValorPrincipal() {
		return totalValorPrincipal;
	}

	public void setTotalValorPrincipal(BigDecimal totalValorPrincipal) {
		this.totalValorPrincipal = totalValorPrincipal;
	}

}
