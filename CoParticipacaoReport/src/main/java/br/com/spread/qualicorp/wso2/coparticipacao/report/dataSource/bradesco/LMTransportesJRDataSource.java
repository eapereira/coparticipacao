package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.LMTransportesReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class LMTransportesJRDataSource extends CoParticipacaoDataSource<LMTransportesReport> {

	private static final String FIELD_COPARTICIPACAO = "LmTransportesCoparticipacaoViewUis";
	private static final String FIELD_RESUMO = "LmTransportesResumoViewUis";

	public LMTransportesJRDataSource() {
		super();
	}

	public LMTransportesJRDataSource(List<LMTransportesReport> LMTransportesReports) {
		super(LMTransportesReports);
	}

	protected List<LMTransportesReport> buildData() {
		List<LMTransportesReport> LMTransportesReports = new ArrayList<>();
		LMTransportesReport LMTransportesReport = new LMTransportesReport();
		LocalDate currentDate = LocalDate.now();

		LMTransportesReports = new ArrayList<>();
		LMTransportesReport = new LMTransportesReport();
		LMTransportesReport.setMes(currentDate.getMonthValue());
		LMTransportesReport.setAno(currentDate.getYear());

		LMTransportesReport
				.setLmTransportesCoparticipacaoViewUis(new LMTransportesCoparticipacaoJRDataSource().getData());
		LMTransportesReport.setLmTransportesResumoViewUis(new LMTransportesResumoJRDataSource().getData());

		LMTransportesReports.add(LMTransportesReport);

		return LMTransportesReports;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if (getRegister() != null) {
			if (FIELD_COPARTICIPACAO.equals(jrField.getName())) {
				return getRegister().getLmTransportesResumoViewUis();
			} else if (FIELD_RESUMO.equals(jrField.getName())) {
				return getRegister().getLmTransportesResumoViewUis();
			}
		}

		return null;
	}

	public static JRDataSource getInstance() {
		return new LMTransportesJRDataSource();
	}
}
