package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitHeaderViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitOdontoReport;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitOdontoJRDataSource extends CoParticipacaoDataSource<TechnitOdontoReport> {

	private static final String FIELD_COPARTICIPACAO = "technitOdontoCoparticipacaoViewUis";

	private static final String FIELD_HEADER = "technitOdontoHeaderViewUis";

	private static final String FIELD_MES = "mes";
	private static final String FIELD_ANO = "ano";
	private static final String FIELD_TP_REGISTRO = "tipoRegistro";
	private static final String FIELD_CD_CONTRATO = "cdContrato";
	private static final String FIELD_TP_ARQUIVO = "tipoArquivo";
	private static final String FIELD_DT_COMPETENCIA_MMYY = "dataCompetenciaMMYY";
	private static final String FIELD_DT_COMPETENCIA_MMYYYY = "dataCompetenciaMMYYYY";
	private static final String FIELD_DT_PROCESSAMENTO_MMYY = "dataProcessamentoMMYY";
	private static final String FIELD_DT_PROCESSAMENTO_MMYYYY = "dataProcessamentoMMYYYY";

	public TechnitOdontoJRDataSource() {
		super();
	}

	public TechnitOdontoJRDataSource(List<TechnitOdontoReport> technitOdontoReports) {
		super(technitOdontoReports);
	}

	protected List<TechnitOdontoReport> buildData() {
		List<TechnitOdontoReport> technitOdontoReports = new ArrayList<>();
		TechnitOdontoReport technitOdontoReport = new TechnitOdontoReport();
		TechnitHeaderViewUi technitHeaderViewUi = new TechnitHeaderViewUi();
		LocalDate dataReport = DateUtils.stringToDate("14/08/2018", "dd/MM/yyyy");
		LocalDate currentDate = LocalDate.now();

		technitOdontoReports = new ArrayList<>();
		technitOdontoReport = new TechnitOdontoReport();
		technitOdontoReport.setMes(currentDate.getMonthValue());
		technitOdontoReport.setAno(currentDate.getYear());

		technitHeaderViewUi.setTipoRegistro(1);
		technitHeaderViewUi.setTipoArquivo("PARTIC.SEGURADO");
		technitHeaderViewUi.setCdContrato("091707");
		technitHeaderViewUi.setDataCompetencia(dataReport);
		technitHeaderViewUi.setDataProcessamento(dataReport);

		technitOdontoReport.setTechnitHeaderViewUi(technitHeaderViewUi);
		technitOdontoReport
				.setTechnitOdontoCoparticipacaoViewUis(new TechnitOdontoCoparticipacaoJRDataSource().getData());

		technitOdontoReports.add(technitOdontoReport);

		return technitOdontoReports;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		try {
			if (getRegister() != null) {
				if (FIELD_COPARTICIPACAO.equals(jrField.getName())) {
					return getRegister().getTechnitOdontoCoparticipacaoViewUis();
				} else if (FIELD_HEADER.equals(jrField.getName())) {
					return getRegister().getTechnitHeaderViewUi();
				} else if (FIELD_MES.equals(jrField.getName())) {
					return getRegister().getMes();
				} else if (FIELD_ANO.equals(jrField.getName())) {
					return getRegister().getAno();
				} else if (FIELD_CD_CONTRATO.equals(jrField.getName())) {
					return getRegister().getCdContrato();
				} else if (FIELD_TP_ARQUIVO.equals(jrField.getName())) {
					return getRegister().getTipoArquivo();
				} else if (FIELD_TP_REGISTRO.equals(jrField.getName())) {
					return getRegister().getTipoRegistro();
				} else if (FIELD_DT_COMPETENCIA_MMYY.equals(jrField.getName())) {
					return getRegister().getDataCompetenciaMMYY();
				} else if (FIELD_DT_PROCESSAMENTO_MMYY.equals(jrField.getName())) {
					return getRegister().getDataProcessamentoMMYY();
				} else if (FIELD_DT_COMPETENCIA_MMYYYY.equals(jrField.getName())) {
					return getRegister().getDataCompetenciaMMYYYY();
				} else if (FIELD_DT_PROCESSAMENTO_MMYYYY.equals(jrField.getName())) {
					return getRegister().getDataProcessamentoMMYYYY();
				}
			}

			return null;
		} catch (CoParticipacaoException e) {
			throw new JRException(e);
		}
	}

	public static JRDataSource getInstance() {
		return new TechnitOdontoJRDataSource();
	}
}
