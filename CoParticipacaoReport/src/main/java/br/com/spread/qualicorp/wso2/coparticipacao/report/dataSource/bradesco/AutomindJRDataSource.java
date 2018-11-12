package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.AutomindReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class AutomindJRDataSource extends CoParticipacaoDataSource<AutomindReport> {

	private static final String FIELD_COPARTICIPACAO = "automindCoparticipacaoViewUis";
	private static final String FIELD_RESUMO = "automindResumoViewUis";

	public AutomindJRDataSource() {
		super();
	}

	protected List<AutomindReport> buildData() {
		List<AutomindReport> automindReports = new ArrayList<>();
		AutomindReport automindReport = new AutomindReport();
		LocalDate currentDate = LocalDate.now();

		automindReports = new ArrayList<>();
		automindReport = new AutomindReport();
		automindReport.setMes(currentDate.getMonthValue());
		automindReport.setAno(currentDate.getYear());

		automindReport.setAutomindCoparticipacaoViewUis(new AutomindCoparticipacaoJRDataSource().getData());
		automindReport.setAutomindResumoViewUis(new AutomindResumoJRDataSource().getData());

		automindReports.add(automindReport);

		return automindReports;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if (getRegister() != null) {
			if (FIELD_COPARTICIPACAO.equals(jrField.getName())) {
				return getRegister().getAutomindCoparticipacaoViewUis();
			} else if (FIELD_RESUMO.equals(jrField.getName())) {
				return getRegister().getAutomindResumoViewUis();
			}
		}

		return null;
	}

	public static JRDataSource getInstance() {
		return new AutomindJRDataSource();
	}
}
