package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.automind;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.AutomindReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class AutomindJRDataSource extends CoParticipacaoJRDataSource<AutomindReport> {

	private static final Logger LOGGER = LogManager.getLogger(AutomindJRDataSource.class);

	private static final String FIELD_COPARTICIPACAO = "automindCoparticipacaoViewUis";
	private static final String FIELD_RESUMO = "automindResumoViewUis";
	private static final String FIELD_SHEETNAME_COPARTICIPACAO = "sheetNameCoparticipacao";
	private static final String FIELD_SHEETNAME_RESUMO = "sheetNameRateio";
	private static final String FIELD_MES = "mes";
	private static final String FIELD_ANO = "ano";

	public AutomindJRDataSource() throws JRException {
		super();
	}

	public AutomindJRDataSource(List<AutomindReport> automindReports) throws JRException {
		super(automindReports);
	}

	protected List<AutomindReport> buildData() throws JRException {
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
			} else if (FIELD_SHEETNAME_COPARTICIPACAO.equals(jrField.getName())) {
				return getRegister().getSheetNameCoparticipacao();
			} else if (FIELD_SHEETNAME_RESUMO.equals(jrField.getName())) {
				return getRegister().getSheetNameRateio();
			} else if (FIELD_MES.equals(jrField.getName())) {
				return getRegister().getMes();
			} else if (FIELD_ANO.equals(jrField.getName())) {
				return getRegister().getAno();
			}
		}

		LOGGER.info("Report's field[{}] not found:", jrField.getName());
		return null;
	}

	public static JRDataSource getInstance() throws JRException {
		return new AutomindJRDataSource();
	}
}
