package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.celpe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeResumoDetailViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CelpeSaudeResumoDetailJRDataSource extends CoParticipacaoJRDataSource<CelpeSaudeResumoDetailViewUi> {

	private static final Logger LOGGER = LogManager.getLogger(CelpeSaudeResumoDetailJRDataSource.class);

	public CelpeSaudeResumoDetailJRDataSource() throws JRException {
		super();
	}

	public CelpeSaudeResumoDetailJRDataSource(List<CelpeSaudeResumoDetailViewUi> celpeSaudeResumoDetailViewUis) {
		super(celpeSaudeResumoDetailViewUis);
	}

	@Override
	protected List<CelpeSaudeResumoDetailViewUi> buildData() throws JRException {
		List<CelpeSaudeResumoDetailViewUi> celpeSaudeResumoViewUis = new ArrayList<>();
		CelpeSaudeResumoDetailViewUi celpeSaudeResumoViewUi = new CelpeSaudeResumoDetailViewUi();
		celpeSaudeResumoViewUi.setCdContrato("071421");
		celpeSaudeResumoViewUi.setSubEstipulante("Celpe");
		celpeSaudeResumoViewUi.setVidas(1354);
		celpeSaudeResumoViewUi.setPercentualVidas(100f);
		celpeSaudeResumoViewUi.setValor(new BigDecimal("139586.21"));
		celpeSaudeResumoViewUi.setPercentualValor(100f);

		celpeSaudeResumoViewUis.add(celpeSaudeResumoViewUi);

		return celpeSaudeResumoViewUis;
	}

	public static CelpeSaudeResumoDetailJRDataSource getInstance() throws JRException {
		return new CelpeSaudeResumoDetailJRDataSource();
	}

}
