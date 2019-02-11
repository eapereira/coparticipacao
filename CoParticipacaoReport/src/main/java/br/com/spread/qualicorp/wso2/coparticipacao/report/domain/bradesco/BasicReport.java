package br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco;
/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class BasicReport {

	private Integer mes;

	private Integer ano;

	private String sheetNameCoparticipacao;
	
	private String sheetNameRateio;
	
	private String cdContrato;
	
	public BasicReport() {
		
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

	public String getSheetNameCoparticipacao() {
		return sheetNameCoparticipacao;
	}

	public void setSheetNameCoparticipacao(String sheetNameCoparticipacao) {
		this.sheetNameCoparticipacao = sheetNameCoparticipacao;
	}

	public String getSheetNameRateio() {
		return sheetNameRateio;
	}

	public void setSheetNameRateio(String sheetNameRateio) {
		this.sheetNameRateio = sheetNameRateio;
	}

	public String getCdContrato() {
		return cdContrato;
	}

	public void setCdContrato(String cdContrato) {
		this.cdContrato = cdContrato;
	}
}
