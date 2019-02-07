package br.com.spread.qualicorp.wso2.coparticipacao.test.spreadsheet.impl.sulamerica;

import java.math.BigDecimal;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class HaocGestanteData {

	private Long matricula;
	private String nameBeneficiario;
	private BigDecimal valorIsencao;
	private String nameTitular;
	
	public HaocGestanteData() {
		
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNameBeneficiario() {
		return nameBeneficiario;
	}

	public void setNameBeneficiario(String nameBeneficiario) {
		this.nameBeneficiario = nameBeneficiario;
	}

	public BigDecimal getValorIsencao() {
		return valorIsencao;
	}

	public void setValorIsencao(BigDecimal valorIsencao) {
		this.valorIsencao = valorIsencao;
	}

	public String getNameTitular() {
		return nameTitular;
	}

	public void setNameTitular(String nameTitular) {
		this.nameTitular = nameTitular;
	}
}
