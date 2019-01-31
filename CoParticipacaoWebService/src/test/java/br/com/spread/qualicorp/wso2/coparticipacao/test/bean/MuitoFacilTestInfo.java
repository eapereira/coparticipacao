package br.com.spread.qualicorp.wso2.coparticipacao.test.bean;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class MuitoFacilTestInfo {
	private Integer totalRegistros;

	private BigDecimal totalValorPrincipal;

	public MuitoFacilTestInfo() {
		totalRegistros = NumberUtils.INTEGER_ZERO;
		totalValorPrincipal = BigDecimal.ZERO;
	}

	public Integer getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public BigDecimal getTotalValorPrincipal() {
		return totalValorPrincipal;
	}

	public void setTotalValorPrincipal(BigDecimal totalValorPrincipal) {
		this.totalValorPrincipal = totalValorPrincipal;
	}

}
