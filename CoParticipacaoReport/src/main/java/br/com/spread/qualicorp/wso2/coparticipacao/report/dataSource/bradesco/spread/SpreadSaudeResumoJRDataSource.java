package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.spread;

import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.SpreadSaudeResumoReport;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class SpreadSaudeResumoJRDataSource extends CoParticipacaoJRDataSource<SpreadSaudeResumoReport> {

	public SpreadSaudeResumoJRDataSource() throws JRException {
		super();
	}

	public SpreadSaudeResumoJRDataSource(List<SpreadSaudeResumoReport> data) {
		super(data);
	}

	@Override
	protected List<SpreadSaudeResumoReport> buildData() throws JRException {
		List<SpreadSaudeResumoReport> spreadSaudeResumoReports = new ArrayList<>();
		SpreadSaudeResumoReport spreadSaudeResumoReport = new SpreadSaudeResumoReport();

		spreadSaudeResumoReport.setResumo(SpreadSaudeResumoCoparticipacaoJRDataSource.getInstance().getData());
		spreadSaudeResumoReport.setResumoInativo(SpreadSaudeResumoInativoJRDataSource.getInstance().getData());

		spreadSaudeResumoReports.add(spreadSaudeResumoReport);

		return spreadSaudeResumoReports;
	}

	public static SpreadSaudeResumoJRDataSource getInstance() throws JRException {
		return new SpreadSaudeResumoJRDataSource();
	}
}
