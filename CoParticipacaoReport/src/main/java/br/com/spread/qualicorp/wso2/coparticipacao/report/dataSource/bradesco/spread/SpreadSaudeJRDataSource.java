package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.spread;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.SpreadSaudeReport;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class SpreadSaudeJRDataSource extends CoParticipacaoJRDataSource<SpreadSaudeReport> {

	private static final Logger LOGGER = LogManager.getLogger(SpreadSaudeJRDataSource.class);

	public SpreadSaudeJRDataSource() throws JRException {
		super();
	}

	public SpreadSaudeJRDataSource(List<SpreadSaudeReport> data) {
		super(data);
	}

	@Override
	protected List<SpreadSaudeReport> buildData() throws JRException {
		List<SpreadSaudeReport> spreadSaudeReports = new ArrayList<>();
		SpreadSaudeReport spreadSaudeReport = new SpreadSaudeReport();

		spreadSaudeReport.setSpreadSaudeResumoReports(SpreadSaudeResumoJRDataSource.getInstance().getData());
		spreadSaudeReport.setSpreadSaudeCoparticipacaoResumidaViewUis(
				SpreadSaudeCoparticipacaoResumidaJRDataSource.getInstance().getData());
		spreadSaudeReport
				.setSpreadSaudeCoparticipacaoViewUis(SpreadSaudeCoparticipacaoJRDataSource.getInstance().getData());

		spreadSaudeReports.add(spreadSaudeReport);

		return spreadSaudeReports;
	}

	public static SpreadSaudeJRDataSource getInstance() throws JRException {
		return new SpreadSaudeJRDataSource();
	}
}
