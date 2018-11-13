package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitOdontoReport;
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

	public TechnitOdontoJRDataSource() {
		super();
	}

	public TechnitOdontoJRDataSource(List<TechnitOdontoReport> technitOdontoReports) {
		super(technitOdontoReports);
	}

	protected List<TechnitOdontoReport> buildData() {
		List<TechnitOdontoReport> technitOdontoReports = new ArrayList<>();
		TechnitOdontoReport technitOdontoReport = new TechnitOdontoReport();
		LocalDate currentDate = LocalDate.now();

		technitOdontoReports = new ArrayList<>();
		technitOdontoReport = new TechnitOdontoReport();
		technitOdontoReport.setMes(currentDate.getMonthValue());
		technitOdontoReport.setAno(currentDate.getYear());

		technitOdontoReport
				.setTechnitOdontoCoparticipacaoViewUis(new TechnitOdontoCoparticipacaoJRDataSource().getData());

		technitOdontoReports.add(technitOdontoReport);

		return technitOdontoReports;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if (getRegister() != null) {
			if (FIELD_COPARTICIPACAO.equals(jrField.getName())) {
				return getRegister().getTechnitOdontoCoparticipacaoViewUis();
			}
		}

		return null;
	}

	public static JRDataSource getInstance() {
		return new TechnitOdontoJRDataSource();
	}
}
