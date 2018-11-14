package br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitHeaderViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class TechnitReport extends BasicReport {

	private TechnitHeaderViewUi technitHeaderViewUi;

	private Integer tipoRegistro;

	private String tipoArquivo;

	public TechnitReport() {
		super();
	}

	public TechnitHeaderViewUi getTechnitHeaderViewUi() {
		return technitHeaderViewUi;
	}

	public void setTechnitHeaderViewUi(TechnitHeaderViewUi technitHeaderViewUi) {
		this.technitHeaderViewUi = technitHeaderViewUi;
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

	public String getDataCompetenciaMMYY() throws CoParticipacaoException {
		if (getTechnitHeaderViewUi().getDataCompetencia() != null) {
			return DateUtils.dateToString(getTechnitHeaderViewUi().getDataCompetencia(), "MM/yy");
		}

		return null;
	}

	public String getDataCompetenciaMMYYYY() throws CoParticipacaoException {
		if (getTechnitHeaderViewUi().getDataCompetencia() != null) {
			return DateUtils.dateToString(getTechnitHeaderViewUi().getDataCompetencia(), "MM/yyyy");
		}

		return null;
	}

	public String getDataProcessamentoMMYY() throws CoParticipacaoException {
		if (getTechnitHeaderViewUi().getDataProcessamento() != null) {
			return DateUtils.dateToString(getTechnitHeaderViewUi().getDataProcessamento(), "MM/yy");
		}

		return null;
	}

	public String getDataProcessamentoMMYYYY() throws CoParticipacaoException {
		if (getTechnitHeaderViewUi().getDataProcessamento() != null) {
			return DateUtils.dateToString(getTechnitHeaderViewUi().getDataProcessamento(), "MM/yyyy");
		}

		return null;
	}

}
