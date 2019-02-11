package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.lmtransportes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.LMTransportesCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class LMTransportesCoparticipacaoJRDataSource
		extends CoParticipacaoJRDataSource<LMTransportesCoparticipacaoViewUi> {

	public LMTransportesCoparticipacaoJRDataSource() throws JRException {
		super();
	}

	public LMTransportesCoparticipacaoJRDataSource(
			List<LMTransportesCoparticipacaoViewUi> lmTransportesCoparticipacaoViewUis) {
		super(lmTransportesCoparticipacaoViewUis);
	}

	protected List<LMTransportesCoparticipacaoViewUi> buildData() throws JRException {
		List<LMTransportesCoparticipacaoViewUi> lmTransportesCoparticipacaoViewUis = new ArrayList<>();
		LMTransportesCoparticipacaoViewUi lmTransportesCoparticipacaoViewUi = new LMTransportesCoparticipacaoViewUi();

		lmTransportesCoparticipacaoViewUi.setCdContrato("074210");
		lmTransportesCoparticipacaoViewUi.setCdEmpresa("AUTOMIND");
		lmTransportesCoparticipacaoViewUi.setCertificado("8345");

		lmTransportesCoparticipacaoViewUi.setNomeTitular("JULIA MC MILLAN CARVALHO CAMPOS");
		lmTransportesCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		lmTransportesCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		lmTransportesCoparticipacaoViewUi.setMatricula("75001");
		lmTransportesCoparticipacaoViewUi.setProfissao("Costureira");

		lmTransportesCoparticipacaoViewUis.add(lmTransportesCoparticipacaoViewUi);

		return lmTransportesCoparticipacaoViewUis;
	}

	public static JRDataSource getInstance() throws JRException {
		return new LMTransportesCoparticipacaoJRDataSource();
	}

}
