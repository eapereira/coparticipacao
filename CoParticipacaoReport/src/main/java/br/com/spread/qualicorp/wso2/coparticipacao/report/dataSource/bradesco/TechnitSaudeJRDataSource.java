package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitHeaderViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitSaudeReport;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitSaudeJRDataSource extends CoParticipacaoDataSource<TechnitSaudeReport> {
	private static final Logger LOGGER = LogManager.getLogger(TechnitSaudeJRDataSource.class);

	private static final String FIELD_COPARTICIPACAO = "technitSaudeCoparticipacaoViewUis";

	private static final String FIELD_HEADER = "technitHeaderViewUi";

	private static final String FIELD_DT_COMPETENCIA_MMYY = "dataCompetenciaMMYY";
	private static final String FIELD_DT_COMPETENCIA_MMYYYY = "dataCompetenciaMMYYYY";
	private static final String FIELD_DT_PROCESSAMENTO_MMYY = "dataProcessamentoMMYY";
	private static final String FIELD_DT_PROCESSAMENTO_MMYYYY = "dataProcessamentoMMYYYY";

	public TechnitSaudeJRDataSource() throws JRException {
		super();
	}

	public TechnitSaudeJRDataSource(List<TechnitSaudeReport> technitSaudeReports) {
		super(technitSaudeReports);
	}

	protected List<TechnitSaudeReport> buildData() throws JRException {
		List<TechnitSaudeReport> technitSaudeReports = new ArrayList<>();
		TechnitSaudeReport technitSaudeReport = new TechnitSaudeReport();
		TechnitHeaderViewUi technitHeaderViewUi = new TechnitHeaderViewUi();
		LocalDate dataReport = DateUtils.stringToDate("14/08/2018", "dd/MM/yyyy");
		LocalDate currentDate = LocalDate.now();

		technitSaudeReports = new ArrayList<>();
		technitSaudeReport = new TechnitSaudeReport();
		technitSaudeReport.setMes(currentDate.getMonthValue());
		technitSaudeReport.setAno(currentDate.getYear());

		technitHeaderViewUi.setTipoRegistro(1);
		technitHeaderViewUi.setTipoArquivo("PARTIC.SEGURADO");
		technitHeaderViewUi.setCdContrato("091707");
		technitHeaderViewUi.setDataCompetencia(dataReport);
		technitHeaderViewUi.setDataProcessamento(dataReport);

		technitSaudeReport.setTechnitHeaderViewUi(technitHeaderViewUi);
		technitSaudeReport.setTechnitSaudeCoparticipacaoViewUis(new TechnitSaudeCoparticipacaoJRDataSource().getData());

		technitSaudeReports.add(technitSaudeReport);

		return technitSaudeReports;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		try {
			if (getRegister() != null) {
				if (FIELD_COPARTICIPACAO.equals(jrField.getName())) {
					return getRegister().getTechnitSaudeCoparticipacaoViewUis();
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
				}
			}

			LOGGER.info("No field found for [{}]:", jrField.getName());
			return null;
		} catch (CoParticipacaoException e) {
			throw new JRException(e);
		}
	}

	public static JRDataSource getInstance() throws JRException {
		return new TechnitSaudeJRDataSource();
	}
}
