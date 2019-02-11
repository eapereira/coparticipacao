package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.celpe;

import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.CelpeSaudeReport;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CelpeSaudeJRDataSource extends CoParticipacaoJRDataSource<CelpeSaudeReport> {

	public CelpeSaudeJRDataSource() throws JRException {
		super();
		// TODO Auto-generated constructor stub
	}

	public CelpeSaudeJRDataSource(List<CelpeSaudeReport> data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<CelpeSaudeReport> buildData() throws JRException {
		List<CelpeSaudeReport> celpeSaudeReports = new ArrayList<>();
		CelpeSaudeReport celpeSaudeReport = new CelpeSaudeReport();
		celpeSaudeReport.setCdContrato("071421");
		celpeSaudeReport.setMes(8);
		celpeSaudeReport.setAno(2018);
		celpeSaudeReport.setCelpeSaudeResumoViewUis(CelpeSaudeResumoJRDataSource.getInstance().getData());
		celpeSaudeReport.setCelpeSaudeRateioViewUis(CelpeSaudeRateioJRDataSource.getInstance().getData());
		celpeSaudeReport
				.setCelpeSaudeCoparticipacaoViewUis(CelpeSaudeCoparticipacaoJRDataSource.getInstance().getData());

		celpeSaudeReports.add(celpeSaudeReport);

		return celpeSaudeReports;
	}

	public CelpeSaudeJRDataSource getInstance() throws JRException {
		return new CelpeSaudeJRDataSource();
	}
}
