package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.technit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitHeaderViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.TechnitSaudeReport;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitSaudeJRDataSource extends CoParticipacaoJRDataSource<TechnitSaudeReport> {

	private static final Logger LOGGER = LogManager.getLogger(TechnitSaudeJRDataSource.class);

	public TechnitSaudeJRDataSource() throws JRException {
		super();
	}

	public TechnitSaudeJRDataSource(List<TechnitSaudeReport> technitSaudeReports) {
		super(technitSaudeReports);
	}

	protected List<TechnitSaudeReport> buildData() throws JRException {
		List<TechnitSaudeReport> technitSaudeReports = new ArrayList<>();
		TechnitSaudeReport technitSaudeReport = new TechnitSaudeReport();
		TechnitHeaderViewUi technitHeaderViewUi = new TechnitHeaderViewUi();
		LocalDate dataReport = DateUtils.stringToDate("14/08/2018", "dd/MM/yyyy");
		LocalDate currentDate = LocalDate.now();

		technitSaudeReports = new ArrayList<>();
		technitSaudeReport = new TechnitSaudeReport();
		technitSaudeReport.setMes(currentDate.getMonthValue());
		technitSaudeReport.setAno(currentDate.getYear());

		technitHeaderViewUi.setTipoRegistro(1);
		technitHeaderViewUi.setTipoArquivo("PARTIC.SEGURADO");
		technitHeaderViewUi.setCdContrato("091707");
		technitHeaderViewUi.setDataCompetencia(dataReport);
		technitHeaderViewUi.setDataProcessamento(dataReport);

		technitSaudeReport.setTechnitHeaderViewUi(technitHeaderViewUi);
		technitSaudeReport.setTechnitSaudeCoparticipacaoViewUis(new TechnitSaudeCoparticipacaoJRDataSource().getData());

		technitSaudeReports.add(technitSaudeReport);

		return technitSaudeReports;
	}

	public static JRDataSource getInstance() throws JRException {
		return new TechnitSaudeJRDataSource();
	}
}
