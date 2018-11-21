package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	private static final Logger LOGGER = LogManager.getLogger(TechnitOdontoJRDataSource.class);

	private static final String FIELD_COPARTICIPACAO = "technitOdontoCoparticipacaoViewUis";

	private static final String FIELD_HEADER = "technitHeaderViewUi";

	private static final String FIELD_TP_REGISTRO = "tipoRegistro";
	private static final String FIELD_TP_ARQUIVO = "tipoArquivo";
	private static final String FIELD_CD_CONTRATO = "cdContrato";

	private static final String FIELD_DT_COMPETENCIA_MMYY = "dataCompetenciaMMYY";
	private static final String FIELD_DT_COMPETENCIA_MMYYYY = "dataCompetenciaMMYYYY";
	private static final String FIELD_DT_PROCESSAMENTO_MMYY = "dataProcessamentoMMYY";
	private static final String FIELD_DT_PROCESSAMENTO_MMYYYY = "dataProcessamentoMMYYYY";

	private static final String FIELD_SHEETNAME_COPARTICIPACAO = "sheetNameCoparticipacao";
	private static final String FIELD_SHEETNAME_RESUMO = "sheetNameRateio";

	public TechnitOdontoJRDataSource() throws JRException {
		super();
	}

	public TechnitOdontoJRDataSource(List<TechnitOdontoReport> technitOdontoReports) {
		super(technitOdontoReports);
	}

	protected List<TechnitOdontoReport> buildData() throws JRException {
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
			LOGGER.info("Looking for Report´s field[{}]:", jrField.getName());

			if (getRegister() != null) {
				if (FIELD_TP_REGISTRO.equals(jrField.getName())) {
					return getRegister().getTechnitHeaderViewUi().getTipoRegistro();
				} else if (FIELD_TP_ARQUIVO.equals(jrField.getName())) {
					return getRegister().getTechnitHeaderViewUi().getTipoArquivo();
				} else if (FIELD_CD_CONTRATO.equals(jrField.getName())) {
					return getRegister().getTechnitHeaderViewUi().getCdContrato();
				} else if (FIELD_COPARTICIPACAO.equals(jrField.getName())) {
					return getRegister().getTechnitOdontoCoparticipacaoViewUis();
				} else if (FIELD_HEADER.equals(jrField.getName())) {
					return getRegister().getTechnitHeaderViewUi();
				} else if (FIELD_DT_COMPETENCIA_MMYY.equals(jrField.getName())) {
					return getRegister().getDataCompetenciaMMYY();
				} else if (FIELD_DT_PROCESSAMENTO_MMYY.equals(jrField.getName())) {
					return getRegister().getDataProcessamentoMMYY();
				} else if (FIELD_DT_COMPETENCIA_MMYYYY.equals(jrField.getName())) {
					return getRegister().getDataCompetenciaMMYYYY();
				} else if (FIELD_DT_PROCESSAMENTO_MMYYYY.equals(jrField.getName())) {
					return getRegister().getDataProcessamentoMMYYYY();
				} else if (FIELD_SHEETNAME_COPARTICIPACAO.equals(jrField.getName())) {
					return getRegister().getSheetNameCoparticipacao();
				} else if (FIELD_SHEETNAME_RESUMO.equals(jrField.getName())) {
					return getRegister().getSheetNameRateio();
				}
			}

			LOGGER.info("Report´s field[{}] not found:", jrField.getName());
			return null;
		} catch (CoParticipacaoException e) {
			throw new JRException(e);
		}
	}

	public static JRDataSource getInstance() throws JRException {
		return new TechnitOdontoJRDataSource();
	}
}
