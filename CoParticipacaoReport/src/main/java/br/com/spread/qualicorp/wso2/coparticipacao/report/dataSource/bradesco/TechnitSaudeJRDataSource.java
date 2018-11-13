package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitSaudeReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitSaudeJRDataSource extends CoParticipacaoDataSource<TechnitSaudeReport> {

	private static final String FIELD_COPARTICIPACAO = "technitSaudeCoparticipacaoViewUis";

	public TechnitSaudeJRDataSource() {
		super();
	}

	public TechnitSaudeJRDataSource(List<TechnitSaudeReport> technitSaudeReports) {
		super(technitSaudeReports);
	}

	protected List<TechnitSaudeReport> buildData() {
		List<TechnitSaudeReport> technitSaudeReports = new ArrayList<>();
		TechnitSaudeReport technitSaudeReport = new TechnitSaudeReport();
		LocalDate currentDate = LocalDate.now();

		technitSaudeReports = new ArrayList<>();
		technitSaudeReport = new TechnitSaudeReport();
		technitSaudeReport.setMes(currentDate.getMonthValue());
		technitSaudeReport.setAno(currentDate.getYear());

		technitSaudeReport.setTechnitSaudeCoparticipacaoViewUis(new TechnitSaudeCoparticipacaoJRDataSource().getData());

		technitSaudeReports.add(technitSaudeReport);

		return technitSaudeReports;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if (getRegister() != null) {
			if (FIELD_COPARTICIPACAO.equals(jrField.getName())) {
				return getRegister().getTechnitSaudeCoparticipacaoViewUis();
			}
		}

		return null;
	}

	public static JRDataSource getInstance() {
		return new TechnitSaudeJRDataSource();
	}
}
