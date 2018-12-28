package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.technit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitHeaderViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitOdontoReport;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitOdontoJRDataSource extends CoParticipacaoJRDataSource<TechnitOdontoReport> {

	private static final Logger LOGGER = LogManager.getLogger(TechnitOdontoJRDataSource.class);

	public TechnitOdontoJRDataSource() throws JRException {
		super();
	}

	public TechnitOdontoJRDataSource(List<TechnitOdontoReport> technitOdontoReports) {
		super(technitOdontoReports);
	}

	protected List<TechnitOdontoReport> buildData() throws JRException {
		List<TechnitOdontoReport> technitOdontoReports = new ArrayList<>();
		TechnitOdontoReport technitOdontoReport = new TechnitOdontoReport();
		TechnitHeaderViewUi technitHeaderViewUi = new TechnitHeaderViewUi();
		LocalDate dataReport;
		LocalDate currentDate;

		try {
			dataReport = DateUtils.stringToDate("14/08/2018", "dd/MM/yyyy");
			currentDate = LocalDate.now();

			technitOdontoReports = new ArrayList<>();
			technitOdontoReport = new TechnitOdontoReport();
			technitOdontoReport.setMes(currentDate.getMonthValue());
			technitOdontoReport.setAno(currentDate.getYear());

			technitHeaderViewUi.setTipoRegistro(1);
			technitHeaderViewUi.setTipoArquivo("PARTIC.SEGURADO");
			technitHeaderViewUi.setCdContrato("091707");
			technitHeaderViewUi.setDataCompetencia(dataReport);
			technitHeaderViewUi.setDataProcessamento(dataReport);

			technitOdontoReport.setTechnitHeaderViewUi(technitHeaderViewUi);
			technitOdontoReport
					.setTechnitOdontoCoparticipacaoViewUis(new TechnitOdontoCoparticipacaoJRDataSource().getData());

			technitOdontoReports.add(technitOdontoReport);

			return technitOdontoReports;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new JRException(e);
		}
	}

	public static JRDataSource getInstance() throws JRException {
		return new TechnitOdontoJRDataSource();
	}
}
