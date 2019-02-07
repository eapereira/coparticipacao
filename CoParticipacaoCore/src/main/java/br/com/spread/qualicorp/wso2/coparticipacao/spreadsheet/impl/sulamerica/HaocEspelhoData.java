package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.impl.sulamerica;

import java.math.BigDecimal;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class HaocEspelhoData {

	private String itemName;
	private BigDecimal valorPrincipal;
	
	public HaocEspelhoData() {
		
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getValorPrincipal() {
		return valorPrincipal;
	}

	public void setValorPrincipal(BigDecimal valorPrincipal) {
		this.valorPrincipal = valorPrincipal;
	}
}
