package br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco;

import java.time.LocalDate;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class TechnitHeaderView extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5017399126546731856L;

	private Integer tipoRegistro;
	private String tipoArquivo;
	private String cdContrato;
	private LocalDate dataCompetencia;
	private LocalDate dataProcessamento;

	public TechnitHeaderView() {

	}

	public Integer getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Integer tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public String getCdContrato() {
		return cdContrato;
	}

	public void setCdContrato(String cdContrato) {
		this.cdContrato = cdContrato;
	}

	public LocalDate getDataCompetencia() {
		return dataCompetencia;
	}

	public void setDataCompetencia(LocalDate dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}

	public LocalDate getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(LocalDate dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
}
