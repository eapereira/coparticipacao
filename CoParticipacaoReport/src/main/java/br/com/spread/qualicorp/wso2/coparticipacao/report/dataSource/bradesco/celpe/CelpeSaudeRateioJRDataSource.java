package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.celpe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeRateioViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CelpeSaudeRateioJRDataSource extends CoParticipacaoJRDataSource<CelpeSaudeRateioViewUi> {

	public CelpeSaudeRateioJRDataSource() throws JRException {
		super();
		// TODO Auto-generated constructor stub
	}

	public CelpeSaudeRateioJRDataSource(List<CelpeSaudeRateioViewUi> data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<CelpeSaudeRateioViewUi> buildData() throws JRException {
		List<CelpeSaudeRateioViewUi> celpeSaudeRateioViewUis = new ArrayList<>();
		CelpeSaudeRateioViewUi celpeSaudeRateioViewUi = new CelpeSaudeRateioViewUi();
		celpeSaudeRateioViewUi.setNameTitular("WILSON RAIMUNDO SOARES");
		celpeSaudeRateioViewUi.setValor(new BigDecimal("71.51"));
		celpeSaudeRateioViewUis.add(celpeSaudeRateioViewUi);

		celpeSaudeRateioViewUi = new CelpeSaudeRateioViewUi();
		celpeSaudeRateioViewUi.setNameTitular("WILTON COSTA DA SILVA");
		celpeSaudeRateioViewUi.setValor(new BigDecimal("235.40"));
		celpeSaudeRateioViewUis.add(celpeSaudeRateioViewUi);

		celpeSaudeRateioViewUi = new CelpeSaudeRateioViewUi();
		celpeSaudeRateioViewUi.setNameTitular("WINICIUS BRUNO CELESTINO VERISSIMO");
		celpeSaudeRateioViewUi.setValor(new BigDecimal("14.17"));
		celpeSaudeRateioViewUis.add(celpeSaudeRateioViewUi);

		celpeSaudeRateioViewUi = new CelpeSaudeRateioViewUi();
		celpeSaudeRateioViewUi.setNameTitular("WINSTTON L NASCIMENTO ANDRADE");
		celpeSaudeRateioViewUi.setValor(new BigDecimal("35.84"));
		celpeSaudeRateioViewUis.add(celpeSaudeRateioViewUi);

		return celpeSaudeRateioViewUis;
	}

	public static CelpeSaudeRateioJRDataSource getInstance() throws JRException {
		return new CelpeSaudeRateioJRDataSource();
	}
}
