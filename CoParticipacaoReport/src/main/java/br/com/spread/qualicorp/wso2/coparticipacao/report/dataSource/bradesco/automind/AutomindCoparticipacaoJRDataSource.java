package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.automind;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.AutomindCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class AutomindCoparticipacaoJRDataSource extends CoParticipacaoJRDataSource<AutomindCoparticipacaoViewUi> {

	public AutomindCoparticipacaoJRDataSource() throws JRException {
		super();
	}

	public AutomindCoparticipacaoJRDataSource(List<AutomindCoparticipacaoViewUi> automindCoparticipacaoViewUis)
			throws JRException {
		super(automindCoparticipacaoViewUis);
	}

	protected List<AutomindCoparticipacaoViewUi> buildData() throws JRException {
		List<AutomindCoparticipacaoViewUi> automindCoparticipacaoViewUis = new ArrayList<>();
		AutomindCoparticipacaoViewUi automindCoparticipacaoViewUi = new AutomindCoparticipacaoViewUi();

		automindCoparticipacaoViewUi.setCdContrato("074210");
		automindCoparticipacaoViewUi.setSubFatura("001");
		automindCoparticipacaoViewUi.setCdEmpresa("AUTOMIND");
		automindCoparticipacaoViewUi.setCertificado("8345");

		automindCoparticipacaoViewUi.setNomeTitular("JULIA MC MILLAN CARVALHO CAMPOS");
		automindCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		automindCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		automindCoparticipacaoViewUi.setMatricula("75001");
		automindCoparticipacaoViewUi.setProfissao("Costureira");

		automindCoparticipacaoViewUis.add(automindCoparticipacaoViewUi);

		return automindCoparticipacaoViewUis;
	}

	public static JRDataSource getInstance() throws JRException {
		return new AutomindCoparticipacaoJRDataSource();
	}

}
