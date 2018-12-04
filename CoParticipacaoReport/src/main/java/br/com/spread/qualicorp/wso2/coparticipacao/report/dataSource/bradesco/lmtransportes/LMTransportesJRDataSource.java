package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.lmtransportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.LMTransportesReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class LMTransportesJRDataSource extends CoParticipacaoJRDataSource<LMTransportesReport> {

	public LMTransportesJRDataSource() throws JRException {
		super();
	}

	public LMTransportesJRDataSource(List<LMTransportesReport> LMTransportesReports) {
		super(LMTransportesReports);
	}

	protected List<LMTransportesReport> buildData() throws JRException {
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

	public static JRDataSource getInstance() throws JRException {
		return new LMTransportesJRDataSource();
	}
}
